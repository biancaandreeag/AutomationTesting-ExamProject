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

// fiecare test porneste cu un browser nou, deja pe site si fara fereastra de cookie-uri
public class ShareData {

    private WebDriver driver;
    private String testName;

    @BeforeMethod
    public void prepareBrowser(Method method) {
        testName = getClass().getSimpleName() + "_" + method.getName();
        LoggerUtility.startTestCase(testName);

        driver = BrowserFactory.getDriver();
        driver.get(SiteUrls.BASE_URL);
        new HomePage(driver).acceptCookiesIfShown();
        LoggerUtility.infoTest("Browser opened on automationexercise.com");
    }

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

    @AfterSuite
    public void mergeLogs() {
        LoggerUtility.mergeTestLogs();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
