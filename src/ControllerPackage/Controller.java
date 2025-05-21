package ControllerPackage;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.*;
import DataAccesPackage.RepairDAO;

import businessPackage.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.sql.Date;

public class Controller {
    private RentalService rentalService = new RentalService();
    private final UnpaidSubscriptionService unpaidSubscriptionService = new UnpaidSubscriptionService();
    private final StationStatsService stationStatsService = new StationStatsService();
    private final BrandRepairCostService brandRepairCostService = new BrandRepairCostService();
    private final BikeService bikeService = new BikeService();

    public ArrayList<BikeModel> getBikes(String brandName) throws DataAccesException {
        return new BrandBikesService().getBikesFromBrand(brandName);
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

    //==== crud bikes
    public void insertBike(BikeModel bikeModel)throws DataAccesException{
        bikeService.insertBike(bikeModel);
    }
    public List<BikeModel> getAllBikes()throws DataAccesException{
        return bikeService.getAllBikes();
    }
    public List<BrandModel> getAllBrands() throws DataAccesException {
        return bikeService.getAllBrands();
    }

    public List<StationModel> getAllStations() throws DataAccesException {
        return bikeService.getAllStations();
    }
    public void deleteBike(int serialNumber)throws DataAccesException{
        bikeService.deleteBike(serialNumber);
    }
    public void updateBike(BikeModel bikeModel,int originalSerialNumber)throws DataAccesException{
        bikeService.updateBike(bikeModel,originalSerialNumber);
    }

    // ====

    public List<RepairSearchModel> getRepairsByStatus(String statusLabel) {
        try {
            return new RepairDAO().getRepairsByStatus(statusLabel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RepairStatusModel> getAllRepairStatus() throws DataAccesException {
        return new RepairDAO().getAllStatus();
    }

    public Vector<String> getAllBrandNames() throws DataAccesException {
        return new BrandBikesService().getAllBrandNames();
    }

    // === New Repair

    public String[] getLocalityNames() throws DataAccesException {
        return new NewRepairService().getLocalityNames();
    }
}
