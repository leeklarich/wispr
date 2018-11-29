import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread
{
    private Socket sock;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    public ArrayList<String> queue = new ArrayList<>();
    private UI master;

    public void run()
    {
        try
        {
            InetAddress host = InetAddress.getLocalHost();
            sock = new Socket(host.getHostName(), 9001);
            System.out.println("[Client] Connection complete!");
            output = new ObjectOutputStream(sock.getOutputStream());

            input = new ObjectInputStream(sock.getInputStream());

            while(true)
            {
                Encryptor enc = new Encryptor("mathematical2001");
                Bundle bun = (Bundle) input.readObject();
                /*
                byte[] resp = bun.getMsg();
                resp = enc.decrypt(resp);
                queue.add(new String(resp));
                if(resp.toString().equalsIgnoreCase("exit")) break;*/

                int action = bun.getAction();

                System.out.println(action);

                // Received msg
                if(action == 1)
                {
                    byte[] resp = bun.getMsg();
                    resp = enc.decrypt(resp);
                    queue.add(new String(resp));
                }

                // Verify login
                else if(action == 2)
                {
                    if(bun != null)
                    {
                        String[] pl = bun.getPayload();

                        if (pl.length == 2)
                        {
                            this.master.setUsername(pl[1]);
                            this.master.promptSuccessfulLogin();
                        }
                        else
                        {
                            this.master.promptFailedLogin();
                        }
                    }
                    else
                    {
                        System.out.println("Client login sequence: null payload");
                    }
                }

                // Add friend
                else if(action == 3)
                {
                    String[] pl = bun.getPayload();

                    if(pl.length == 1)
                    {
                        if(pl[0].equals("Success"))
                        {
                            System.out.println("Successfully added friend");
                            this.getFriendsList(this.master.getUsername());
                            this.master.updateFriendsListView();
                        }
                        else
                        {
                            System.out.println("Failed to add friend");
                            this.master.promptFailedFriendAdd();
                        }
                    }
                }

                // Remove friend
                else if(action == 4)
                {
                    String[] pl = bun.getPayload();

                    if(pl.length == 1)
                    {
                        if(pl[0].equals("Success"))
                        {
                            System.out.println("Successfully removed friend");
                            this.getFriendsList(this.master.getUsername());
                            this.master.updateFriendsListView();
                        }
                        else
                        {
                            System.out.println("Failed to remove friend");
                        }
                    }
                }

                // Get friends list
                else if(action == 5)
                {
                    try
                    {
                        String[] pl = bun.getPayload();

                        this.master.setFriendsList(pl);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Client::run#5");
                    }
                }

                // Create account
                else if(action == 6)
                {
                    String[] pl = bun.getPayload();

                    if(pl.length == 2)
                    {
                        if(pl[0].equals("Success"))
                        {
                            System.out.println("Successfully made account");
                            this.master.setUsername(pl[1]);
                            this.master.promptSuccessfulAccountCreation();
                        }
                    }
                    else
                    {
                        System.out.println("Failed to create account");
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e + "\nClient::run");
            System.out.println("Failed to run client and make connection!");
        }
    }

    public void kill()
    {
        try
        {
            output.close();
            input.close();
            sock.close();
        }
        catch(Exception e)
        {
            System.out.println("Error killing client streams!");
        }
    }

    public void sendMsg(byte[] s)
    {
        try
        {
            output.writeObject(new Bundle(s));
            System.out.println("[Sending] " + s.toString());
        }
        catch(Exception e)
        {
            System.err.println(e + "\nClient::sendMsg");
            System.out.println("Failed to send message!");
        }
    }

    public void changeRoom(int id)
    {
        try
        {
            output.writeObject(new Bundle(id));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + "\nClient::changeRoom");
        }
    }

    public void addFriend(String name)
    {
        try
        {
            String[] list = {this.master.getUsername(), name};
            output.writeObject(new Bundle(3, list));
        }
        catch(Exception e)
        {
            System.out.println(e + "\nClient::addFriend");
            System.out.println("Failed to add friend");
        }
    }

    public void removeFriend(String name)
    {
        try
        {
            String[] list = {this.master.getUsername(), name};
            output.writeObject(new Bundle(4, list));
        }
        catch(Exception e)
        {
            System.out.println(e + "\nClient::removeFriend");
            System.out.println("Failed to remove friend");
        }
    }

    public void sendLoginInfo(String username, String password)
    {
        try
        {
            String[] list = {username, password};
            output.writeObject(new Bundle(2, list));
        }
        catch (Exception e)
        {
            System.out.println(e + "\nClient::sendLoginInfo");
        }
    }

    public void sendNewAccountInfo(String username, String password)
    {
        try
        {
            String[] list = {username, password};
            output.writeObject(new Bundle(6, list));
        }
        catch(Exception e)
        {
            System.out.println(e + "\nClient::sendNewAccountInfo");
        }
    }

    public void getFriendsList(String username)
    {
        try
        {
            String[] list = {username};
            output.writeObject(new Bundle(5, list));
        }
        catch(Exception e)
        {
            System.out.println(e + "\nClient::getFriendsList");
        }
    }

    public Client(UI ui)
    {
        this.master = ui;
    }
}
