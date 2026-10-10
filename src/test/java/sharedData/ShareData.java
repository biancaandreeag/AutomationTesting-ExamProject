package sharedData;

import com.aventstack.chaintest.plugins.ChainTestListener;
import configUtility.SiteUrls;
import logger.LoggerUtility;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import sharedData.browser.BrowserFactory;

import java.lang.reflect.Method;

// fiecare test porneste cu un browser nou, deja pe site si fara fereastra de cookie-uri;
// toate clasele de test extind ShareData, ca sa nu repet asta in fiecare test
public class ShareData {

    private WebDriver driver;
    private String testName;

    // inainte de fiecare test: pornesc logul testului, deschid browserul ales,
    // intru pe site si inchid fereastra de cookie-uri
    @BeforeMethod
    public void prepareBrowser(Method method) {
        // numele logului: Clasa_metoda (ex. CartTest_addProductToCart)
        testName = getClass().getSimpleName() + "_" + method.getName();
        LoggerUtility.startTestCase(testName);

        driver = BrowserFactory.getDriver();
        driver.get(SiteUrls.BASE_URL);
        new HomePage(driver).acceptCookiesIfShown();
        LoggerUtility.infoTest("Browser opened on automationexercise.com");
    }

    // dupa fiecare test: daca a picat, scriu eroarea in log si pun un screenshot in raport,
    // apoi inchid browserul cu quit() (inchide si procesul chromedriver, nu doar fereastra).
    // screenshot-ul se face inainte de quit, cat browserul mai e deschis
    @AfterMethod
    public void clearBrowser(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LoggerUtility.errorLog(result.getThrowable().getMessage());
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            ChainTestListener.embed(screenshot, "image/png");
        }
        driver.quit();
        LoggerUtility.infoTest("Browser closed");
        LoggerUtility.endTestCase(testName);
    }

    // o singura data, dupa toate testele
    @AfterSuite
    public void mergeLogs() {
        LoggerUtility.mergeTestLogs();
    }

    // driverul e private; testele il iau doar prin metoda asta
    public WebDriver getDriver() {
        return driver;
    }
}
