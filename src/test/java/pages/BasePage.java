package pages;

import helperMethods.AlertMethods;
import helperMethods.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// clasa de baza pentru toate paginile (HomePage, CartPage...): fiecare pagina o extinde
// si primeste de aici driverul si metodele ajutatoare, fara sa le mai creeze singura.
// daca adaug o metoda noua in ElementMethods, o au automat toate paginile
public class BasePage {

    // protected: le vad paginile (clasele copil), dar nu si testele
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
