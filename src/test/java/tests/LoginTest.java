package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest {

    @Test
    public void loginWithWrongCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");

        HomePage homePage = new HomePage(driver);
        homePage.acceptCookiesIfShown();
        homePage.goToSignupLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("nu.exista@example.com", "ParolaGresita1");

        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Your email or password is incorrect!");

        driver.quit();
    }
}
