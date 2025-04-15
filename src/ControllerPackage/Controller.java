package ControllerPackage;

import businessPackage.RentalService;
import ModelsPackage.RentalDateSearchModel;
import ModelsPackage.BikeModel;

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


}
