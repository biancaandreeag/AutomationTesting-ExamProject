package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import sharedData.ShareData;

public class LoginTest extends ShareData {

    @Test
    public void loginWithWrongCredentials() {
        HomePage homePage = new HomePage(getDriver());
        homePage.goToSignupLogin();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("nu.exista@example.com", "ParolaGresita1");

        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Your email or password is incorrect!");
    }
}
