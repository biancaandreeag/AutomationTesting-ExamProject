package helperMethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptMethods {

    private JavascriptExecutor js;

    public JavaScriptMethods(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    // reclamele de pe site pot acoperi elementele de jos, asa ca il aduc in mijlocul ecranului
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", element);
    }

    // apasa elementul direct din pagina, chiar daca peste el e altceva (ex. o reclama)
    public void clickWithJavaScript(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
}
