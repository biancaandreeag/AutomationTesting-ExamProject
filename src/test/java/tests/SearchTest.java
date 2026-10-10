package tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import objectData.SearchObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import sharedData.ShareData;
import xmlReader.XmlDataLoader;

import java.util.List;

@Listeners(ChainTestListener.class)
public class SearchTest extends ShareData {

    // data-driven: testul ruleaza o data pentru fiecare <dataSet_N> din searchData.xml;
    // un caz nou de cautare se adauga doar in XML, fara cod
    @DataProvider(name = "searchData")
    public Object[][] searchData() {
        return XmlDataLoader.loadData("src/test/resources/searchData.xml", SearchObject.class)
                .values().stream()
                .map(data -> new Object[]{data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "searchData")
    public void searchProducts(SearchObject data) {

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
