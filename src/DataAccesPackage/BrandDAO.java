package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BrandModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BrandDAO {
    public List<BrandModel> getAllBrands() throws DataAccesException {
        ArrayList<BrandModel> brandModels = new ArrayList<BrandModel>();

        Connection connection = SingletonConnection.getInstance();

        String query = """
SELECT * FROM brand;
""";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                brandModels.add(new BrandModel(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DataAccesException("Erreur de récupération des marques", e);
        }
        return brandModels;
    }
}
