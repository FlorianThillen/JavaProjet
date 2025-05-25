package DataAccessPackage;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.BrandRepairCostModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandRepairCostDAO {
    public List<BrandRepairCostModel> getAverageCostPerBrand() throws DataAccessException {
        Connection connection = SingletonConnection.getInstance();
        List<BrandRepairCostModel> result = new ArrayList<>();

        String query = "SELECT br.name AS brandName, AVG(r.cost) AS avgCost " +
                "FROM repair r " +
                "JOIN bike b ON r.serial_number = b.serial_number " +
                "JOIN brand br ON b.brand_name = br.name " +
                "GROUP BY br.name";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String brandName = rs.getString("brandName");
                float avgCost = rs.getFloat("avgCost");

                result.add(new BrandRepairCostModel(brandName, avgCost));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erreur de récupération des coûts moyens de réparation", e);
        }

        return result;
    }
}
