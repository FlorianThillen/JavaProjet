package DataAccessPackage;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.MechanicModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MechanicDAO {
    public ArrayList<MechanicModel> selectAllMechanics() throws DataAccessException {
        ArrayList<MechanicModel> mechanics = new ArrayList<MechanicModel>();

        Connection connection = SingletonConnection.getInstance();

        String query = """
                    SELECT * FROM mechanic
                    """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                mechanics.add(new MechanicModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DataAccessException("Erreur de récupération des mecaniciens", e);
        }
        return mechanics;
    }
}
