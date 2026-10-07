package pages;

import logger.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

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

    public String getProductName(int index) {
        return products.get(index).findElement(By.tagName("p")).getText();
    }

    public String getProductPrice(int index) {
        return products.get(index).findElement(By.tagName("h2")).getText();
    }

    public void addProductToCart(int index) {
        WebElement addButton = products.get(index).findElement(By.cssSelector("a.add-to-cart"));
        elementMethods.clickElement(addButton);
        LoggerUtility.infoTest("Added product " + index + " to cart");
    }

    // fereastra apare abia dupa ce serverul raspunde la "Add to cart", deci astept sa fie vizibila
    public String getAddedMessage() {
        return elementMethods.getText(addedMessage);
    }

    public void viewCart() {
        elementMethods.clickElement(viewCartLink);
        LoggerUtility.infoTest("Opened cart from the confirmation window");
    }

    public void searchProduct(String term) {
        elementMethods.fillElement(searchField, term);
        elementMethods.clickElement(searchButton);
        LoggerUtility.infoTest("Searched for " + term);
    }

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
