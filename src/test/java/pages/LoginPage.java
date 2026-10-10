package pages;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// pagina Signup / Login (/login) are doua formulare unul langa altul:
// in stanga login-ul (email + parola), in dreapta primul pas al inregistrarii (nume + email).
// le deosebesc prin atributul data-qa, pus de site special pentru teste
public class LoginPage extends BasePage {

    // formularul de login
    @FindBy(css = "input[data-qa='login-email']")
    private WebElement emailField;

    @FindBy(css = "input[data-qa='login-password']")
    private WebElement passwordField;

    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//form[@action='/login']//p")
    private WebElement loginErrorMessage;

    // formularul de inregistrare
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

    // completez emailul si parola si apas Login; rezultatul il verifica testul, nu pagina
    public void login(String email, String password) {
        elementMethods.fillElement(emailField, email);
        elementMethods.fillElement(passwordField, password);
        elementMethods.clickElement(loginButton);
        LoggerUtility.infoTest("Tried to log in with " + email);
    }

    // mesajul rosu de sub formular, ex. "Your email or password is incorrect!"
    public String getLoginErrorMessage() {
        return elementMethods.getText(loginErrorMessage);
    }

    // primul pas al inregistrarii; daca emailul e nou, site-ul trece la formularul mare (SignupPage)
    public void signup(String name, String email) {
        elementMethods.fillElement(signupNameField, name);
        elementMethods.fillElement(signupEmailField, email);
        elementMethods.clickElement(signupButton);
        LoggerUtility.infoTest("Tried to sign up with " + email);
    }

    // apare cand emailul e deja folosit: "Email Address already exist!"
    public String getSignupErrorMessage() {
        return elementMethods.getText(signupErrorMessage);
    }

    // un email fara @ e oprit de browser inainte sa plece formularul; mesajul e in atributul validationMessage
    public String getSignupEmailValidationMessage() {
        return signupEmailField.getDomProperty("validationMessage");
    }

    // ca sa verific ca formularul nu a plecat (am ramas pe /login)
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
