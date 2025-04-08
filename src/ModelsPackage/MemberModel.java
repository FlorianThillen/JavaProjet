package ModelsPackage;

public class MemberModel {
    private int nationalNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean gotDiscount;
    private String street;
    private int streetNumber;
    private MemberModel holder;
    private LocalityModel locality;
    private SubscriptionModel subscription;

    public MemberModel(int nationalNumber, String firstName, String lastName, String email, String phoneNumber, boolean gotDiscount, String street, int streetNumber, MemberModel holder, LocalityModel locality, SubscriptionModel subscription) {
        setNationalNumber(nationalNumber);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setGotDiscount(gotDiscount);
        setStreet(street);
        setStreetNumber(streetNumber);
        setHolder(holder);
        setLocality(locality);
        setSubscription(subscription);
    }
    public int getNationalNumber() {
        return nationalNumber;
    }
    public void setNationalNumber(int nationalNumber) {
        this.nationalNumber = nationalNumber;
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
    public boolean isGotDiscount() {
        return gotDiscount;
    }
    public void setGotDiscount(boolean gotDiscount) {
        this.gotDiscount = gotDiscount;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
    public MemberModel getHolder() {
        return holder;
    }
    public void setHolder(MemberModel holder) {
        this.holder = holder;
    }
    public LocalityModel getLocality() {
        return locality;
    }
    public void setLocality(LocalityModel locality) {
        this.locality = locality;
    }
    public SubscriptionModel getSubscription() {
        return subscription;
    }
    public void setSubscription(SubscriptionModel subscription) {
        this.subscription = subscription;
    }
}
