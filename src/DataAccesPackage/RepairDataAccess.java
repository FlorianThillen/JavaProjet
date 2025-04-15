package DataAccesPackage;


import ModelsPackage.RepairModel;
import ModelsPackage.RepairSearchModel;
import ModelsPackage.RepairStatusModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairDataAccess {
    public List<RepairSearchModel> getRepairsByStatus(String statusLabel) throws SQLException {
        List<RepairSearchModel> repairs = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();

        String query = "SELECT r.date, r.cost, b.serialNumber, b.isElectric, b.nbKilometer, m.firstname, m.lastname " +
                "FROM repair r " +
                "JOIN bike b ON r.bikeID = b.bikeID " +
                "JOIN mechanic m ON r.mechanicID = m.mechanicID " +
                "JOIN repairstatus rs ON r.statusID = rs.statusID " +
                "WHERE rs.label = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, statusLabel);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                repairs.add(new RepairSearchModel(
                        rs.getDate("date"),
                        rs.getDouble("cost"),
                        rs.getString("serialNumber"),
                        rs.getBoolean("isElectric"),
                        rs.getInt("nbKilometer"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                ));
            }
        }
        return repairs;
    }

    public List<RepairStatusModel> getAllStatus() {
        List<RepairStatusModel> status = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();

        String query = "SELECT * FROM repairStatus";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                status.add(new RepairStatusModel(
                        rs.getString("statusID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

}
