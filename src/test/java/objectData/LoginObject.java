package objectData;

// datele din loginData.xml; XmlDataLoader le completeaza, deci numele campurilor = numele tag-urilor
// fara setteri: valorile le pune XmlDataLoader, testele doar le citesc prin getteri
public class LoginObject {

    private String email;
    private String password;
    private String expectedMessage;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
}
