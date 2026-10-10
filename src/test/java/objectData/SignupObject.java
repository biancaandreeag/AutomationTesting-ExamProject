package objectData;

// datele din signupData.xml; XmlDataLoader le completeaza, deci numele campurilor = numele tag-urilor
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
