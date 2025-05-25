package DataAccessPackage;

import ModelsPackage.RentalDateSearchModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RentalSearchDAO {
    public List<RentalDateSearchModel> getRentalsBewteenDates(Date startDate,Date endDate){
        Connection connection = SingletonConnection.getInstance();

        List<RentalDateSearchModel> results = new ArrayList<>();
        String sql = "SELECT rental.id, rental.start_date, bike.serial_number, bike.buying_date, brand.name AS brand, brand.waranty_duration, station.name AS station " +
                "FROM rental " +
                "JOIN bike ON rental.bike_id = bike.serial_number " +
                "JOIN brand ON bike.brand_name = brand.name " +
                "JOIN station ON bike.station_id = station.station_number " +
                "WHERE rental.start_date BETWEEN ? AND ?";

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
