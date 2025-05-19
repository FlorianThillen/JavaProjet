package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BrandModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandDAO {
    ArrayList<BrandModel> fetchAllBrands() throws DataAccesException {
        ArrayList<BrandModel> brandModels = new ArrayList<BrandModel>();

        Connection connection = SingletonConnection.getInstance();

        String query = "";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

        } catch (SQLException e) {
            throw new DataAccesException("Erreur de récupération des marques", e);
        }
        return brandModels;
    }
}
