package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import objectData.LoginObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import sharedData.ShareData;
import xmlReader.XmlDataLoader;

@Listeners(ChainTestListener.class)
public class LoginTest extends ShareData {

    // login cu un cont care nu exista -> site-ul trebuie sa arate mesajul de eroare (test negativ)
    @Test
    public void loginWithWrongCredentials() {
        // datele: un email care nu exista si mesajul de eroare asteptat (loginData.xml)
        LoginObject data = XmlDataLoader.loadData("src/test/resources/loginData.xml", LoginObject.class).get("dataSet_1");

        // intru pe pagina de login din meniu
        HomePage homePage = new HomePage(getDriver());
        homePage.goToSignupLogin();

        // incerc sa ma loghez cu datele gresite
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(data.getEmail(), data.getPassword());

        // site-ul trebuie sa refuze login-ul si sa arate exact mesajul asteptat
        Assert.assertEquals(loginPage.getLoginErrorMessage(), data.getExpectedMessage(), "Mesaj gresit la login cu date gresite");
    }
}
