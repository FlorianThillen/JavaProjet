package ModelsPackage;

public class StationModel {
    private int stationNumber;
    private String name;
    private String street;
    private int streetNumber;
    private LocalityModel locality;

    public StationModel(int stationNumber, String name, String street, int streetNumber, LocalityModel locality) {
        setStationNumber(stationNumber);
        setName(name);
        setStreet(street);
        setStreetNumber(streetNumber);
        setLocality(locality);
    }
    public StationModel() {};

    public int getStationNumber() {
        return stationNumber;
    }
    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(int streetNumber) {
        if (streetNumber < 1) throw new IllegalArgumentException("streetNumber must be an int above 0.");
        this.streetNumber = streetNumber;
    }
    public LocalityModel getLocality() {
        return locality;
    }
    public void setLocality(LocalityModel locality) {
        this.locality = locality;
    }

    //pour recevoir le nom en cas d appel du model
    @Override
    public String toString() {
        return getName();
    }
}
