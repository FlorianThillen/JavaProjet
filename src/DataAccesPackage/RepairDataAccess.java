package DataAccesPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairDataAccess {
    public List<Object[]> searchRepairsByStatus(String statusLabel) {
        Connection connection = SingletonConnection.getInstance();
        List<Object[]> results = new ArrayList<>();
        String sql = "SELECT r.date, r.cost, b.serialNumber, m.firstname, m.lastname " +
                "FROM repair r " +
                "JOIN bike b ON r.bikeId = b.serialNumber " +
                "JOIN mechanic m ON r.mechanicId = m.badgeId " +
                "JOIN repair_status s ON r.status = s.status " +
                "WHERE s.status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, statusLabel);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                        rs.getDate("date"),
                        rs.getInt("cost"),
                        rs.getInt("serialNumber"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                };
                results.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
