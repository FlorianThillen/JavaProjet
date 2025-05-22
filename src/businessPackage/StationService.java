package businessPackage;

import DataAccesPackage.RentalDAO;
import DataAccesPackage.StationDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.StationModel;

import java.util.List;

public class StationService {
    private final StationDAO stationDAO;

    public StationService(){
        this.stationDAO =new StationDAO();
    }


    public List<StationModel> getAllStations() throws DataAccesException {
        return stationDAO.getAllStations();
    }

}
