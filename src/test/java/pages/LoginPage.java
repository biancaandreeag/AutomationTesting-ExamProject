package pages;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // pe pagina sunt doua formulare (login si signup), data-qa le deosebeste
    @FindBy(css = "input[data-qa='login-email']")
    private WebElement emailField;

    @FindBy(css = "input[data-qa='login-password']")
    private WebElement passwordField;

    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//form[@action='/login']//p")
    private WebElement loginErrorMessage;

    @FindBy(css = "input[data-qa='signup-name']")
    private WebElement signupNameField;

    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement signupEmailField;

    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(xpath = "//form[@action='/signup']//p")
    private WebElement signupErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        elementMethods.fillElement(emailField, email);
        elementMethods.fillElement(passwordField, password);
        elementMethods.clickElement(loginButton);
        LoggerUtility.infoTest("Tried to log in with " + email);
    }

    public String getLoginErrorMessage() {
        return elementMethods.getText(loginErrorMessage);
    }

    public void signup(String name, String email) {
        elementMethods.fillElement(signupNameField, name);
        elementMethods.fillElement(signupEmailField, email);
        elementMethods.clickElement(signupButton);
        LoggerUtility.infoTest("Tried to sign up with " + email);
    }

    public String getSignupErrorMessage() {
        return elementMethods.getText(signupErrorMessage);
    }

    // un email fara @ e oprit de browser inainte sa plece formularul; mesajul e in atributul validationMessage
    public String getSignupEmailValidationMessage() {
        return signupEmailField.getDomProperty("validationMessage");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
