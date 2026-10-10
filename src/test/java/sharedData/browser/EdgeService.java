package sharedData.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

// setarile pentru Edge: fereastra mare, reclamele blocate si, pe GitHub, modul fara ecran (headless).
// tot ce tine de browser sta aici, nu in teste sau in ShareData
public class EdgeService implements BrowserService {

    @Override
    public WebDriver openBrowser() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments(AdBlocking.HOST_RULES);
        // pe GitHub nu exista ecran: browser fara fereastra, dar cu rezolutie de desktop
        if (isCiRun()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-dev-shm-usage");
            // pe Linux, pe serverul GitHub, Edge nu porneste fara asta (Chrome merge si fara)
            options.addArguments("--no-sandbox");
        }
        WebDriver driver = new EdgeDriver(options);
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
