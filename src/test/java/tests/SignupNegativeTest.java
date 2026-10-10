package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import objectData.SignupObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import sharedData.ShareData;
import xmlReader.XmlDataLoader;

import java.util.Map;

@Listeners(ChainTestListener.class)
public class SignupNegativeTest extends ShareData {

    private Map<String, SignupObject> signupData = XmlDataLoader.loadData("src/test/resources/signupData.xml", SignupObject.class);

    // contul din dataSet_1 exista deja pe site, deci inregistrarea trebuie refuzata
    @Test
    public void signupWithExistingEmail() {
        SignupObject data = signupData.get("dataSet_1");

        new HomePage(getDriver()).goToSignupLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.signup(data.getName(), data.getEmail());

        Assert.assertEquals(loginPage.getSignupErrorMessage(), data.getExpectedMessage(), "Mesaj gresit pentru un email deja folosit");
    }

    @Test
    public void signupWithInvalidEmail() {
        SignupObject data = signupData.get("dataSet_2");

        new HomePage(getDriver()).goToSignupLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.signup(data.getName(), data.getEmail());

        Assert.assertFalse(loginPage.getSignupEmailValidationMessage().isEmpty(), "Browserul trebuia sa refuze emailul fara @");
        Assert.assertTrue(loginPage.getCurrentUrl().endsWith("/login"), "Formularul nu trebuia trimis");
    }
}
