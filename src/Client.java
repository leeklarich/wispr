import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread {
    private Socket sock;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    public ArrayList<String> queue = new ArrayList<>();
    private String username;

    public void run() {
        try {
            InetAddress host = InetAddress.getLocalHost();
            sock = new Socket(host.getHostName(), 9001);
            System.out.println("[Client] Connection complete!");
            output = new ObjectOutputStream(sock.getOutputStream());

            input = new ObjectInputStream(sock.getInputStream());

            while(true) {
                String resp = (String) input.readObject();
                queue.add(resp);
                if(resp.equalsIgnoreCase("exit")) break;
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to run client and make connection!");
        }
    }

    public void kill() {
        try {
            output.close();
            input.close();
            sock.close();
        } catch(Exception e) {
            System.out.println("Error killing client streams!");
        }
    }

    // Deprecated!
    public String getResponse() {
        String str = "";
        try {
            str = (String) input.readObject();
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Failed to get response!");
        }
        return str;
    }

    public void sendMsg(String s) {
        try {
            output.writeObject(new Bundle(s));
            System.out.println("[Sending] " + s);
        } catch(Exception e) {
            System.err.println(e);
            System.out.println("Failed to send message!");
        }
    }

    public void changeRoom(int id) {
        try {
            output.writeObject(new Bundle(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
