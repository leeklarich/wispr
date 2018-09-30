import java.sql.*;

public class DBConnenction {
    private Connection db;

    public void connect(String url) {
        try {
            this.db = DriverManager.getConnection(url);
            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to connect to database!");
        }
    }

}
