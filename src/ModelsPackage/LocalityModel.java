package ModelsPackage;

public class LocalityModel {
    private int postalCode;
    private String name;

    public LocalityModel(int postalCode, String name) {
        setPostalCode(postalCode);
        setName(name);
    }
    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
