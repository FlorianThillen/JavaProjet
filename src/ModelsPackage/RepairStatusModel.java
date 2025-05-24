package ModelsPackage;

public class RepairStatusModel {

    private String status;

    public RepairStatusModel(String status) {
        setStatus(status);
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getStatus();
    }
}
