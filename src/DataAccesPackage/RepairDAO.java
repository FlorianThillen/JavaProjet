package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.RepairModel;
import ModelsPackage.RepairSearchModel;
import ModelsPackage.RepairStatusModel;

import javax.xml.crypto.Data;
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
                "JOIN mechanic m ON r.id = m.repair_id " +
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

    public void insert(RepairModel repair) throws DataAccesException {
        Connection connection = SingletonConnection.getInstance();

        String query = """
                INSERT INTO repair (id, cost, date, libelle, serial_number)
                VALUES (?,?,?,?,?);
                """;

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, repair.getRepairId());
            stmt.setFloat(2, repair.getCost());
            stmt.setDate(3, repair.getDate());
            stmt.setString(4, repair.getStatus().getStatus());
            stmt.setInt(5, repair.getBike().getSerialNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DataAccesException("Erreur lors de l'insertion de la nouvelle réparation", e.getCause());
        }
    }
}
