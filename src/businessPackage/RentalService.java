package businessPackage;

import DataAccesPackage.RentalDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.RentalModel;

import java.util.List;


public class RentalService {
    private final RentalDAO rentalDAO;

    public RentalService(){
        this.rentalDAO =new RentalDAO();
    }

    public List<RentalModel> getAllRentals() throws DataAccesException {
        return rentalDAO.getAllRentals();
    }

    public RentalModel getRentalById(int id) throws DataAccesException {
        return rentalDAO.getRentalById(id);
    }

    public void insertRental(RentalModel rental) throws DataAccesException {
        rentalDAO.insertRental(rental);
    }

    public void updateRental(RentalModel rental) throws DataAccesException {
        rentalDAO.updateRental(rental);
    }

    public void deleteRental(int id) throws DataAccesException {
        rentalDAO.deleteRental(id);
    }
}
