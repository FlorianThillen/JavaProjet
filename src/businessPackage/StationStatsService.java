package businessPackage;
import DataAccesPackage.StationStatsDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.StationBikeNbModel;

import java.util.ArrayList;
import java.util.List;
public class StationStatsService {
    private final StationStatsDAO stationStatsDAO;

    public StationStatsService(){
        this.stationStatsDAO = new StationStatsDAO();
    }
    public List<StationBikeNbModel> getStationsStatus(int min , int max) throws DataAccesException{
        List<StationBikeNbModel> rawData = stationStatsDAO.getBikeCountPerStation();
        List<StationBikeNbModel> result = new ArrayList<>();

        for (StationBikeNbModel item : rawData){
            String status;
            if (item.getBikeCount() < min){
                status = "LOW";
            }else if (item.getBikeCount() > max){
                status = "HIGH";
            }else {
                status = "OK";
            }
            result.add(new StationBikeNbModel(item.getStationName(),item.getBikeCount(),status));
        }

        //pour trier en fonction du statut
        result.sort((s1, s2) -> s1.getStatus().compareTo(s2.getStatus()));

        return result;
    }
}
