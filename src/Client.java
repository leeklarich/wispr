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
                Encryptor enc = new Encryptor("mathematical2001");
                Bundle bun = (Bundle) input.readObject();
                byte[] resp = bun.getMsg();
                resp = enc.decrypt(resp);
                queue.add(new String(resp));
                if(resp.toString().equalsIgnoreCase("exit")) break;
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

    public void sendMsg(byte[] s) {
        try {
            output.writeObject(new Bundle(s));
            System.out.println("[Sending] " + s.toString());
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
