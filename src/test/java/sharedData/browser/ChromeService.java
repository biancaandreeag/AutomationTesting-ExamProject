package sharedData.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeService implements BrowserService {

    @Override
    public WebDriver openBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        // pe Mac --start-maximized nu are efect
        driver.manage().window().maximize();
        return driver;
    }
}
