package sharedData.browser;

import org.openqa.selenium.WebDriver;

// ce trebuie sa stie orice browser folosit in teste
public interface BrowserService {

    WebDriver openBrowser();
}
