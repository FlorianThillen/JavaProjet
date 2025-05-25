package DataAccessPackage;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.LocalityModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocalityDAO {
    public ArrayList<LocalityModel> selectAllLocalities() throws DataAccessException {
        ArrayList<LocalityModel> localityModels = new ArrayList<LocalityModel>();

        Connection connection = SingletonConnection.getInstance();

        String query = """
                    SELECT * FROM localite
                    """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                localityModels.add(new LocalityModel(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DataAccessException("Erreur de récupération des localités", e);
        }
        return localityModels;
    }
}
