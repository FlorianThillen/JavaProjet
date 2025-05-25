package DataAccessPackage;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDAO {
    public ArrayList<StationModel> selectStationsFromLocality(LocalityModel locality) throws DataAccessException {
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
                        locality
                ));
            }
        } catch (SQLException e) {
            String error_message = String.format("Erreur lors du chargement des stations de %s", locality.getName());
            throw new DataAccessException(error_message, e);
        }
        return stations;
    }

    public List<StationModel> getAllStations() throws DataAccessException {
        List<StationModel> list = new ArrayList<>();

        Connection connection = SingletonConnection.getInstance();

        String query = """
        SELECT s.station_number, s.name, s.street, s.street_number,
               l.postal_code, l.name AS locality_name
        FROM station s
        JOIN localite l ON s.postal_code = l.postal_code AND s.local_name = l.name
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new StationModel(
                        rs.getInt("station_number"),
                        rs.getString("name"),
                        rs.getString("street"),
                        rs.getInt("street_number"),
                        new LocalityModel(rs.getInt("postal_code"), rs.getString("locality_name"))
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors du chargement des stations", e);
        }

        return list;
    }
}
