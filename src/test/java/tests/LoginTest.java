package tests;

import objectData.LoginObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import sharedData.ShareData;
import xmlReader.XmlDataLoader;

public class LoginTest extends ShareData {

    @Test
    public void loginWithWrongCredentials() {
        LoginObject data = XmlDataLoader.loadData("src/test/resources/loginData.xml", LoginObject.class).get("dataSet_1");

        HomePage homePage = new HomePage(getDriver());
        homePage.goToSignupLogin();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(data.getEmail(), data.getPassword());

        Assert.assertEquals(loginPage.getLoginErrorMessage(), data.getExpectedMessage());
    }
}
