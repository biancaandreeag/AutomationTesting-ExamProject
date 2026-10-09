package queries;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersTable {

    private Connection connection = DBConnection.getInstance().getConnection();

    public synchronized void insertUser(String name, String email) {
        execute("INSERT INTO users (name, email) VALUES (?, ?)", name, email);
    }

    public synchronized void deleteUserByEmail(String email) {
        execute("DELETE FROM users WHERE email = ?", email);
    }

    public synchronized String getNameByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM users WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            return result.next() ? result.getString("name") : null;
        } catch (Exception e) {
            throw new RuntimeException("SELECT esuat: " + e.getMessage(), e);
        }
    }

    // PreparedStatement in loc de query lipit din bucati: valorile nu pot strica sau schimba query-ul
    private void execute(String query, String... values) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                statement.setString(i + 1, values[i]);
            }
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Query esuat: " + e.getMessage(), e);
        }
    }
}
