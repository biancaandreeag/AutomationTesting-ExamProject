package tests.api;

import apiObjects.ProductsResponse;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import logger.LoggerUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

// teste fara browser, direct pe API-ul site-ului
@Listeners(ChainTestListener.class)
public class ProductsApiTest {

    private String testName;

    @BeforeMethod
    public void setUp(Method method) {
        testName = getClass().getSimpleName() + "_" + method.getName();
        LoggerUtility.startTestCase(testName);
        RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @AfterMethod
    public void tearDown() {
        LoggerUtility.endTestCase(testName);
    }

    @Test
    public void getAllProducts() throws Exception {
        Response response = RestAssured.given().get("/productsList");
        Assert.assertEquals(response.getStatusCode(), 200);

        ProductsResponse body = readBody(response);
        Assert.assertEquals(body.getResponseCode(), 200);
        Assert.assertFalse(body.getProducts().isEmpty(), "Lista de produse e goala");
        Assert.assertTrue(body.containsProduct("Blue Top"), "Lipseste produsul Blue Top");
        LoggerUtility.infoTest("Products list returned " + body.getProducts().size() + " products");
    }

    @Test
    public void searchProduct() throws Exception {
        Response response = RestAssured.given()
                .formParam("search_product", "jeans")
                .post("/searchProduct");
        Assert.assertEquals(response.getStatusCode(), 200);

        ProductsResponse body = readBody(response);
        Assert.assertEquals(body.getResponseCode(), 200);
        Assert.assertTrue(body.containsProduct("Soft Stretch Jeans"), "Lipseste Soft Stretch Jeans din cautare");
        LoggerUtility.infoTest("Search for jeans returned " + body.getProducts().size() + " products");
    }

    // API-ul trimite JSON, dar cu Content-Type text/html, asa ca il citesc ca text si il transform eu
    private ProductsResponse readBody(Response response) throws Exception {
        return new ObjectMapper().readValue(response.asString(), ProductsResponse.class);
    }
}
