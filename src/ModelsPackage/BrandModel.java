package ModelsPackage;

public class BrandModel {
    private String name;
    private int warrantyDuration;

    public BrandModel(String name, int warrantyDuration) {
        setName(name);
        setWarrantyDuration(warrantyDuration);
    }
    public BrandModel() {};

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
        if (warrantyDuration < 0) throw new IllegalArgumentException("warrantyDuration should be 0 or higher.");
        this.warrantyDuration = warrantyDuration;
    }

    //pour recevoir le nom en cas d appel du model
    @Override
    public String toString() {
        return getName();
    }
}
