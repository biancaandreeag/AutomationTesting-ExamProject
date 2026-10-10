package helperMethods;

import logger.LoggerUtility;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// metodele de baza pe care le folosesc toate paginile: click, scris intr-un camp,
// citit un text si ales dintr-un dropdown. fiecare asteapta intai elementul
// (sa fie vizibil sau clickabil), ca sa nu dau click pe ceva care inca nu s-a incarcat.
// asa nu am nevoie de Thread.sleep nicaieri
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

    // astept pana elementul se vede pe ecran (nu doar sa existe in pagina)
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // click sigur: astept sa fie clickabil, il aduc in mijlocul ecranului, apoi dau click.
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

    // citesc textul doar dupa ce elementul e vizibil, altfel as putea primi un text gol
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
