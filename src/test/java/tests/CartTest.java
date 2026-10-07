package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;
import sharedData.ShareData;

@Listeners(ChainTestListener.class)
public class CartTest extends ShareData {

    // tot fluxul intr-un singur test: intre teste diferite cosul se goleste (sesiune noua)
    @Test
    public void addProductToCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.goToProducts();

        ProductsPage productsPage = new ProductsPage(getDriver());
        String expectedName = productsPage.getProductName(0);
        String expectedPrice = productsPage.getProductPrice(0);

        productsPage.addProductToCart(0);
        Assert.assertEquals(productsPage.getAddedMessage(), "Your product has been added to cart.");
        productsPage.viewCart();

        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getNumberOfProducts(), 1, "In cos trebuia sa fie un singur produs");
        Assert.assertEquals(cartPage.getProductName(0), expectedName);
        Assert.assertEquals(cartPage.getProductPrice(0), expectedPrice);
    }
}
