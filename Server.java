import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.sql.DriverManager;
import java.util.ArrayList;

public class Server extends Thread {
    private ServerSocket server;
    private Socket sock;
    private DBConnenction conn;
    private ArrayList<ClientConnection> connections;

    public void connect(String url) {
        this.conn.connect();
    }

    public void run() {
        try {
            server = new ServerSocket(9001);
            connections = new ArrayList<>();

        } catch (Exception e) {
            System.out.println("Failed to start server!");
        }

        while (true) {
            System.out.println("[Server] Waiting for connections...");

            try {
                sock = server.accept();
                connections.add(new ClientConnection(sock));
                connections.get(connections.size() - 1).start();
            } catch (IOException e) {
                System.out.println(e);
                System.out.println("Failed to add new client connection!");
            }
        }
    }
}
