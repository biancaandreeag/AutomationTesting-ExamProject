package pages;

import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// pagina care apare dupa ce creez sau sterg un cont ("ACCOUNT CREATED!" / "ACCOUNT DELETED!"),
// plus doua linkuri din meniu care apar doar cand esti logat: "Logged in as ..." si "Delete Account"
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

    // butonul Continue de dupa crearea contului; ma duce inapoi pe site, deja logat
    public void continueToSite() {
        elementMethods.clickElement(continueButton);
    }

    // textul din meniu "Logged in as <nume>": asa stiu sigur ca sunt logat
    public String getLoggedInText() {
        return elementMethods.getText(loggedInAs);
    }

    // sterge contul de pe site, ca sa nu raman cu conturi de test
    public void deleteAccount() {
        elementMethods.clickElement(deleteAccountLink);
        LoggerUtility.infoTest("Deleted the account");
    }

    public String getAccountDeletedTitle() {
        return elementMethods.getText(accountDeletedTitle);
    }
}
