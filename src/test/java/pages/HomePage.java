package pages;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

// meniul de sus al site-ului (Products, Signup / Login, Contact us) si fereastra de cookie-uri
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

    public void goToSignupLogin() {
        elementMethods.clickElement(signupLoginLink);
        LoggerUtility.infoTest("Opened Signup / Login page");
    }

    public void goToProducts() {
        elementMethods.clickElement(productsLink);
        LoggerUtility.infoTest("Opened Products page");
    }

    public void goToContactUs() {
        elementMethods.clickElement(contactUsLink);
        LoggerUtility.infoTest("Opened Contact Us page");
    }
}
