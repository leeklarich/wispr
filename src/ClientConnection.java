import java.io.*;
import java.net.*;

public class ClientConnection extends Thread {
    protected Socket sock;
    private ObjectOutputStream out;
    private Server serv;
    private ChatRoom room;

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
            System.out.println(e + "\nLine 24");
        }

        while(true)
        {
            try
            {
                Bundle bundle = (Bundle) in.readObject();
                /*System.out.println("[Server] Received message: " + line);
                this.room.broadcast(line);
                if(line.equalsIgnoreCase("exit")) break;*/
                switch(bundle.getAction()) {
                    case 0:
                        // Switch room
                        this.connect(bundle.getRoomNumber());
                        break;
                    case 1:
                        // Send msg
                        this.room.broadcast(bundle.getMsg());
                        break;
                    default:
                        break;
                }
            }
            catch(Exception e)
            {
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

    public void broadcast(byte[] str)
    {
        try {
            out.writeObject(new Bundle(str));
        } catch(Exception e) {
            System.out.println(e.getMessage() + "\nCC broadcast issue");
        }
    }

    public void connect(int id)
    {
        if(this.room != null) {
            this.room.disconnect(this);
        }
        this.room = this.serv.connectChatRoom(this, id);
    }

    public ChatRoom getRoom()
    {
        return this.room;
    }
}
