package configUtility;

// doar tine datele de conectare citite de ConfigReader, ca sa ajunga la DBConnection
public class DatabaseConfig {

    private String port;
    private String database;
    private String username;
    private String password;

    public DatabaseConfig(String port, String database, String username, String password) {
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
