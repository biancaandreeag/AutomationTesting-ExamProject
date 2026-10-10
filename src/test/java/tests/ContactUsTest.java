package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import objectData.ContactObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import sharedData.ShareData;
import xmlReader.XmlDataLoader;

@Listeners(ChainTestListener.class)
public class ContactUsTest extends ShareData {

    // completez formularul, atasez un fisier, confirm alerta si verific mesajul de succes
    @Test
    public void sendContactFormWithAttachment() {
        ContactObject data = XmlDataLoader.loadData("src/test/resources/contactData.xml", ContactObject.class).get("dataSet_1");

        new HomePage(getDriver()).goToContactUs();

        ContactUsPage contactUsPage = new ContactUsPage(getDriver());
        contactUsPage.fillForm(data);
        contactUsPage.submitAndConfirm();

        // verific si textul alertei, si mesajul de succes de dupa ea
        Assert.assertEquals(contactUsPage.getAlertText(), data.getExpectedAlert(), "Alt text in alerta de confirmare");
        Assert.assertEquals(contactUsPage.getSuccessMessage(), data.getExpectedMessage(), "Formularul nu a fost trimis");
    }
}
