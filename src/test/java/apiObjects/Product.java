package apiObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// un produs asa cum vine din API; iau doar campurile de care am nevoie,
// restul (ex. category) sunt ignorate prin @JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private int id;
    private String name;
    private String price;
    private String brand;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }
}
