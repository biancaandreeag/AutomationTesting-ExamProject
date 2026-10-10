package pages;

import helperMethods.AlertMethods;
import helperMethods.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// toate paginile primesc driverul si metodele ajutatoare de aici
public class BasePage {

    protected WebDriver driver;
    protected ElementMethods elementMethods;
    protected AlertMethods alertMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        this.alertMethods = new AlertMethods(driver);
        // leaga campurile cu @FindBy de elementele din pagina; ele se cauta abia cand le folosesc
        PageFactory.initElements(driver, this);
    }
}
