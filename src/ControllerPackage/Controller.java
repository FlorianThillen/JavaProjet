package ControllerPackage;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.*;
import DataAccessPackage.RepairDAO;

import BusinessPackage.*;

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
    private final BrandService brandService = new BrandService();
    private final StationService stationService = new StationService();

    public ArrayList<BikeModel> getBikes(String brandName) throws DataAccessException {
        return new BrandBikesService().getBikesFromBrand(brandName);
    }

    public List<RentalDateSearchModel> getRentalsBetweenDates(Date start, Date end) throws IllegalArgumentException {
        if (end.before(start)) throw new IllegalArgumentException("End date is before start date.");

        return rentalSearchService.searchRentals(start,end);
    }
    // tache métier 2 ,1/3
    public List<UnpaidSubscriptionModel> getUnpaidSubscriptions() throws DataAccessException {
        return unpaidSubscriptionService.getUnpaidSubscription();
    }
    // tache métier 2 ,2/3
    public List<StationBikeNbModel> getStationsStatus(int min,int max) throws DataAccessException, IllegalArgumentException {
        if (min < 0) throw new IllegalArgumentException("Requested minimum should be 0 or above.");
        if (max < 1) throw new IllegalArgumentException("Maximum should be 1 or above.");
        if (max <= min) throw new IllegalArgumentException("Maximum should be above minimum.");

        return stationStatsService.getStationsStatus(min, max);
    }
    // tache métier 2 ,3/3
    public List<BrandRepairCostModel> getAverageRepairCostPeBrand() throws DataAccessException {
        return brandRepairCostService.getAverageRepairCostPerBrand();
    }

    //==== crud bikes
    public void insertBike(BikeModel bikeModel) throws DataAccessException {
        bikeService.insertBike(bikeModel);
    }
    public List<BikeModel> getAllBikes() throws DataAccessException {
        return bikeService.getAllBikes();
    }
    public List<BrandModel> getAllBrands() throws DataAccessException {
        return brandService.getAllBrands();
    }

    public List<StationModel> getAllStations() throws DataAccessException {
        return stationService.getAllStations();
    }
    public void deleteBike(int serialNumber) throws DataAccessException {
        bikeService.deleteBike(serialNumber);
    }
    public void updateBike(BikeModel bikeModel, int originalSerialNumber) throws DataAccessException {
        if (originalSerialNumber < 1)
            throw new DataAccessException("originalSerialNumber should be positive.");
        if (bikeModel == null)
            throw new DataAccessException("bikeModel is null.");
        else if (bikeModel.getSerialNumber() == originalSerialNumber)
            throw new DataAccessException("Both serial number are identical.");

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

    public List<RepairStatusModel> getAllRepairStatus() throws DataAccessException {
        return new RepairDAO().getAllStatus();
    }

    public Vector<String> getAllBrandNames() throws DataAccessException {
        return new BrandBikesService().getAllBrandNames();
    }

    // === New Repair
    public ArrayList<LocalityModel> getLocalities() throws DataAccessException {
        return new NewRepairService().getLocalities();
    }

    public ArrayList<StationModel> getStationsFromLocality(LocalityModel locality) throws DataAccessException {
        return new NewRepairService().getStationsFromLocality(locality);
    }

    public ArrayList<MechanicModel> getAllMechanics() throws DataAccessException {
        return new NewRepairService().getAllMechanics();
    }

    // Crud Rental
    public List<RentalModel> getAllRentals() throws DataAccessException {
        return rentalService.getAllRentals();
    }

    public RentalModel getRentalById(int id) throws DataAccessException {
        return rentalService.getRentalById(id);
    }

    public void insertRental(RentalModel rental) throws DataAccessException {
        rentalService.insertRental(rental);
    }

    public void updateRental(RentalModel rental) throws DataAccessException {
        rentalService.updateRental(rental);
    }

    public void deleteRental(int id) throws DataAccessException {
        rentalService.deleteRental(id);
    }

    public ArrayList<BikeModel> getBikesFromStation(StationModel station) throws DataAccessException {
        return new NewRepairService().getBikesFromStation(station);
    }

    public void saveNewRepair(RepairModel repair) throws DataAccessException {
        new NewRepairService().saveNewRepair(repair);
    }
}
