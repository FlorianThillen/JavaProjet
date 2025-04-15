package ControllerPackage;

import ModelsPackage.RepairSearchModel;
import DataAccesPackage.RepairDataAccess;

import ModelsPackage.RepairStatusModel;
import businessPackage.RentalService;
import ModelsPackage.RentalDateSearchModel;
import ModelsPackage.BikeModel;


import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.sql.Date;

public class Controller {
    private RentalService rentalService = new RentalService();

    public Vector<BikeModel> getBikes(String brandName) {
        return new Vector<BikeModel>();
    }

    public List<RentalDateSearchModel> getRentalsBetweenDates(Date start,Date end){
        return rentalService.searchRentals(start,end);
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
