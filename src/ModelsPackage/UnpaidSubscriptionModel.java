package ModelsPackage;

import java.security.PublicKey;

public class UnpaidSubscriptionModel {
    private String firstName;
    private String lastName;
    private String email;

    public UnpaidSubscriptionModel(String firstName,String lastName, String email){
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName(){
        return getFirstName()+" "+getLastName();
    }


}
