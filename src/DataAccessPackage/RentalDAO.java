package DataAccessPackage;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.RentalModel;
import ModelsPackage.BikeModel;
import ModelsPackage.SubscriptionModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {

    private final Connection connection;

    public RentalDAO() {
        this.connection = SingletonConnection.getInstance();
    }

    public boolean existsRentalId(int id) throws DataAccessException {
        String sql = "SELECT 1 FROM rental WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (SQLException e) {
            throw new DataAccessException("Erreur vérification existence location (ID=" + id + ")", e);
        }
    }

    public boolean existsBike(int serial) throws DataAccessException {
        String sql = "SELECT 1 FROM bike WHERE serial_number = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, serial);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (SQLException e) {
            throw new DataAccessException("Erreur vérification existence vélo (ID=" + serial + ")", e);
        }
    }

    public boolean existsSubscription(int card) throws DataAccessException {
        String sql = "SELECT 1 FROM subscription WHERE card_number = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, card);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (SQLException e) {
            throw new DataAccessException("Erreur vérification existence abonnement (ID=" + card + ")", e);
        }
    }

    public List<RentalModel> getAllRentals() throws DataAccessException {
        List<RentalModel> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rental";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                rentals.add(mapResultSetToRental(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la récupération des locations", e);
        }
        return rentals;
    }

    public RentalModel getRentalById(int id) throws DataAccessException {
        String sql = "SELECT * FROM rental WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapResultSetToRental(rs);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la récupération de la location (ID=" + id + ")", e);
        }
        return null;
    }

    public void insertRental(RentalModel rental) throws DataAccessException {
        String sql = "INSERT INTO rental (id, start_date, return_date, comment, had_issue, bike_id, sub_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rental.getRentalId());
            // start_date
            stmt.setDate(2, Date.valueOf(rental.getStartDate()));

            // return_date
            if (rental.getReturnDate() != null) {
                stmt.setDate(3, Date.valueOf(rental.getReturnDate()));
            } else {
                stmt.setNull(3, Types.DATE);
            }

            // comment
            if (rental.getComment() != null && !rental.getComment().isEmpty()) {
                stmt.setString(4, rental.getComment());
            } else {
                stmt.setNull(4, Types.LONGVARCHAR);
            }

            stmt.setBoolean(5, rental.isHadIssue());
            stmt.setInt(6, rental.getBike().getSerialNumber());
            stmt.setInt(7, rental.getSubscription().getCardNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            handleIntegrityErrorsOnWrite(e, rental);
            throw new DataAccessException("Erreur lors de l'insertion de la location", e);
        }
    }

    public void updateRental(RentalModel rental) throws DataAccessException {
        String sql = "UPDATE rental SET start_date = ?, return_date = ?, comment = ?, had_issue = ?, bike_id = ?, sub_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(rental.getStartDate()));

            if (rental.getReturnDate() != null) {
                stmt.setDate(2, Date.valueOf(rental.getReturnDate()));
            } else {
                stmt.setNull(2, Types.DATE);
            }

            if (rental.getComment() != null && !rental.getComment().isEmpty()) {
                stmt.setString(3, rental.getComment());
            } else {
                stmt.setNull(3, Types.LONGVARCHAR);
            }

            stmt.setBoolean(4, rental.isHadIssue());
            stmt.setInt(5, rental.getBike().getSerialNumber());
            stmt.setInt(6, rental.getSubscription().getCardNumber());
            stmt.setInt(7, rental.getRentalId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new DataAccessException("Aucune location trouvée pour l'ID " + rental.getRentalId());
            }
        } catch (SQLException e) {
            handleIntegrityErrorsOnWrite(e, rental);
            throw new DataAccessException("Erreur lors de la mise à jour de la location (ID=" + rental.getRentalId() + ")", e);
        }
    }

    public void deleteRental(int id) throws DataAccessException {
        String sql = "DELETE FROM rental WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new DataAccessException("Aucune location trouvée pour l'ID " + id);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la suppression de la location (ID=" + id + ")", e);
        }
    }

    private RentalModel mapResultSetToRental(ResultSet rs) throws SQLException {
        RentalModel rental = new RentalModel();

        rental.setRentalId(rs.getInt("id"));
        rental.setStartDate(rs.getDate("start_date").toLocalDate());

        Date ret = rs.getDate("return_date");
        rental.setReturnDate(ret != null ? ret.toLocalDate() : null);

        String comment = rs.getString("comment");
        rental.setComment(comment);

        rental.setHadIssue(rs.getBoolean("had_issue"));

        BikeModel bike = new BikeModel();
        bike.setSerialNumber(rs.getInt("bike_id"));
        rental.setBike(bike);

        SubscriptionModel subscription = new SubscriptionModel();
        subscription.setCardNumber(rs.getInt("sub_id"));
        rental.setSubscription(subscription);

        return rental;
    }

    private void handleIntegrityErrorsOnWrite(SQLException e, RentalModel rental) throws DataAccessException {
        if ("23000".equals(e.getSQLState())) {
            String msg = e.getMessage() != null ? e.getMessage().toLowerCase() : "";

            // Id déjà utilisé (PK)
            if (msg.contains("duplicate") && msg.contains("'primary'")) {
                throw new DataAccessException("L'ID " + rental.getRentalId() + " est déjà utilisé.", e);
            }
            if (msg.contains("duplicate") && msg.contains("for key 'primary'")) {
                throw new DataAccessException("L'ID " + rental.getRentalId() + " est déjà utilisé.", e);
            }

            // FK bike/sub inexistants
            if (msg.contains("foreign key") && msg.contains("bike_id")) {
                throw new DataAccessException("Aucun vélo trouvé avec l'ID " + rental.getBike().getSerialNumber() + ".", e);
            }
            if (msg.contains("foreign key") && msg.contains("sub_id")) {
                throw new DataAccessException("Aucun abonnement trouvé avec l'ID " + rental.getSubscription().getCardNumber() + ".", e);
            }
        }
    }
    public List<Integer> getAllBikeIds() throws DataAccessException {
        String sql = "SELECT serial_number FROM bike ORDER BY serial_number";
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) ids.add(rs.getInt(1));
            return ids;
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors du chargement des ID vélos", e);
        }
    }

    public List<Integer> getAllSubscriptionIds() throws DataAccessException {
        String sql = "SELECT card_number FROM subscription ORDER BY card_number";
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) ids.add(rs.getInt(1));
            return ids;
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors du chargement des ID abonnés", e);
        }
    }

}
