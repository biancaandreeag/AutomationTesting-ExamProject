package objectData;

// datele din signupData.xml; XmlDataLoader le completeaza, deci numele campurilor = numele tag-urilor
// fara setteri: valorile le pune XmlDataLoader, testele doar le citesc prin getteri
public class SignupObject {

    private String name;
    private String email;
    private String expectedMessage;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
}
