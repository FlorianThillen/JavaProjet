package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BrandRepairCostModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandRepairCostDAO {
    //private final Connection connection;

    public BrandRepairCostDAO() {
   //     this.connection = SingletonConnection.getInstance();
    }

    public List<BrandRepairCostModel> getAverageCostPerBrand() throws DataAccesException{
        List<BrandRepairCostModel> fakeList = new ArrayList<>();

        fakeList.add(new BrandRepairCostModel("Décathlon", 35.5f));
        fakeList.add(new BrandRepairCostModel("Patapim", 22.3f));
        fakeList.add(new BrandRepairCostModel("Crocodiro", 48.0f));

        return fakeList;
/*

        List<BrandRepairCostModel> result = new ArrayList<>();

        String query = "SELECT br.name AS brandName, AVG(r.cost) AS avgCost" +
                "FROM repair r" +
                "JOIN bike b ON r.bike_id = b.id_brand" +
                "JOIN brand br ON b.brand_id = br.id_brand" +
                "GROUP BY br.name";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String brandName = rs.getString("brandName");
                float averageCost = rs.getFloat("avgCost");
            }
        }catch (SQLException e){
            throw new DataAccesException("Erreur de récupération des coûts moyen de réparation",e);
        }


        return result;*/
    }


}
