package sharedData.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeService implements BrowserService {

    @Override
    public WebDriver openBrowser() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments(AdBlocking.HOST_RULES);
        if (isCiRun()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
        }
        WebDriver driver = new EdgeDriver(options);
        // pe Mac --start-maximized nu are efect; in headless maximize() ar micsora fereastra
        if (!isCiRun()) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    private boolean isCiRun() {
        return Boolean.parseBoolean(System.getProperty("CICD"));
    }
}
