package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StationDAO {
    public ArrayList<StationModel> selectStationsFromLocality(LocalityModel locality) throws DataAccesException {
        ArrayList<StationModel> stations = new ArrayList<>();

        Connection connection = SingletonConnection.getInstance();

        String query = """
                SELECT * FROM station s
                WHERE s.postal_code = ? AND s.local_name = ?;
                """;
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, locality.getPostalCode());
            stmt.setString(2, locality.getName());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                stations.add(new StationModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        null
                ));
            }
        } catch (SQLException e) {
            String error_message = String.format("Erreur lors du chargement des stations de %s", locality.getName());
            throw new DataAccesException(error_message, e);
        }
        return stations;
    }
}
