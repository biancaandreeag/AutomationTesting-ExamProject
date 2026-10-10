package helperMethods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// alertele JavaScript: ferestrele mici ale browserului, cu OK / Cancel (le are ex. formularul Contact Us).
// Selenium nu le vede ca pe elemente din pagina, asa ca au metodele lor, separat de ElementMethods
public class AlertMethods {

    private WebDriverWait wait;

    public AlertMethods(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // textul din alerta, ex. "Press OK to proceed!"
    public String getAlertText() {
        return waitForAlert().getText();
    }

    // apas OK
    public void acceptAlert() {
        waitForAlert().accept();
    }

    // alerta nu apare imediat dupa click, asa ca astept sa fie acolo (maxim 10 secunde)
    private Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
