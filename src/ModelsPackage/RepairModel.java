package ModelsPackage;

import java.util.Date;

public class RepairModel {
    private int repairId;
    private int cost;
    private Date date;
    private RepairStatusModel status;
    private MechanicModel mechanic;
    private BikeModel bike;

    public RepairModel(int repairId, int cost, Date date, RepairStatusModel status, MechanicModel mechanic, BikeModel bike) {
        setRepairId(repairId);
        setCost(cost);
        setDate(date);
        setRepairStatus(status);
        setMechanic(mechanic);
        setBike(bike);
    }
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
        this.cost = cost;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
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
