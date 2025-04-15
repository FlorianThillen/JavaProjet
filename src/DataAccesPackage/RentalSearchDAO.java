package DataAccesPackage;

import ModelsPackage.RentalDateSearchModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RentalSearchDAO {
    public List<RentalDateSearchModel> getRentalsBewteenDates(Date startDate,Date endDate){
        Connection connection = SingletonConnection.getInstance();

        List<RentalDateSearchModel> results = new ArrayList<>();
        String sql = "select  rental.id, rental.startDate, bike.serialNumber, bike.buyingDate, brand.name as brand, station.name as station " +
                "from    rental " +
                "join    bike on rental.bikeId = bike.serialNumber " +
                "join    brand on bike.brandName = brand.name" +
                "join    station on  bike.stationId = station.stationNumber " +
                "where   rental.startDate BETWEEN ? and ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1,startDate);
            stmt.setDate(2,endDate);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                results.add(new RentalDateSearchModel(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7)
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }



        return results;
    }

}
