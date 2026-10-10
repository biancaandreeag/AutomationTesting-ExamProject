package pages;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

// pagina de start a site-ului. de fapt folosesc meniul de sus, care e acelasi pe toate paginile,
// asa ca metodele de aici merg de oriunde: Products, Signup / Login, Contact us.
// tot aici e si fereastra de cookie-uri care apare la prima intrare pe site
public class HomePage extends BasePage {

    // lista, nu un singur element: daca fereastra nu apare, lista e goala si testul nu pica
    @FindBy(css = "button.fc-cta-consent")
    private List<WebElement> consentButtons;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[@href='/contact_us']")
    private WebElement contactUsLink;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // fereastra de cookie-uri apare doar la prima vizita, deci nu e mereu acolo
    public void acceptCookiesIfShown() {
        if (!consentButtons.isEmpty()) {
            elementMethods.clickElement(consentButtons.get(0));
            LoggerUtility.infoTest("Cookie consent accepted");
        }
    }

    // click pe "Signup / Login" din meniu
    public void goToSignupLogin() {
        elementMethods.clickElement(signupLoginLink);
        LoggerUtility.infoTest("Opened Signup / Login page");
    }

    // click pe "Products" din meniu
    public void goToProducts() {
        elementMethods.clickElement(productsLink);
        LoggerUtility.infoTest("Opened Products page");
    }

    // click pe "Contact us" din meniu
    public void goToContactUs() {
        elementMethods.clickElement(contactUsLink);
        LoggerUtility.infoTest("Opened Contact Us page");
    }
}
