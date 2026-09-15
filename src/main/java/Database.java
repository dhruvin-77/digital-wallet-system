import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:wallet.db";

    private Database() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() {
        String sql = """
                CREATE TABLE IF NOT EXISTS jdbc_users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL UNIQUE
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Database setup error: " + e.getMessage());
        }
    }

    public static void saveUser(String name, String email) {
        String sql = """
                INSERT OR IGNORE INTO jdbc_users
                (name, email)
                VALUES (?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not save user to database: " + e.getMessage());
        }
    }

    public static void showUsers() {
        String sql = "SELECT id, name, email FROM jdbc_users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("========== JDBC USERS ==========");

            while (resultSet.next())
                System.out.println(resultSet.getInt("id") + ". "
                        + resultSet.getString("name") + " - "
                        + resultSet.getString("email"));

        } catch (SQLException e) {
            System.out.println("Could not read database users: " + e.getMessage());
        }
    }
}
