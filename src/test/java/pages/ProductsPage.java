package pages;

import logger.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

// pagina Products (/products): lista cu toate produsele, campul de cautare
// si fereastra care apare dupa Add to cart (cu "View Cart" si "Continue Shopping").
// produsele le aleg dupa pozitie: index 0 = primul produs din lista
public class ProductsPage extends BasePage {

    // toate casutele de produs din pagina, in ordinea in care apar
    @FindBy(css = ".features_items .productinfo")
    private List<WebElement> products;

    @FindBy(id = "search_product")
    private WebElement searchField;

    @FindBy(id = "submit_search")
    private WebElement searchButton;

    @FindBy(css = ".features_items h2.title")
    private WebElement productsTitle;

    @FindBy(css = "#cartModal .modal-body p")
    private WebElement addedMessage;

    @FindBy(css = "#cartModal a[href='/view_cart']")
    private WebElement viewCartLink;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // fiecare produs e o casuta: numele e in <p>, pretul in <h2>
    public String getProductName(int index) {
        return products.get(index).findElement(By.tagName("p")).getText();
    }

    public String getProductPrice(int index) {
        return products.get(index).findElement(By.tagName("h2")).getText();
    }

    // butonul Add to cart e in casuta produsului; il caut in interiorul ei, ca sa nu apas pe alt produs
    public void addProductToCart(int index) {
        WebElement addButton = products.get(index).findElement(By.cssSelector("a.add-to-cart"));
        elementMethods.clickElement(addButton);
        LoggerUtility.infoTest("Added product " + index + " to cart");
    }

    // fereastra apare abia dupa ce serverul raspunde la "Add to cart", deci astept sa fie vizibila
    public String getAddedMessage() {
        return elementMethods.getText(addedMessage);
    }

    // aceeasi metoda, dar dupa numele produsului in loc de pozitie (overloading = polimorfism):
    // Java alege varianta dupa parametru, addProductToCart(0) sau addProductToCart("Blue Top")
    public void addProductToCart(String productName) {
        int index = getProductNames().indexOf(productName);
        if (index == -1) {
            throw new IllegalArgumentException("Produsul nu e in lista: " + productName);
        }
        LoggerUtility.infoTest("Found " + productName + " at position " + index);
        addProductToCart(index);
    }

    // din fereastra de confirmare apas "View Cart" si ajung in cos
    public void viewCart() {
        elementMethods.clickElement(viewCartLink);
        LoggerUtility.infoTest("Opened cart from the confirmation window");
    }

    // scriu termenul in campul de cautare si apas lupa de langa
    public void searchProduct(String term) {
        elementMethods.fillElement(searchField, term);
        elementMethods.clickElement(searchButton);
        LoggerUtility.infoTest("Searched for " + term);
    }

    // titlul de deasupra listei: "ALL PRODUCTS" sau, dupa o cautare, "SEARCHED PRODUCTS"
    public String getProductsTitle() {
        return elementMethods.getText(productsTitle);
    }

    // in HTML unele nume au spatii duble ("Sleeveless  Dress"), asa ca le reduc la unul singur
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement product : products) {
            names.add(product.findElement(By.tagName("p")).getText().replaceAll("\\s+", " ").trim());
        }
        return names;
    }
}
