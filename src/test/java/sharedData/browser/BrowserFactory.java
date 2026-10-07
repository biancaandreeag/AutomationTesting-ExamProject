package sharedData.browser;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    // browserul vine din pom.xml sau din linia de comanda: mvn test -Dbrowser=edge
    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");

        BrowserService service;
        switch (browser.toLowerCase()) {
            case "chrome":
                service = new ChromeService();
                break;
            case "edge":
                service = new EdgeService();
                break;
            default:
                throw new IllegalArgumentException("Browser necunoscut: " + browser);
        }
        return service.openBrowser();
    }
}
