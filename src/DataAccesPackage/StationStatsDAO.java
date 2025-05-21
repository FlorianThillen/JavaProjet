package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationBikeNbModel;
import ModelsPackage.StationModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class StationStatsDAO {
    public List<StationBikeNbModel> getBikeCountPerStation() throws DataAccesException {
        Connection connection = SingletonConnection.getInstance();
        List<StationBikeNbModel> result = new ArrayList<>();
        String query = "SELECT s.name AS station_name, COUNT(b.serial_number) AS bike_count " +
                "FROM station s " +
                "LEFT JOIN bike b ON b.station_id = s.station_number " +
                "GROUP BY s.name";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String stationName = rs.getString("station_name");
                int bikeCount = rs.getInt("bike_count");

                result.add(new StationBikeNbModel(stationName, bikeCount));
            }
        } catch (SQLException e){
            throw new DataAccesException("Erreur de récuperation du nombre de vélos par station",e);
        }

        return result;
    }
}
