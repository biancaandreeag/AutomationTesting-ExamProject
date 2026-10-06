package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "#cart_info_table tbody tr")
    private List<WebElement> cartRows;

    @FindBy(css = "#cart_info_table .cart_description h4 a")
    private List<WebElement> productNames;

    @FindBy(css = "#cart_info_table .cart_price p")
    private List<WebElement> productPrices;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfProducts() {
        return cartRows.size();
    }

    public String getProductName(int index) {
        return elementMethods.getText(productNames.get(index));
    }

    public String getProductPrice(int index) {
        return elementMethods.getText(productPrices.get(index));
    }
}
