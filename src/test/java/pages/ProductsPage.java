package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(css = ".features_items .productinfo")
    private List<WebElement> products;

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
    }

    public String getAddedMessage() {
        return addedMessage.getText();
    }

    public void viewCart() {
        elementMethods.clickElement(viewCartLink);
    }
}
