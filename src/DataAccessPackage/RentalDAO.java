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
                if (rs.next()) {
                    return mapResultSetToRental(rs);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la récupération de la location avec l'ID : " + id, e);
        }

        return null;
    }

    public void insertRental(RentalModel rental) throws DataAccessException {
        String sql = "INSERT INTO rental (id, start_date, return_date, comment, had_issue, bike_id, sub_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rental.getRentalId());
            stmt.setDate(2, Date.valueOf(rental.getStartDate()));
            stmt.setDate(3, Date.valueOf(rental.getReturnDate()));
            stmt.setString(4, rental.getComment());
            stmt.setBoolean(5, rental.isHadIssue());
            stmt.setInt(6, rental.getBike().getSerialNumber());
            stmt.setInt(7, rental.getSubscription().getCardNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de l'insertion de la location", e);
        }
    }

    public void updateRental(RentalModel rental) throws DataAccessException {
        String sql = "UPDATE rental SET start_date = ?, return_date = ?, comment = ?, had_issue = ?, bike_id = ?, sub_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(rental.getStartDate()));
            stmt.setDate(2, Date.valueOf(rental.getReturnDate()));
            stmt.setString(3, rental.getComment());
            stmt.setBoolean(4, rental.isHadIssue());
            stmt.setInt(5, rental.getBike().getSerialNumber());
            stmt.setInt(6, rental.getSubscription().getCardNumber());
            stmt.setInt(7, rental.getRentalId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la mise à jour de la location avec l'ID : " + rental.getRentalId(), e);
        }
    }

    public void deleteRental(int id) throws DataAccessException {
        String sql = "DELETE FROM rental WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la suppression de la location avec l'ID : " + id, e);
        }
    }

    private RentalModel mapResultSetToRental(ResultSet rs) throws SQLException {
        RentalModel rental = new RentalModel();

        rental.setRentalId(rs.getInt("id"));
        rental.setStartDate(rs.getDate("start_date").toLocalDate());
        rental.setReturnDate(rs.getDate("return_date").toLocalDate());
        rental.setComment(rs.getString("comment"));
        rental.setHadIssue(rs.getBoolean("had_issue"));

        BikeModel bike = new BikeModel();
        bike.setSerialNumber(rs.getInt("bike_id"));
        rental.setBike(bike);

        SubscriptionModel subscription = new SubscriptionModel();
        subscription.setCardNumber(rs.getInt("sub_id"));
        rental.setSubscription(subscription);

        return rental;
    }
}
