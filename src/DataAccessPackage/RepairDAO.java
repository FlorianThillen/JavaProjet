package DataAccessPackage;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.RepairModel;
import ModelsPackage.RepairSearchModel;
import ModelsPackage.RepairStatusModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairDAO {

    public List<RepairSearchModel> getRepairsByStatus(String statusLabel) throws SQLException {
        List<RepairSearchModel> repairs = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();

        String query = "SELECT r.date, r.cost, b.serial_number, b.is_electric, b.nb_kilometer, m.first_name, m.last_name " +
                "FROM repair r " +
                "JOIN bike b ON r.serial_number = b.serial_number " +
                "JOIN repair_status rs ON r.libelle = rs.libelle " +
                "JOIN mechanic m ON r.mechanic_id = m.badge_id " +
                "WHERE rs.libelle = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, statusLabel);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                repairs.add(new RepairSearchModel(
                        rs.getDate(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)
                ));
            }
        }
        return repairs;
    }

    public List<RepairStatusModel> getAllStatus() {
        List<RepairStatusModel> status = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();

        String query = "SELECT libelle FROM repair_status";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                status.add(new RepairStatusModel(
                        rs.getString("libelle")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public void insert(RepairModel repair) throws DataAccessException {
        Connection connection = SingletonConnection.getInstance();

        String query = """
                INSERT INTO repair (cost, date, libelle, serial_number, mechanic_id)
                VALUES (?,?,?,?,?);
                """;

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setFloat(1, repair.getCost());
            stmt.setDate(2, repair.getDate());
            stmt.setString(3, repair.getStatus().getStatus());
            stmt.setInt(4, repair.getBike().getSerialNumber());
            stmt.setInt(5, repair.getMechanic().getBadgeId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DataAccessException("Erreur lors de l'insertion de la nouvelle réparation", e.getCause());
        }
    }
}
