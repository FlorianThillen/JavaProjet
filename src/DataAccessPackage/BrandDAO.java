package DataAccessPackage;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.BrandModel;

import java.sql.*;
import java.util.*;

public class BrandDAO {
    public ArrayList<BrandModel> getAllBrands() throws DataAccessException {
        ArrayList<BrandModel> brandModels = new ArrayList<BrandModel>();

        Connection connection = SingletonConnection.getInstance();

        String query = """
                    SELECT * FROM brand
                    """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                brandModels.add(new BrandModel(
                        rs.getString(1),
                        rs.getInt(2)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DataAccessException("Erreur de récupération des marques", e);
        }
        return brandModels;
    }
}
