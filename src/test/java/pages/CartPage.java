package pages;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

// pagina cosului (/view_cart): un tabel cu cate un rand pentru fiecare produs adaugat.
// de aici citesc ce e in cos (nume, pret), ca sa compar cu ce am adaugat,
// si tot de aici merg mai departe la checkout
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

    // cate randuri are tabelul = cate produse diferite sunt in cos
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

    // butonul "Proceed To Checkout"; merge doar daca esti logat (altfel site-ul cere login)
    public void proceedToCheckout() {
        elementMethods.clickElement(proceedToCheckoutButton);
        LoggerUtility.infoTest("Proceeded to checkout");
    }
}
