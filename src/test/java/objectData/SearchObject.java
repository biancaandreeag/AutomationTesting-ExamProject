package objectData;

// datele din searchData.xml; XmlDataLoader le completeaza, deci numele campurilor = numele tag-urilor
// fara setteri: valorile le pune XmlDataLoader, testele doar le citesc prin getteri
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
