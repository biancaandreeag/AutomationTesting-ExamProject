package sharedData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

// fiecare test porneste cu un browser nou, deja pe site si fara fereastra de cookie-uri
public class ShareData {

    private WebDriver driver;

    @BeforeMethod
    public void prepareBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
        new HomePage(driver).acceptCookiesIfShown();
    }

    // quit si nu close: inchide si procesul chromedriver, nu doar fereastra
    @AfterMethod
    public void clearBrowser() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
