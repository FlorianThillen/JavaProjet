package ModelsPackage;

import java.util.Date;

public class SubscriptionModel {
    private int cardNumber;
    private int price;
    private Date date;
    private boolean cautionPayed;
    private boolean subscriptionPayed;

    public SubscriptionModel(int cardNumber, int price, Date date, boolean cautionPayed, boolean subscriptionPayed) {
        setCardNumber(cardNumber);
        setPrice(price);
        setDate(date);
        setCautionPayed(cautionPayed);
        setSubscriptionPayed(subscriptionPayed);
    }

    public SubscriptionModel() { }

    public int getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public boolean isCautionPayed() {
        return cautionPayed;
    }
    public void setCautionPayed(boolean cautionPayed) {
        this.cautionPayed = cautionPayed;
    }
    public boolean isSubscriptionPayed() {
        return subscriptionPayed;
    }
    public void setSubscriptionPayed(boolean subscriptionPayed) {
        this.subscriptionPayed = subscriptionPayed;
    }
}
