package DataAccesPackage;

import ModelsPackage.RepairSearchModel;
import ModelsPackage.RepairStatusModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairDataAccess {

    public List<RepairSearchModel> getRepairsByStatus(String statusLabel) throws SQLException {
        List<RepairSearchModel> repairs = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();

        String query = "SELECT r.dat, r.cost, b.serial_number, b.is_electric, b.nb_kilometer, m.first_name, m.last_name " +
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
                        rs.getDate("dat"),
                        rs.getDouble("cost"),
                        rs.getString("serial_number"),
                        rs.getBoolean("is_electric"),
                        rs.getInt("nb_kilometer"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
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
}
