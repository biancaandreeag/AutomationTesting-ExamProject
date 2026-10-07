package sharedData.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeService implements BrowserService {

    @Override
    public WebDriver openBrowser() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
