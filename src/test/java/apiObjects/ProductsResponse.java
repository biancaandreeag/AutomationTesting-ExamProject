package apiObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// tot raspunsul de la /productsList sau /searchProduct: codul din body si lista de produse
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsResponse {

    private int responseCode;
    private List<Product> products;

    public int getResponseCode() {
        return responseCode;
    }

    public List<Product> getProducts() {
        return products;
    }

    // caut dupa numele exact, ca in testul de API sa verific un produs anume
    public boolean containsProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
