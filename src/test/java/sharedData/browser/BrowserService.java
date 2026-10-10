package sharedData.browser;

import org.openqa.selenium.WebDriver;

// ce trebuie sa stie orice browser folosit in teste: fiecare (Chrome, Edge) are setarile lui,
// dar toate se deschid la fel, cu openBrowser()
public interface BrowserService {

    WebDriver openBrowser();
}
