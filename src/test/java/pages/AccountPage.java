package pages;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// mesajele dupa creare / stergere de cont si linkurile din meniul unui utilizator logat
public class AccountPage extends BasePage {

    @FindBy(css = "h2[data-qa='account-created']")
    private WebElement accountCreatedTitle;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInAs;

    @FindBy(xpath = "//a[@href='/delete_account']")
    private WebElement deleteAccountLink;

    @FindBy(css = "h2[data-qa='account-deleted']")
    private WebElement accountDeletedTitle;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String getAccountCreatedTitle() {
        return elementMethods.getText(accountCreatedTitle);
    }

    public void continueToSite() {
        elementMethods.clickElement(continueButton);
    }

    public String getLoggedInText() {
        return elementMethods.getText(loggedInAs);
    }

    public void deleteAccount() {
        elementMethods.clickElement(deleteAccountLink);
        LoggerUtility.infoTest("Deleted the account");
    }

    public String getAccountDeletedTitle() {
        return elementMethods.getText(accountDeletedTitle);
    }
}
