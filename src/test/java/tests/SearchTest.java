package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import objectData.SearchObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import sharedData.ShareData;
import xmlReader.XmlDataLoader;

import java.util.List;

@Listeners(ChainTestListener.class)
public class SearchTest extends ShareData {

    @Test
    public void searchProducts() {
        SearchObject data = XmlDataLoader.loadData("src/test/resources/searchData.xml", SearchObject.class).get("dataSet_1");

        HomePage homePage = new HomePage(getDriver());
        homePage.goToProducts();

        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.searchProduct(data.getSearchTerm());

        Assert.assertEquals(productsPage.getProductsTitle(), "SEARCHED PRODUCTS");

        // site-ul cauta si dupa categorie, nu doar dupa nume: pentru "dress" intoarce si
        // produse din categoria Dress fara cuvantul in nume, deci verific un produs cunoscut
        List<String> names = productsPage.getProductNames();
        Assert.assertFalse(names.isEmpty(), "Cautarea nu a intors niciun produs");
        Assert.assertTrue(names.contains(data.getExpectedProduct()), "Lipseste din rezultate: " + data.getExpectedProduct() + ". Gasite: " + names);
    }
}
