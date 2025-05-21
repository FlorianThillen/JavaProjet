package businessPackage;

import DataAccesPackage.BikeDAO;
import DataAccesPackage.LocalityDAO;
import DataAccesPackage.StationDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import java.util.ArrayList;

public class NewRepairService {
    public ArrayList<LocalityModel> getLocalities() throws DataAccesException {
        return new LocalityDAO().selectAllLocalities();
    }

    public ArrayList<StationModel> getStationsFromLocality(LocalityModel locality) throws DataAccesException {
        return new StationDAO().selectStationsFromLocality(locality);
    }

    public ArrayList<BikeModel> getBikesFromStation(StationModel station) throws DataAccesException {
        return new BikeDAO().getBikesFromStation(station);
    }
}
