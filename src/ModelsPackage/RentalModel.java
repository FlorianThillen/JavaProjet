package ModelsPackage;

import java.util.Date;

public class RentalModel {
    private int rentalId;
    private Date startDate;
    private Date returnDate;
    private String comment;
    private boolean hadIssue;
    private SubscriptionModel subscription;
    private BikeModel bike;

    public RentalModel(int rentalId, Date startDate, Date returnDate, String comment, boolean hadIssue, SubscriptionModel subscription, BikeModel bike) {
        setRentalId(rentalId);
        setStartDate(startDate);
        setReturnDate(returnDate);
        setComment(comment);
        setHadIssue(hadIssue);
        setSubscription(subscription);
        setBike(bike);
    }
    public int getRentalId() {
        return rentalId;
    }
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public boolean isHadIssue() {
        return hadIssue;
    }
    public void setHadIssue(boolean hadIssue) {
        this.hadIssue = hadIssue;
    }
    public SubscriptionModel getSubscription() {
        return subscription;
    }
    public void setSubscription(SubscriptionModel subscription) {
        this.subscription = subscription;
    }
    public BikeModel getBike() {
        return bike;
    }
    public void setBike(BikeModel bike) {
        this.bike = bike;
    }
}
