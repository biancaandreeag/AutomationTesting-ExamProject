package pages;

import logger.LoggerUtility;
import objectData.AccountObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// a doua pagina a inregistrarii ("Enter Account Information"), unde ajung dupa ce
// completez numele si emailul in LoginPage. are doua parti: datele contului (sus)
// si adresa (jos); la final butonul Create Account
public class SignupPage extends BasePage {

    @FindBy(id = "id_gender2")
    private WebElement mrsTitle;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "days")
    private WebElement daySelect;

    @FindBy(id = "months")
    private WebElement monthSelect;

    @FindBy(id = "years")
    private WebElement yearSelect;

    @FindBy(id = "first_name")
    private WebElement firstNameField;

    @FindBy(id = "last_name")
    private WebElement lastNameField;

    @FindBy(id = "address1")
    private WebElement addressField;

    @FindBy(id = "country")
    private WebElement countrySelect;

    @FindBy(id = "state")
    private WebElement stateField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "zipcode")
    private WebElement zipcodeField;

    @FindBy(id = "mobile_number")
    private WebElement mobileField;

    @FindBy(css = "button[data-qa='create-account']")
    private WebElement createAccountButton;

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    // partea de sus a formularului: titlu, parola, data nasterii
    public void fillAccountInformation(AccountObject data) {
        elementMethods.clickElement(mrsTitle);
        elementMethods.fillElement(passwordField, data.getPassword());
        elementMethods.selectByText(daySelect, data.getDay());
        elementMethods.selectByText(monthSelect, data.getMonth());
        elementMethods.selectByText(yearSelect, data.getYear());
        LoggerUtility.infoTest("Filled account information");
    }

    // partea de jos: numele si adresa (apar apoi la checkout ca adresa de livrare)
    public void fillAddressInformation(AccountObject data) {
        elementMethods.fillElement(firstNameField, data.getFirstName());
        elementMethods.fillElement(lastNameField, data.getLastName());
        elementMethods.fillElement(addressField, data.getAddress());
        elementMethods.selectByText(countrySelect, data.getCountry());
        elementMethods.fillElement(stateField, data.getState());
        elementMethods.fillElement(cityField, data.getCity());
        elementMethods.fillElement(zipcodeField, data.getZipcode());
        elementMethods.fillElement(mobileField, data.getMobileNumber());
        LoggerUtility.infoTest("Filled address information");
    }

    // dupa click, site-ul arata "ACCOUNT CREATED!" (il citesc din AccountPage)
    public void createAccount() {
        elementMethods.clickElement(createAccountButton);
        LoggerUtility.infoTest("Clicked Create Account");
    }
}
