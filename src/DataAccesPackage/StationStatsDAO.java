package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.StationBikeNbModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class StationStatsDAO {


    //private final Connection connection;

    public StationStatsDAO(){
    //    this.connection= SingletonConnection.getInstance();
    }

    public List<StationBikeNbModel> getBikeCountPerStation() throws DataAccesException {
        List<StationBikeNbModel> result = new ArrayList<>();

        // données pour test
        result.add(new StationBikeNbModel("Namur Centre", 3, null));
        result.add(new StationBikeNbModel("Gare Nord", 7, null));
        result.add(new StationBikeNbModel("Université", 12, null));

        return result;
/*
        String query ="SELECT s.name AS station_name, COUNT (b.id_bike) AS bike_count" +
                "FROM station s" +
                "LEFT JOIN bike b ON b.station_id = s.id_station" +
                "GROUP BY s.name";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String name = rs.getString("station_name");
                int count = rs.getInt("bike_count");
            }
        } catch (SQLException e){
            throw new DataAccesException("Erreur de récuperation du nombre de vélos par station",e);
        }

        return result;
*/
    }
}
