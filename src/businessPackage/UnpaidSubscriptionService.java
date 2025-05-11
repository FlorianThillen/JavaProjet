package businessPackage;


import DataAccesPackage.UnpaidSubscriptionDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.UnpaidSubscriptionModel;

import java.util.ArrayList;
import java.util.List;

public class UnpaidSubscriptionService {

    private final UnpaidSubscriptionDAO dao;

    public UnpaidSubscriptionService(){
        this.dao=new UnpaidSubscriptionDAO();
    }
    public List<UnpaidSubscriptionModel> getUnpaidSubscription()throws DataAccesException{
        return dao.getUnpaidSubscription();
    }
}
