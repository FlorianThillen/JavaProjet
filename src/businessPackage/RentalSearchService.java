package BusinessPackage;

import ModelsPackage.RentalDateSearchModel;
import DataAccessPackage.RentalSearchDAO;

import java.sql.Date;
import java.util.List;


public class RentalSearchService {
    private final RentalSearchDAO rentalSearchDAO;

    public RentalSearchService(){
        this.rentalSearchDAO = new RentalSearchDAO();
    }

    public List<RentalDateSearchModel> searchRentals(Date startDate, Date endDate){
        if(startDate.after(endDate)){
            throw new IllegalArgumentException("! Date de debut apres la date de fin !"); // à modifier pour appeler une exepction à créer dans l exception package
        }
        return rentalSearchDAO.getRentalsBewteenDates(startDate,endDate);
    }
}
