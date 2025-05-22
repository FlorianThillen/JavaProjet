package businessPackage;
import DataAccesPackage.BikeDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.BrandModel;
import ModelsPackage.StationModel;

import java.util.List;

public class BikeService {
    private final BikeDAO bikeDAO;

    public BikeService(){
        this.bikeDAO=new BikeDAO();
    }

    public void insertBike(BikeModel bikeModel)throws DataAccesException{
        bikeDAO.insert(bikeModel);
    }

    public List<BikeModel> getAllBikes()throws DataAccesException{
        return bikeDAO.findAll();
    }
    //---
    /*
    public List<BrandModel> getAllBrands() throws DataAccesException {
        return bikeDAO.getAllBrands();
    }


    public List<StationModel> getAllStations() throws DataAccesException {
        return bikeDAO.getAllStations();
    }
    */
    //---
    public void deleteBike(int serialNumber) throws DataAccesException {
        bikeDAO.deleteBySerialNumber(serialNumber);
    }
    public void updateBike(BikeModel bike, int originalSerialNumber) throws DataAccesException {
        bikeDAO.update(bike, originalSerialNumber);
    }


}
