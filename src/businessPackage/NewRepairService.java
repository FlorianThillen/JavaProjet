package businessPackage;

import DataAccesPackage.*;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.*;

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

    public ArrayList<MechanicModel> getAllMechanics() throws DataAccesException {
        return new MechanicDAO().selectAllMechanics();
    }

    public void saveNewRepair(RepairModel repair) throws DataAccesException {
        new RepairDAO().insert(repair);
    }
}
