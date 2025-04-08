package ModelsPackage;

public class MechanicModel {
    private int BadgeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public MechanicModel(int badgeId, String firstName, String lastName, String email, String phoneNumber) {
        setBadgeId(badgeId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }
    public int getBadgeId() {
        return BadgeId;
    }
    public void setBadgeId(int badgeId) {
        BadgeId = badgeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
