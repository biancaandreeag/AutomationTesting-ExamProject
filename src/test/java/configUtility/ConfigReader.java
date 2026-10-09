package configUtility;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ConfigReader {

    public static DatabaseConfig readDatabaseConfig(String filePath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filePath));
            return new DatabaseConfig(
                    getValue(document, "port"),
                    getValue(document, "database"),
                    getValue(document, "username"),
                    getValue(document, "password"));
        } catch (Exception e) {
            throw new RuntimeException("Nu am putut citi configurarea bazei de date: " + e.getMessage(), e);
        }
    }

    private static String getValue(Document document, String tag) {
        return document.getElementsByTagName(tag).item(0).getTextContent().trim();
    }
}
