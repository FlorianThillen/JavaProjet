package ControllerPackage;

import DataAccesPackage.BikeDAO;
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
    private RentalSearchService rentalSearchService = new RentalSearchService();
    private final UnpaidSubscriptionService unpaidSubscriptionService = new UnpaidSubscriptionService();
    private final StationStatsService stationStatsService = new StationStatsService();
    private final BrandRepairCostService brandRepairCostService = new BrandRepairCostService();
    private final BikeService bikeService = new BikeService();
    private final RentalService rentalService = new RentalService();

    public ArrayList<BikeModel> getBikes(String brandName) throws DataAccesException {
        return new BrandBikesService().getBikesFromBrand(brandName);
    }

    public List<RentalDateSearchModel> getRentalsBetweenDates(Date start,Date end){
        return rentalSearchService.searchRentals(start,end);
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

    public ArrayList<LocalityModel> getLocalities() throws DataAccesException {
        return new NewRepairService().getLocalities();
    }

    public ArrayList<StationModel> getStationsFromLocality(LocalityModel locality) throws DataAccesException {
        return new NewRepairService().getStationsFromLocality(locality);
    }

<<<<<<< Updated upstream
    // Crud Rental
    public List<RentalModel> getAllRentals() throws DataAccesException {
        return rentalService.getAllRentals();
    }

    public RentalModel getRentalById(int id) throws DataAccesException {
        return rentalService.getRentalById(id);
    }

    public void insertRental(RentalModel rental) throws DataAccesException {
        rentalService.insertRental(rental);
    }

    public void updateRental(RentalModel rental) throws DataAccesException {
        rentalService.updateRental(rental);
    }

    public void deleteRental(int id) throws DataAccesException {
        rentalService.deleteRental(id);
    }

=======
    public ArrayList<BikeModel> getBikesFromStation(StationModel station) throws DataAccesException {
        return new BikeDAO().getBikesFromStation(station);
    }
>>>>>>> Stashed changes
}
