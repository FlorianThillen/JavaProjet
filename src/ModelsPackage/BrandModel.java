package ModelsPackage;

public class BrandModel {
    private String name;
    private int warrantyDuration;

    public BrandModel(String name, int warrantyDuration) {
        setName(name);
        setWarrantyDuration(warrantyDuration);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWarrantyDuration() {
        return warrantyDuration;
    }
    public void setWarrantyDuration(int warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
    }
}
