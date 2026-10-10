package objectData;

// datele din checkoutData.xml; XmlDataLoader le completeaza, deci numele campurilor = numele tag-urilor
// fara setteri: valorile le pune XmlDataLoader, testele doar le citesc prin getteri
public class CheckoutObject {

    private String productName;
    private String comment;
    private String nameOnCard;
    private String cardNumber;
    private String cvc;
    private String expiryMonth;
    private String expiryYear;

    public String getProductName() {
        return productName;
    }

    public String getComment() {
        return comment;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }
}
