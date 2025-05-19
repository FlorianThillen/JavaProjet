package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BrandRepairCostModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandRepairCostDAO {
    public List<BrandRepairCostModel> getAverageCostPerBrand() throws DataAccesException{
        Connection connection = SingletonConnection.getInstance();
        List<BrandRepairCostModel> result = new ArrayList<>();

        String query = "SELECT br.name AS brandName, AVG(r.cost) AS avgCost" +
                "FROM repair r" +
                "JOIN bike b ON r.bike_id = b.id_brand" +
                "JOIN brand br ON b.brand_id = br.id_brand" +
                "GROUP BY br.name";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                rs.getString("brandName");
                rs.getFloat("avgCost");
            }
        }catch (SQLException e){
            throw new DataAccesException("Erreur de récupération des coûts moyen de réparation",e);
        }
        return result;
    }
}
