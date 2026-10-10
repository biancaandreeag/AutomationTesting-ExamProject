package pages;

import logger.LoggerUtility;
import objectData.CheckoutObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// checkout -> plata -> confirmarea comenzii
public class CheckoutPage extends BasePage {

    @FindBy(css = "#address_delivery .address_firstname")
    private WebElement deliveryName;

    // tot blocul: clasa address_address1 e pusa si pe randul de companie, care e gol
    @FindBy(id = "address_delivery")
    private WebElement deliveryAddressBlock;

    @FindBy(name = "message")
    private WebElement commentField;

    @FindBy(css = "a[href='/payment']")
    private WebElement placeOrderButton;

    @FindBy(css = "input[data-qa='name-on-card']")
    private WebElement nameOnCardField;

    @FindBy(css = "input[data-qa='card-number']")
    private WebElement cardNumberField;

    @FindBy(css = "input[data-qa='cvc']")
    private WebElement cvcField;

    @FindBy(css = "input[data-qa='expiry-month']")
    private WebElement expiryMonthField;

    @FindBy(css = "input[data-qa='expiry-year']")
    private WebElement expiryYearField;

    @FindBy(css = "button[data-qa='pay-button']")
    private WebElement payButton;

    @FindBy(css = "h2[data-qa='order-placed']")
    private WebElement orderPlacedTitle;

    @FindBy(xpath = "//h2[@data-qa='order-placed']/following-sibling::p")
    private WebElement orderConfirmedMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getDeliveryName() {
        return elementMethods.getText(deliveryName);
    }

    public String getDeliveryAddressText() {
        return elementMethods.getText(deliveryAddressBlock);
    }

    public void placeOrder(String comment) {
        elementMethods.fillElement(commentField, comment);
        elementMethods.clickElement(placeOrderButton);
        LoggerUtility.infoTest("Placed the order");
    }

    public void payWithCard(CheckoutObject data) {
        elementMethods.fillElement(nameOnCardField, data.getNameOnCard());
        elementMethods.fillElement(cardNumberField, data.getCardNumber());
        elementMethods.fillElement(cvcField, data.getCvc());
        elementMethods.fillElement(expiryMonthField, data.getExpiryMonth());
        elementMethods.fillElement(expiryYearField, data.getExpiryYear());
        elementMethods.clickElement(payButton);
        LoggerUtility.infoTest("Paid with the test card");
    }

    public String getOrderPlacedTitle() {
        return elementMethods.getText(orderPlacedTitle);
    }

    public String getOrderConfirmedMessage() {
        return elementMethods.getText(orderConfirmedMessage);
    }
}
