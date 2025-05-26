package ModelsPackage;

import java.util.Date;

public class RepairModel {
    private int repairId;
    private int cost;
    private java.sql.Date date;
    private RepairStatusModel status;
    private MechanicModel mechanic;
    private BikeModel bike;

    public RepairModel(int repairId, int cost, java.sql.Date date, RepairStatusModel status, MechanicModel mechanic, BikeModel bike) {
        setRepairId(repairId);
        setCost(cost);
        setDate(date);
        setRepairStatus(status);
        setMechanic(mechanic);
        setBike(bike);
    }
    public RepairModel() {};
    public int getRepairId() {
        return repairId;
    }
    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        if (cost < 0) throw new IllegalArgumentException("cost can't be negative");
        this.cost = cost;
    }
    public java.sql.Date getDate() {
        return date;
    }
    public void setDate(java.sql.Date date) {
        this.date = date;
    }
    public RepairStatusModel getStatus() {
        return status;
    }
    public void setRepairStatus(RepairStatusModel status) {
        this.status = status;
    }
    public MechanicModel getMechanic() {
        return mechanic;
    }
    public void setMechanic(MechanicModel mechanic) {
        this.mechanic = mechanic;
    }
    public BikeModel getBike() {
        return bike;
    }
    public void setBike(BikeModel bike) {
        this.bike = bike;
    }
}
