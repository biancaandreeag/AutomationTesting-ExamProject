package pages;

import logger.LoggerUtility;
import objectData.ContactObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

// pagina Contact Us: formularul cu nume, email, subiect, mesaj si fisier atasat
public class ContactUsPage extends BasePage {

    @FindBy(css = "input[data-qa='name']")
    private WebElement nameField;

    @FindBy(css = "input[data-qa='email']")
    private WebElement emailField;

    @FindBy(css = "input[data-qa='subject']")
    private WebElement subjectField;

    @FindBy(id = "message")
    private WebElement messageField;

    @FindBy(name = "upload_file")
    private WebElement uploadInput;

    @FindBy(css = "input[data-qa='submit-button']")
    private WebElement submitButton;

    @FindBy(css = "#contact-page .status.alert-success")
    private WebElement successMessage;

    // dupa OK alerta dispare, asa ca ii pastrez textul aici pentru assert
    private String alertText;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(ContactObject data) {
        elementMethods.fillElement(nameField, data.getName());
        elementMethods.fillElement(emailField, data.getEmail());
        elementMethods.fillElement(subjectField, data.getSubject());
        elementMethods.fillElement(messageField, data.getMessage());
        // input-ul de tip file primeste calea fisierului; in date e relativa (merge si pe CI), browserul o vrea completa
        uploadInput.sendKeys(new File(data.getFilePath()).getAbsolutePath());
        LoggerUtility.infoTest("Filled the contact form and attached " + data.getFilePath());
    }

    // dupa Submit site-ul cere confirmare printr-o alerta; textul il pastrez pentru verificare
    public void submitAndConfirm() {
        elementMethods.clickElement(submitButton);
        alertText = alertMethods.getAlertText();
        alertMethods.acceptAlert();
        LoggerUtility.infoTest("Submitted the form and accepted the alert: " + alertText);
    }

    public String getAlertText() {
        return alertText;
    }

    public String getSuccessMessage() {
        return elementMethods.getText(successMessage);
    }
}
