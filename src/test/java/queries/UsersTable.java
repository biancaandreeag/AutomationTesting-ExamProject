package queries;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// query-urile pe tabela users din exam_database (tabela se creeaza cu createTables.sql)
// synchronized pe metode: in paralel, testele nu folosesc conexiunea in acelasi timp
public class UsersTable {

    private Connection connection = DBConnection.getInstance().getConnection();

    // adaug un rand cu numele si emailul contului creat pe site
    public synchronized void insertUser(String name, String email) {
        execute("INSERT INTO users (name, email) VALUES (?, ?)", name, email);
    }

    // sterg randul la finalul testului, ca tabela sa ramana curata
    public synchronized void deleteUserByEmail(String email) {
        execute("DELETE FROM users WHERE email = ?", email);
    }

    // intoarce null daca nu exista niciun rand cu emailul dat
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
