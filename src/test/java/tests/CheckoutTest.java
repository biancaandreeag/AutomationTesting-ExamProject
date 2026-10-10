package tests;

import apiRequests.AccountRequests;
import com.aventstack.chaintest.plugins.ChainTestListener;
import objectData.AccountObject;
import objectData.CheckoutObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import sharedData.ShareData;
import xmlReader.XmlDataLoader;

@Listeners(ChainTestListener.class)
public class CheckoutTest extends ShareData {

    // contul il creez prin API (mai repede decat formularul, care e testat in AccountTest),
    // apoi tot fluxul de cumparare in browser, intr-un singur test
    @Test
    public void placeOrderWithLoggedInUser() {
        AccountObject account = XmlDataLoader.loadData("src/test/resources/accountData.xml", AccountObject.class).get("dataSet_1");
        CheckoutObject checkout = XmlDataLoader.loadData("src/test/resources/checkoutData.xml", CheckoutObject.class).get("dataSet_1");
        String email = "bianca.checkout." + System.currentTimeMillis() + "@example.com";
        AccountRequests.createAccount(account, email);

        try {
            HomePage homePage = new HomePage(getDriver());
            homePage.goToSignupLogin();
            new LoginPage(getDriver()).login(email, account.getPassword());

            homePage.goToProducts();
            ProductsPage productsPage = new ProductsPage(getDriver());
            String productName = productsPage.getProductName(0);
            productsPage.addProductToCart(0);
            productsPage.viewCart();

            CartPage cartPage = new CartPage(getDriver());
            Assert.assertEquals(cartPage.getProductName(0), productName);
            cartPage.proceedToCheckout();

            CheckoutPage checkoutPage = new CheckoutPage(getDriver());
            Assert.assertEquals(checkoutPage.getDeliveryName(), "Mrs. " + account.getFirstName() + " " + account.getLastName());
            Assert.assertTrue(checkoutPage.getDeliveryAddressText().contains(account.getAddress()), "Adresa de livrare nu e cea din cont");

            checkoutPage.placeOrder(checkout.getComment());
            checkoutPage.payWithCard(checkout);
            Assert.assertEquals(checkoutPage.getOrderPlacedTitle(), "ORDER PLACED!");
            Assert.assertEquals(checkoutPage.getOrderConfirmedMessage(), "Congratulations! Your order has been confirmed!");
        } finally {
            // contul se sterge si daca un assert pica, ca sa nu ramana conturi de test pe site
            AccountRequests.deleteAccount(email, account.getPassword());
        }
    }
}
