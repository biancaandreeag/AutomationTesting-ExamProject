package pages;

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

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        elementMethods.fillElement(emailField, email);
        elementMethods.fillElement(passwordField, password);
        elementMethods.clickElement(loginButton);
    }

    public String getLoginErrorMessage() {
        return elementMethods.getText(loginErrorMessage);
    }
}
