package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.UnpaidSubscriptionModel;
import java.sql.*;
import java.util.ArrayList;

import java.sql.Connection;
import java.util.List;

public class UnpaidSubscriptionDAO {
    public List<UnpaidSubscriptionModel> getUnpaidSubscription() throws DataAccesException {
        Connection connection = SingletonConnection.getInstance();
        ArrayList<UnpaidSubscriptionModel> result = new ArrayList<>();

        String query = "SELECT p.firstname, p.lastname, p.email " +
                "FROM person p " +
                "JOIN member m ON p.id_person = m.person_id " +
                "JOIN subscription s ON s.user_id = m.id_member " +
                "WHERE s.subscription_paid = false ";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");

                UnpaidSubscriptionModel model = new UnpaidSubscriptionModel(firstName, lastName, email);
                result.add(model);
            }

        } catch (SQLException e){
            throw new DataAccesException("Erreur lors de la récuperation des abonnement non reglé",e);
        }


        return result;
    }
}
