package ModelsPackage;

import java.util.Date;

public class RepairSearchModel {
    private Date date;
    private double cost;
    private String serialNumber;
    private boolean isElectric;
    private int nbKilometer;
    private String mechanicFirstname;
    private String mechanicLastname;

    public RepairSearchModel(Date date, double cost, String serialNumber, boolean isElectric,
                             int nbKilometer, String mechanicFirstname, String mechanicLastname) {
        setDate(date);
        setCost(cost);
        setSerialNumber(serialNumber);
        setElectric(isElectric);
        setNbKilometer(nbKilometer);
        setMechanicFirstname(mechanicFirstname);
        setMechanicLastname(mechanicLastname);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean isElectric) {
        this.isElectric = isElectric;
    }

    public int getNbKilometer() {
        return nbKilometer;
    }

    public void setNbKilometer(int nbKilometer) {
        this.nbKilometer = nbKilometer;
    }

    public String getMechanicFirstname() {
        return mechanicFirstname;
    }

    public void setMechanicFirstname(String mechanicFirstname) {
        this.mechanicFirstname = mechanicFirstname;
    }

    public String getMechanicLastname() {
        return mechanicLastname;
    }

    public void setMechanicLastname(String mechanicLastname) {
        this.mechanicLastname = mechanicLastname;
    }
}
