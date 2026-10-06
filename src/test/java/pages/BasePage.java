package pages;

import helperMethods.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// toate paginile primesc driverul si metodele ajutatoare de aici
public class BasePage {

    protected WebDriver driver;
    protected ElementMethods elementMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }
}
