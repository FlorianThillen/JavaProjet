package BusinessPackage;

import DataAccessPackage.*;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.*;

import java.util.ArrayList;

public class NewRepairService {
    public ArrayList<LocalityModel> getLocalities() throws DataAccessException {
        return new LocalityDAO().selectAllLocalities();
    }

    public ArrayList<StationModel> getStationsFromLocality(LocalityModel locality) throws DataAccessException {
        return new StationDAO().selectStationsFromLocality(locality);
    }

    public ArrayList<BikeModel> getBikesFromStation(StationModel station) throws DataAccessException {
        return new BikeDAO().getBikesFromStation(station);
    }

    public ArrayList<MechanicModel> getAllMechanics() throws DataAccessException {
        return new MechanicDAO().selectAllMechanics();
    }

    public void saveNewRepair(RepairModel repair) throws DataAccessException {
        new RepairDAO().insert(repair);
    }
}
