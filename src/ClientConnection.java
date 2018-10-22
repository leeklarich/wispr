import java.io.*;
import java.net.*;

public class ClientConnection extends Thread {
    protected Socket sock;
    private ObjectOutputStream out;
    private Server serv;

    public ClientConnection(Socket client, Server s) {
        this.serv = s;
        this.sock = client;
    }

    public void run() {
        ObjectInputStream in = null;
        out = null;
        BufferedReader br = null;

        try {
            in = new ObjectInputStream(sock.getInputStream());
            br = new BufferedReader(new InputStreamReader(in));
            out = new ObjectOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }

        String line;

        while(true) {
            try {
                line = (String) in.readObject();
                System.out.println("[Server] Received message: " + line);
                this.serv.broadcast(line);
                if(line.equalsIgnoreCase("exit")) break;
            } catch(Exception e) {
                System.out.println(e);
                break;
            }
        }

        try {
            in.close();
            out.close();
            br.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void broadcast(String str) {
        try {
            out.writeObject(str);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
