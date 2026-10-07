package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import sharedData.ShareData;

import java.util.List;

public class SearchTest extends ShareData {

    @Test
    public void searchProducts() {
        String searchTerm = "dress";
        String expectedProduct = "Sleeveless Dress";

        HomePage homePage = new HomePage(getDriver());
        homePage.goToProducts();

        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.searchProduct(searchTerm);

        Assert.assertEquals(productsPage.getProductsTitle(), "SEARCHED PRODUCTS");

        // site-ul cauta si dupa categorie, nu doar dupa nume: pentru "dress" intoarce si
        // produse din categoria Dress fara cuvantul in nume, deci verific un produs cunoscut
        List<String> names = productsPage.getProductNames();
        Assert.assertFalse(names.isEmpty(), "Cautarea nu a intors niciun produs");
        Assert.assertTrue(names.contains(expectedProduct), "Lipseste din rezultate: " + expectedProduct + ". Gasite: " + names);
    }
}
