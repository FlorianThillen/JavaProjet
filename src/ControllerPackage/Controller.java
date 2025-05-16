package ControllerPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.*;
import DataAccesPackage.RepairDataAccess;

import businessPackage.BrandRepairCostService;
import businessPackage.RentalService;
import businessPackage.StationStatsService;
import businessPackage.UnpaidSubscriptionService;


import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.sql.Date;

public class Controller {
    private RentalService rentalService = new RentalService();
    private final UnpaidSubscriptionService unpaidSubscriptionService = new UnpaidSubscriptionService();
    private final StationStatsService stationStatsService = new StationStatsService();
    private final BrandRepairCostService brandRepairCostService = new BrandRepairCostService();

    public Vector<BikeModel> getBikes(String brandName) {
        return new Vector<BikeModel>();
    }

    public List<RentalDateSearchModel> getRentalsBetweenDates(Date start,Date end){
        return rentalService.searchRentals(start,end);
    }
    // tache métier 2 ,1/3
    public List<UnpaidSubscriptionModel> getUnpaidSubscriptions() throws DataAccesException {
        return unpaidSubscriptionService.getUnpaidSubscription();
    }
    // tache métier 2 ,2/3
    public List<StationBikeNbModel> getStationsStatus(int min,int max)throws DataAccesException{
        return stationStatsService.getStationsStatus(min, max);
    }
    // tache métier 2 ,3/3
    public List<BrandRepairCostModel> getAverageRepairCostPeBrand()throws DataAccesException{
        return brandRepairCostService.getAverageRepairCostPerBrand();
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
