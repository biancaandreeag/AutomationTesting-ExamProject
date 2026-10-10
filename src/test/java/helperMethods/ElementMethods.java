package helperMethods;

import logger.LoggerUtility;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// actiunile de baza pe elemente (click, scris, citit text, select), toate cu asteptare inainte:
// asa nu dau click si nu scriu intr-un element care inca nu s-a incarcat
public class ElementMethods {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavaScriptMethods javaScriptMethods;

    public ElementMethods(WebDriver driver) {
        this.driver = driver;
        // daca elementul nu e gata in 10 secunde, testul pica cu TimeoutException
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

    // sterg intai ce era in camp, altfel textul nou s-ar lipi de cel vechi
    public void fillElement(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    // pentru dropdown-urile <select> (zi, luna, an, tara): aleg dupa textul care se vede
    public void selectByText(WebElement element, String text) {
        waitForVisibility(element);
        new Select(element).selectByVisibleText(text);
    }
}
