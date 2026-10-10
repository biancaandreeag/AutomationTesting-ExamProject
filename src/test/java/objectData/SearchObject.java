package objectData;

// datele din searchData.xml; XmlDataLoader le completeaza, deci numele campurilor = numele tag-urilor
public class SearchObject {

    private String searchTerm;
    private String expectedProduct;

    public String getSearchTerm() {
        return searchTerm;
    }

    public String getExpectedProduct() {
        return expectedProduct;
    }
}
