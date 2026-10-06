package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "button.fc-cta-consent")
    private List<WebElement> consentButtons;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // fereastra de cookie-uri apare doar la prima vizita, deci nu e mereu acolo
    public void acceptCookiesIfShown() {
        if (!consentButtons.isEmpty()) {
            elementMethods.clickElement(consentButtons.get(0));
        }
    }

    public void goToSignupLogin() {
        elementMethods.clickElement(signupLoginLink);
    }

    public void goToProducts() {
        elementMethods.clickElement(productsLink);
    }
}
