package ModelsPackage;

public class BrandRepairCostModel {
    private String brandName;
    private float averageCost;

    public BrandRepairCostModel(String brandName,float averageCost){
        this.brandName=brandName;
        this.averageCost=averageCost;
    }

    public String getBrandName() {
        return brandName;
    }

    public float getAverageCost() {
        return averageCost;
    }
}
