package BusinessPackage;

import DataAccessPackage.StationDAO;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.StationModel;

import java.util.List;

public class StationService {
    private final StationDAO stationDAO;

    public StationService(){
        this.stationDAO =new StationDAO();
    }


    public List<StationModel> getAllStations() throws DataAccessException {
        return stationDAO.getAllStations();
    }

}
