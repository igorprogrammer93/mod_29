package Database;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/vendasdb";
        String user = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(url, user, password);
    }
}
