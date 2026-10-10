package xmlReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

// fiecare <dataSet_N> din XML devine un obiect; tag-urile trebuie sa aiba exact numele campurilor din clasa
public class XmlDataLoader {

    public static <T> Map<String, T> loadData(String filePath, Class<T> clazz) {
        // LinkedHashMap: seturile raman in ordinea din fisier
        Map<String, T> dataSets = new LinkedHashMap<>();
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filePath));
            NodeList sets = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < sets.getLength(); i++) {
                // sar peste spatiile si randurile goale dintre tag-uri
                if (sets.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element set = (Element) sets.item(i);
                T object = clazz.getDeclaredConstructor().newInstance();

                NodeList values = set.getChildNodes();
                for (int j = 0; j < values.getLength(); j++) {
                    if (values.item(j).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    Element value = (Element) values.item(j);
                    // reflection: caut in clasa campul cu numele tag-ului si ii pun valoarea, chiar daca e private
                    Field field = clazz.getDeclaredField(value.getTagName());
                    field.setAccessible(true);
                    field.set(object, value.getTextContent().trim());
                }
                dataSets.put(set.getTagName(), object);
            }
        } catch (Exception e) {
            throw new RuntimeException("Nu am putut citi datele din " + filePath + ": " + e.getMessage(), e);
        }
        return dataSets;
    }
}
