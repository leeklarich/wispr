import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DBConnenction {
    private String url = "jdbc:sqlite:./new.db";
    private Connection db;

    public DBConnenction() {
        try {
            this.db = DriverManager.getConnection(this.url);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
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
        try (Statement stmt = db.createStatement()) {
            // create a new table
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createFriendsTable() {
        String query = "CREATE TABLE IF NOT EXISTS friends (\n"
                + "user1 integer,\n"
                + "user2 integer,\n"
                + ");";
        try (Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            stmt.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage() + "\nFailed to create friends table");
        }
    }

    //adds new username into database table
    public void insertDB(String username, String password){
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        try {
            sql = "INSERT INTO users(username,password) VALUES(?,?)";
            PreparedStatement pstmt = db.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            pstmt.close();
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
        try {
            PreparedStatement pstmt = db.prepareStatement(sql);
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

    public void makeFriends(String user1, String user2) {
        if(getUserId(user2) < 0) {
            System.out.println("Invalid user!");
            return;
        }
        String query = "INSERT INTO friends(user1, user2) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = db.prepareStatement(query);
            int user1ID = getUserId(user1);
            int user2ID = getUserId(user2);
            System.out.println(user1ID + "\t" + user2ID);
            if(user1ID != -1 || user2ID != -1) {
                pstmt.setInt(1, user1ID);
                pstmt.setInt(2, user2ID);
            }
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + "\nmake friends error");
        }
    }

    public void removeFriends(String user1, String user2) {
        String query = "DELETE FROM friends WHERE user1 = ? AND user2 = ?";
        try {
            PreparedStatement pstmt = db.prepareStatement(query);
            int user1ID = getUserId(user1);
            int user2ID = getUserId(user2);
            if(user1ID != -1 || user2ID != -1) {
                pstmt.setInt(1, user1ID);
                pstmt.setInt(2, user2ID);
            }
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + "\nremove friends error");
        }
    }

    public ArrayList<String> getFriends(String user) {
        ArrayList<String> list = new ArrayList<>();
        String query = "SELECT user2 FROM friends WHERE user1 = ?;";
        try {
            PreparedStatement pstmt = db.prepareStatement(query);
            pstmt.setInt(1, getUserId(user));
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(getUserName(rs.getInt("user2")));
            }
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + "\nget friends error");
        }
        return list;
    }

    private int getUserId(String name) {
        String query = "SELECT id FROM users WHERE username = ?";
        try {
            PreparedStatement pstmt = db.prepareStatement(query);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    private String getUserName(int id) {
        String query = "SELECT username FROM users WHERE id = ?";
        try {
            PreparedStatement pstmt = db.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getString("username");
            }
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Failed lookup";
    }
}
