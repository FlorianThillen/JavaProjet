package BusinessPackage;

import DataAccessPackage.RentalDAO;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.RentalModel;

import java.util.List;


public class RentalService {
    private final RentalDAO rentalDAO;

    public RentalService(){
        this.rentalDAO =new RentalDAO();
    }

    public List<RentalModel> getAllRentals() throws DataAccessException {
        return rentalDAO.getAllRentals();
    }

    public RentalModel getRentalById(int id) throws DataAccessException {
        return rentalDAO.getRentalById(id);
    }

    public void insertRental(RentalModel rental) throws DataAccessException {
        rentalDAO.insertRental(rental);
    }

    public void updateRental(RentalModel rental) throws DataAccessException {
        rentalDAO.updateRental(rental);
    }

    public void deleteRental(int id) throws DataAccessException {
        rentalDAO.deleteRental(id);
    }
}
