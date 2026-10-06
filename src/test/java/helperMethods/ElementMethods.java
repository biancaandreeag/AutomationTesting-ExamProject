package helperMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementMethods {

    private WebDriver driver;
    private WebDriverWait wait;

    public ElementMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void fillElement(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }
}
