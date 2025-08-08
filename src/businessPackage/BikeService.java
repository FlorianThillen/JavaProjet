package BusinessPackage;
import DataAccessPackage.BikeDAO;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;

import java.util.List;

public class BikeService {
    private final BikeDAO bikeDAO;

    public BikeService(){
        this.bikeDAO = new BikeDAO();
    }

    public void insertBike(BikeModel bikeModel) throws DataAccessException {
        bikeDAO.insert(bikeModel);
    }

    public List<BikeModel> getAllBikes() throws DataAccessException {
        return bikeDAO.findAll();
    }

    public void deleteBike(int serialNumber) throws DataAccessException {
        bikeDAO.deleteBySerialNumber(serialNumber);
    }

    public void updateBike(BikeModel bike) throws DataAccessException {
        bikeDAO.update(bike);
    }
}
