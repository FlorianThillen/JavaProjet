package DataAccesPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.UnpaidSubscriptionModel;
import java.sql.*;
import java.util.ArrayList;

import java.sql.Connection;
import java.util.List;

public class UnpaidSubscriptionDAO {
    //private final Connection connection;

    public UnpaidSubscriptionDAO(){
        //this.connection=SingletonConnection.getInstance();
    }

    public List<UnpaidSubscriptionModel> getUnpaidSubscription() throws DataAccesException {
        List<UnpaidSubscriptionModel> fakeList = new ArrayList<>();
        fakeList.add(new UnpaidSubscriptionModel("Alice", "Dupont", "alice@mail.be"));
        fakeList.add(new UnpaidSubscriptionModel("Jean", "Martin", "jean@mail.be"));
        return fakeList;
        /*
        ArrayList<UnpaidSubscriptionModel> result = new ArrayList<>();

        String query = "SELECT p.firstName, p.lastName, p.email " +
                "FROM Person p " +
                "JOIN Member m ON p.idPerson = m.personId " +
                "JOIN Subscription s ON s.userId = m.idMember " +
                "WHERE s.subscriptionPaid = false ";

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

        */
    }
}
