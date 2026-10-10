package database;

import configUtility.ConfigReader;
import configUtility.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;

// o singura conexiune pentru toate testele (Singleton)
public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    // constructor private: din afara nu se poate face new DBConnection(), doar getInstance()
    private DBConnection() {
        DatabaseConfig config = ConfigReader.readDatabaseConfig("src/test/resources/dbConfig.xml");
        // useSSL=false si allowPublicKeyRetrieval: setari pentru un MySQL local, fara certificat
        String url = "jdbc:mysql://localhost:" + config.getPort() + "/" + config.getDatabase()
                + "?allowPublicKeyRetrieval=true&useSSL=false";
        try {
            connection = DriverManager.getConnection(url, config.getUsername(), config.getPassword());
        } catch (Exception e) {
            throw new RuntimeException("Conexiunea la MySQL a esuat (serverul e pornit?): " + e.getMessage(), e);
        }
    }

    // prima data creeaza conexiunea, apoi intoarce mereu aceeasi;
    // synchronized ca doua teste care ruleaza in paralel sa nu creeze doua conexiuni
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
