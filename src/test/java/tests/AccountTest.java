package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import objectData.AccountObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import queries.UsersTable;
import sharedData.ShareData;
import xmlReader.XmlDataLoader;

@Listeners(ChainTestListener.class)
public class AccountTest extends ShareData {

    // cont nou pe site -> salvat in baza de date -> verificat -> sters de pe site, ca sa nu ramana conturi de test
    @Test
    public void createAccountAndSaveInDatabase() {
        AccountObject data = XmlDataLoader.loadData("src/test/resources/accountData.xml", AccountObject.class).get("dataSet_1");
        // email nou la fiecare rulare, altfel site-ul spune ca exista deja
        String email = "bianca." + System.currentTimeMillis() + "@example.com";

        new HomePage(getDriver()).goToSignupLogin();
        new LoginPage(getDriver()).signup(data.getName(), email);

        SignupPage signupPage = new SignupPage(getDriver());
        signupPage.fillAccountInformation(data);
        signupPage.fillAddressInformation(data);
        signupPage.createAccount();

        AccountPage accountPage = new AccountPage(getDriver());
        Assert.assertEquals(accountPage.getAccountCreatedTitle(), "ACCOUNT CREATED!");

        UsersTable usersTable = new UsersTable();
        usersTable.insertUser(data.getName(), email);
        Assert.assertEquals(usersTable.getNameByEmail(email), data.getName(), "Contul nu a ajuns corect in baza de date");

        accountPage.continueToSite();
        Assert.assertEquals(accountPage.getLoggedInText(), "Logged in as " + data.getName());

        accountPage.deleteAccount();
        Assert.assertEquals(accountPage.getAccountDeletedTitle(), "ACCOUNT DELETED!");
        usersTable.deleteUserByEmail(email);
    }
}
