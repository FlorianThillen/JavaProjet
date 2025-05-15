package ModelsPackage;

public class StationBikeNbModel {
    private String stationName;
    private int bikeCount;
    private String status;

    public StationBikeNbModel(String stationName, int bikeCount,String status){
        setBikeCount(bikeCount);
        setStationName(stationName);
        setStatus(status);
    }

// setter
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setBikeCount(int bikeCount) {
        this.bikeCount = bikeCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }
// getter
    public String getStationName() {
        return stationName;
    }

    public int getBikeCount() {
        return bikeCount;
    }

    public String getStatus() {
        return status;
    }
}
