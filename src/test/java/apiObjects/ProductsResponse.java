package apiObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

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

    public boolean containsProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
