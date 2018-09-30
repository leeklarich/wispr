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
    private ArrayList<String> queue = new ArrayList<>();

    public void run() {
        try {
            InetAddress host = InetAddress.getLocalHost();
            sock = new Socket(host.getHostName(), 9001);
            System.out.println("[Client] Connection complete!");
            output = new ObjectOutputStream(sock.getOutputStream());

            input = new ObjectInputStream(sock.getInputStream());

            //while(true) {
             //   String resp = (String) input.readObject();
             //   queue.add("[Response] " + resp);
             //   System.out.println("[Client] Received: " + resp);
             //   if(resp.equalsIgnoreCase("exit")) break;
            //}
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

    public ArrayList<String> getContent() {
        final ArrayList<String> q = new ArrayList<>(queue);
        queue.clear();
        return q;
    }

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
            output.writeObject(s);
            System.out.println("[Sending] " + s);
        } catch(Exception e) {
            System.err.println(e);
            System.out.println("Failed to send message!");
        }
    }
}
