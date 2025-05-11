package ControllerPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.*;
import DataAccesPackage.RepairDataAccess;

import businessPackage.RentalService;
import businessPackage.UnpaidSubscriptionService;


import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.sql.Date;

public class Controller {
    private RentalService rentalService = new RentalService();
    private final UnpaidSubscriptionService unpaidSubscriptionService = new UnpaidSubscriptionService();

    public Vector<BikeModel> getBikes(String brandName) {
        return new Vector<BikeModel>();
    }

    public List<RentalDateSearchModel> getRentalsBetweenDates(Date start,Date end){
        return rentalService.searchRentals(start,end);
    }
    public List<UnpaidSubscriptionModel> getUnpaidSubscriptions() throws DataAccesException {
        return unpaidSubscriptionService.getUnpaidSubscription();
    }
    public List<RepairSearchModel> getRepairsByStatus(String statusLabel) {
        try {
            return new RepairDataAccess().getRepairsByStatus(statusLabel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RepairStatusModel> getAllRepairStatus() {
        return new RepairDataAccess().getAllStatus();
    }
}
