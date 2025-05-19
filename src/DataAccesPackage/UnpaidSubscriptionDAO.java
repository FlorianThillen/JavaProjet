package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.UnpaidSubscriptionModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnpaidSubscriptionDAO {
    public List<UnpaidSubscriptionModel> getUnpaidSubscription() throws DataAccesException {
        Connection connection = SingletonConnection.getInstance();
        List<UnpaidSubscriptionModel> result = new ArrayList<>();

        String query = "SELECT m.first_name, m.last_name, m.email " +
                "FROM member m " +
                "JOIN subscription s ON m.sub_id = s.card_number " +
                "WHERE s.subscription_paid = false";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString(1);
                String lastName = rs.getString(2);
                String email = rs.getString(3);

                result.add(new UnpaidSubscriptionModel(firstName, lastName, email));
            }
        } catch (SQLException e) {
            throw new DataAccesException("Erreur lors de la récupération des abonnements non réglés", e);
        }

        return result;
    }
}
