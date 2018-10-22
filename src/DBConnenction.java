import javax.swing.*;
import java.sql.*;

public class DBConnenction {
    private Connection db;
    private String url = "jdbc:sqlite:./new.db";

    //change type from var to connection
    public void connect()
    {
        url = this.url;
        try {
            this.db = DriverManager.getConnection(url);
            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to connect to database!");
        }

    }

    //creates table in the database if it doesnt exists
    public void createTable(){
        url = this.url;
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "id integer PRIMARY KEY,\n"
                + "username text NOT NULL,\n"
                + "password text NOT NULL\n"
                + ");";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);

            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //adds new username into database table
    public void insertDB(String username, String password){
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        try {
            Connection conn = DriverManager.getConnection(url);
            sql = "INSERT INTO users(username,password) VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //returns true when username and password are in database
    public Boolean getLogin(String user, String pass){
        url = this.url;
        Boolean userExists = false;

        String sql = "SELECT id ,username ,password "
                + "FROM users WHERE username = ? AND password = ?";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                userExists = true;
            }
            pstmt.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return userExists;
    }
}
