package configUtility;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

// citeste din dbConfig.xml datele de conectare la MySQL (port, baza, user); parola vine separat
public class ConfigReader {

    public static DatabaseConfig readDatabaseConfig(String filePath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filePath));
            return new DatabaseConfig(
                    getValue(document, "port"),
                    getValue(document, "database"),
                    getValue(document, "username"),
                    getPassword());
        } catch (Exception e) {
            throw new RuntimeException("Nu am putut citi configurarea bazei de date: " + e.getMessage(), e);
        }
    }

    // textul din tag, ex. <port>3306</port> -> "3306"
    private static String getValue(Document document, String tag) {
        return document.getElementsByTagName(tag).item(0).getTextContent().trim();
    }

    // parolele nu se pun in cod si nici pe Git; local, fara variabila setata, MySQL-ul are root fara parola
    private static String getPassword() {
        String password = System.getenv("DB_PASSWORD");
        return password != null ? password : "";
    }
}
