package pages;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

// pagina cosului (View Cart): randurile din tabel, numele, pretul si butonul de checkout
public class CartPage extends BasePage {

    @FindBy(css = "#cart_info_table tbody tr")
    private List<WebElement> cartRows;

    @FindBy(css = "#cart_info_table .cart_description h4 a")
    private List<WebElement> productNames;

    @FindBy(css = "#cart_info_table .cart_price p")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    private WebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfProducts() {
        return cartRows.size();
    }

    // index 0 = primul rand din cos
    public String getProductName(int index) {
        return elementMethods.getText(productNames.get(index));
    }

    public String getProductPrice(int index) {
        return elementMethods.getText(productPrices.get(index));
    }

    public void proceedToCheckout() {
        elementMethods.clickElement(proceedToCheckoutButton);
        LoggerUtility.infoTest("Proceeded to checkout");
    }
}
