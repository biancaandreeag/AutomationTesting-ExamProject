package objectData;

// datele din contactData.xml; XmlDataLoader le completeaza, deci numele campurilor = numele tag-urilor
public class ContactObject {

    private String name;
    private String email;
    private String subject;
    private String message;
    private String filePath;
    private String expectedAlert;
    private String expectedMessage;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getExpectedAlert() {
        return expectedAlert;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
}
