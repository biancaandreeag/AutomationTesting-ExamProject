package helperMethods;

import logger.LoggerUtility;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementMethods {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavaScriptMethods javaScriptMethods;

    public ElementMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.javaScriptMethods = new JavaScriptMethods(driver);
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // reclamele Google (iframe) apar uneori peste butoane si primesc ele click-ul;
    // atunci apas butonul prin JavaScript, ca un utilizator care inchide reclama si da click
    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        javaScriptMethods.scrollToElement(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            LoggerUtility.infoTest("Click intercepted by an ad, clicking with JavaScript");
            javaScriptMethods.clickWithJavaScript(element);
        }
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

    public void selectByText(WebElement element, String text) {
        waitForVisibility(element);
        new Select(element).selectByVisibleText(text);
    }
}
