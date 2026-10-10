package sharedData.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// setarile pentru Chrome; tot ce tine de browser sta aici, nu in teste
public class ChromeService implements BrowserService {

    @Override
    public WebDriver openBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments(AdBlocking.HOST_RULES);
        // pe GitHub nu exista ecran: browser fara fereastra, dar cu rezolutie de desktop
        if (isCiRun()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-dev-shm-usage");
        }
        WebDriver driver = new ChromeDriver(options);
        // pe Mac --start-maximized nu are efect; in headless maximize() ar micsora fereastra
        if (!isCiRun()) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    // pe GitHub Actions rulez cu -DCICD=true; local e false (din pom.xml)
    private boolean isCiRun() {
        return Boolean.parseBoolean(System.getProperty("CICD"));
    }
}
