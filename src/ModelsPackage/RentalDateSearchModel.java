package ModelsPackage;

import java.util.Date;

public class RentalDateSearchModel {
    private int rentalId;
    private Date startDate;
    private int bikesSerialNumber;
    private Date bikeBuyingDate;
    private String brandName;
    private int warrantyDuration;
    private String stationName;

    public RentalDateSearchModel(int rentalId, Date startDate, int bikeSerialNumber, Date bikeBuyingDate,
                                 String brandName, int warrantyDuration, String stationName){
        setRentalId(rentalId);
        setStartDate(startDate);
        setBikesSerialNumber(bikeSerialNumber);
        setBikeBuyingDate(bikeBuyingDate);
        setBrandName(brandName);
        setWarrantyDuration(warrantyDuration);
        setStationName(stationName);

    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setBikesSerialNumber(int bikesSerialNumber) {
        this.bikesSerialNumber = bikesSerialNumber;
    }

    public void setBikeBuyingDate(Date bikeBuyingDate) {
        this.bikeBuyingDate = bikeBuyingDate;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setWarrantyDuration(int warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Date getBikeBuyingDate() {
        return bikeBuyingDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getBikesSerialNumber() {
        return bikesSerialNumber;
    }

    public int getRentalId() {
        return rentalId;
    }

    public int getWarrantyDuration() {
        return warrantyDuration;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getStationName() {
        return stationName;
    }
}
