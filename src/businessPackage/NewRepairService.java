package businessPackage;

import DataAccesPackage.LocalityDAO;
import DataAccesPackage.StationDAO;
import ExceptionsPackage.DataAccesException;
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
}
