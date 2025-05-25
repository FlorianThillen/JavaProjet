package BusinessPackage;


import DataAccessPackage.UnpaidSubscriptionDAO;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.UnpaidSubscriptionModel;

import java.util.List;

public class UnpaidSubscriptionService {

    private final UnpaidSubscriptionDAO dao;

    public UnpaidSubscriptionService(){
        this.dao=new UnpaidSubscriptionDAO();
    }
    public List<UnpaidSubscriptionModel> getUnpaidSubscription()throws DataAccessException {
        return dao.getUnpaidSubscription();
    }
}
