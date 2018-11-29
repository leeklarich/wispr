import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.sql.DriverManager;
import java.util.ArrayList;

public class Server extends Thread
{
    private ServerSocket server;
    private Socket sock;
    private DBConnenction conn;
    private ArrayList<ClientConnection> connections;
    public ArrayList<ChatRoom> rooms;
    public ArrayList<String> msgs;

    public ChatRoom connectChatRoom(ClientConnection cc, int roomNumber)
    {
        this.rooms.get(roomNumber).connect(cc);
        return this.rooms.get(roomNumber);
    }

    public void run()
    {
        try
        {
            conn = new DBConnenction();
            conn.createTable();
            conn.createFriendsTable();
            server = new ServerSocket(9001);
            connections = new ArrayList<>();
            msgs = new ArrayList<>();
            rooms = new ArrayList<>();

            for(int i = 0; i < 5; i++)
            {
                this.rooms.add(new ChatRoom(this));
            }
        }
        catch (Exception e)
        {
            System.out.println(e + "\nFailed to start server!");
        }

        while (true)
        {
            System.out.println("[Server] Waiting for connections...");

            try
            {
                sock = server.accept();
                connections.add(new ClientConnection(sock, this));
                connections.get(connections.size() - 1).start();
            }
            catch (IOException e)
            {
                System.out.println(e);
                System.out.println("Failed to add new client connection!");
            }
        }
    }

    public void broadcast(byte[] s, int roomNumber)
    {
        try
        {
            this.rooms.get(roomNumber).broadcast(s);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + "\nServer Line 49");
        }
    }

    public boolean verifyLogin(String username, String password)
    {
        return this.conn.getLogin(username, password);
    }

    public boolean makeFriends(String name1, String name2)
    {
        return this.conn.makeFriends(name1, name2);
    }

    public boolean removeFriends(String name1, String name2)
    {
        return this.conn.removeFriends(name1, name2);
    }

    public String[] getFriendsList(String name)
    {
        ArrayList<String> list = this.conn.getFriends(name);
        String[] newList = new String[list.size()];

        int index = 0;
        for(String s: list)
        {
            newList[index] = s;
            index++;
        }

        return newList;
    }

    public boolean createAccount(String username, String password)
    {
        return this.conn.insertDB(username, password);
    }
}
