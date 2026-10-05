package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginTest {

    @Test
    public void loginWithWrongCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationexercise.com");

        // la prima vizita apare fereastra de cookie-uri peste pagina
        List<WebElement> consentButtons = driver.findElements(By.cssSelector("button.fc-cta-consent"));
        if (!consentButtons.isEmpty()) {
            consentButtons.get(0).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/login']"))).click();

        // pe pagina sunt doua formulare (login si signup), de aceea caut dupa data-qa
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-qa='login-email']")));
        emailField.sendKeys("nu.exista@example.com");
        driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys("ParolaGresita1");
        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//form[@action='/login']//p")));
        Assert.assertEquals(errorMessage.getText(), "Your email or password is incorrect!");

        driver.quit();
    }
}
