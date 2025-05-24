package ModelsPackage;

import java.util.Date;

public class BikeModel {
    private int serialNumber;
    private boolean electric;
    private Date buyingDate;
    private int batteryLevel;
    private int nbKilometers;
    private BrandModel brand;
    private StationModel station;

    public BikeModel(int serialNumber, boolean isElectric, Date buyingDate, int batteryLevel, int nbKilometers, BrandModel brand,StationModel station) {
        setSerialNumber(serialNumber);
        setElectric(isElectric);
        setBuyingDate(buyingDate);
        setBatteryLevel(batteryLevel);
        setNbKilometers(nbKilometers);
        setBrand(brand);
        setStation(station);
    }

    public BikeModel() {

    }

    public int getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
    public boolean isElectric() {
        return electric;
    }
    public void setElectric(boolean electric) {
        this.electric = electric;
    }
    public java.sql.Date getBuyingDate() {
        return (java.sql.Date) buyingDate;
    }
    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }
    public int getBatteryLevel() {
        return batteryLevel;
    }
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
    public int getNbKilometers() {
        return nbKilometers;
    }
    public void setNbKilometers(int nbKilometers) {
        this.nbKilometers = nbKilometers;
    }
    public BrandModel getBrand() {
        return brand;
    }
    public void setBrand(BrandModel brand) {
        this.brand = brand;
    }

    public void setStation(StationModel station) {
        this.station = station;
    }

    public StationModel getStation() {
        return station;
    }

    @Override
    public String toString() {
        return Integer.toString(getSerialNumber());
    }
}
