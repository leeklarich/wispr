import java.io.*;
import java.net.*;

public class ClientConnection extends Thread
{
    protected Socket sock;
    private ObjectOutputStream out;
    private Server serv;
    private ChatRoom room;

    public ClientConnection(Socket client, Server s)
    {
        this.serv = s;
        this.sock = client;
    }

    public void run()
    {
        ObjectInputStream in = null;
        out = null;
        BufferedReader br = null;

        try
        {
            in = new ObjectInputStream(sock.getInputStream());
            br = new BufferedReader(new InputStreamReader(in));
            out = new ObjectOutputStream(sock.getOutputStream());
        }
        catch (IOException e)
        {
            System.out.println(e + "\nClientConnection::run");
        }

        while(true)
        {
            try
            {
                Bundle bundle = (Bundle) in.readObject();
                /*System.out.println("[Server] Received message: " + line);
                this.room.broadcast(line);
                if(line.equalsIgnoreCase("exit")) break;*/
                String name1, name2;
                System.out.println(bundle.getAction());

                switch(bundle.getAction())
                {
                    case 0:
                        // Switch room
                        try
                        {
                            this.connect(bundle.getRoomNumber());
                        }
                        catch (Exception e)
                        {
                            System.out.println(e + "\nCC::run#case0");
                        }
                        break;

                    case 1:
                        // Send msg
                        try
                        {
                            this.room.broadcast(bundle.getMsg());
                        }
                        catch (Exception e)
                        {
                            System.out.println(e + "\nCC::run#case1");
                        }
                        break;

                    case 2:
                        // Verify login
                        String username = bundle.getPayload()[0];
                        String password = bundle.getPayload()[1];

                        try
                        {
                            if(this.verifyLogin(username, password))
                            {
                                String[] list = {"Success", username};
                                out.writeObject(new Bundle(2, list));
                            }
                            else
                            {
                                String[] list = {"Failure"};
                                out.writeObject(new Bundle(2, list));
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                        break;

                    case 3:
                        // Add friend
                        name1 = bundle.getPayload()[0];
                        name2 = bundle.getPayload()[1];

                        try
                        {
                            if(this.serv.makeFriends(name1, name2))
                            {
                                String[] list = {"Success"};
                                out.writeObject(new Bundle(3, list));
                            }
                            else
                            {
                                String[] list = {"Failure"};
                                out.writeObject(new Bundle(3, list));
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                        break;

                    case 4:
                        // Remove friend
                        name1 = bundle.getPayload()[0];
                        name2 = bundle.getPayload()[1];

                        try
                        {
                            if (this.serv.removeFriends(name1, name2))
                            {
                                String[] list = {"Success"};
                                out.writeObject(new Bundle(4, list));
                            }
                            else
                            {
                                String[] list = {"Failure"};
                                out.writeObject(new Bundle(4, list));
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                        break;

                    case 5:
                        // Get friends list
                        String name = bundle.getPayload()[0];

                        try
                        {
                            String[] list = this.serv.getFriendsList(name);
                            out.writeObject(new Bundle(5, list));
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                        break;

                    case 6:
                        // Create account
                        String[] pl = bundle.getPayload();

                        try
                        {
                            if(this.serv.createAccount(pl[0], pl[1]))
                            {
                                String[] list = {"Success", pl[0]};
                                out.writeObject(new Bundle(6, list));
                            }
                            else
                            {
                                String[] list = {"Failure"};
                                out.writeObject(new Bundle(6, list));
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println(e);
                        }
                        break;

                    default:
                        break;
                }
            }
            catch(Exception e)
            {
                System.out.println(e + "\nCC::run#1");
                break;
            }
        }

        try
        {
            in.close();
            out.close();
            br.close();
        }
        catch(Exception e)
        {
            System.out.println(e + "\nCC::run#2");
        }
    }

    public void broadcast(byte[] str)
    {
        try
        {
            out.writeObject(new Bundle(str));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage() + "\nCC broadcast issue");
        }
    }

    public void connect(int id)
    {
        if(this.room != null)
        {
            this.room.disconnect(this);
        }

        try
        {
            this.room = this.serv.connectChatRoom(this, id);
        }
        catch (Exception e)
        {
            System.out.println(e + "\nCC::connect");
        }
    }

    public boolean verifyLogin(String username, String password)
    {
        return this.serv.verifyLogin(username, password);
    }

    public ChatRoom getRoom()
    {
        return this.room;
    }
}
