package helperMethods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// alertele JavaScript: ferestrele mici ale browserului, cu OK / Cancel
public class AlertMethods {

    private WebDriverWait wait;

    public AlertMethods(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getAlertText() {
        return waitForAlert().getText();
    }

    public void acceptAlert() {
        waitForAlert().accept();
    }

    // alerta nu apare imediat dupa click, asa ca astept sa fie acolo (maxim 10 secunde)
    private Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
