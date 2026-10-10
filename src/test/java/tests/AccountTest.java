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

        // pasul 1 al inregistrarii: nume + email
        new HomePage(getDriver()).goToSignupLogin();
        new LoginPage(getDriver()).signup(data.getName(), email);

        // pasul 2: datele contului si adresa, apoi Create Account
        SignupPage signupPage = new SignupPage(getDriver());
        signupPage.fillAccountInformation(data);
        signupPage.fillAddressInformation(data);
        signupPage.createAccount();

        AccountPage accountPage = new AccountPage(getDriver());
        Assert.assertEquals(accountPage.getAccountCreatedTitle(), "ACCOUNT CREATED!");

        // contul creat pe site il salvez si in baza de date, apoi verific ca s-a salvat corect
        UsersTable usersTable = new UsersTable();
        usersTable.insertUser(data.getName(), email);
        Assert.assertEquals(usersTable.getNameByEmail(email), data.getName(), "Contul nu a ajuns corect in baza de date");

        // continui pe site si verific ca sunt logat cu contul nou
        accountPage.continueToSite();
        Assert.assertEquals(accountPage.getLoggedInText(), "Logged in as " + data.getName(), "Utilizatorul nu e logat dupa crearea contului");

        // la final sterg contul de pe site si randul din baza de date
        accountPage.deleteAccount();
        Assert.assertEquals(accountPage.getAccountDeletedTitle(), "ACCOUNT DELETED!");
        usersTable.deleteUserByEmail(email);
    }
}
