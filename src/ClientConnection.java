import java.io.*;
import java.net.*;

public class ClientConnection extends Thread {
    protected Socket sock;

    public ClientConnection(Socket client) {
        this.sock = client;
    }

    public void run() {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
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
                out.writeObject("User: " + line);

                if(line.equalsIgnoreCase("exit")) break;
            } catch (Exception e) {
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
}
