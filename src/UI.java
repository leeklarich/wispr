import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class UI extends JFrame
{
    public Client client = new Client(this);
    private ChatUI chat;
    private MainUI login;
    private SplashUI splash;
    private FriendsBox fb;
    private JPanel main;
    private int state; // 0 - login page; 1 - splash page; 2 - chat page
    public String username;
    //public DBConnenction dbc;
    public ArrayList<String> fList;

    /**
     * This initializes the GUI.
     */
    public void init(int a)
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clearPanel();

        if (a == 0)
        {
            client.start();
            login = new MainUI(this);
            main.add(login);
            fList = new ArrayList<>();
            setTitle("Login");
        }

        else if (a == 1)
        {
            splash = new SplashUI(this);
            main.add(splash);

            try
            {
                this.client.getFriendsList(this.username);
                setTitle("Splash Page");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            catch (Exception e)
            {
                System.out.println("Failed to get friends list\nUI::init#1");
            }
            fb = new FriendsBox(this);
            for(String s : fList)
            {
                System.out.println(s);
            }
        }

        else if (a == 2)
        {
            chat = new ChatUI(this);
            main.add(chat);
            setTitle("Chat");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        pack();
        setVisible(true);
    }

    public void clearPanel()
    {
        try
        {
            main.removeAll();
        }
        catch(Exception e)
        {
            System.out.println("Couldn't remove panel!");
        }
    }

    public void setUsername(String s)
    {
        this.username = s;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void promptSuccessfulLogin()
    {
        JOptionPane.showMessageDialog(null, "Logging In As " + this.username);
        this.init(1);
    }

    public void promptFailedLogin()
    {
        JOptionPane.showMessageDialog(null, "You Have Entered The Wrong Credentials!");
        this.login.clearTextFields();
    }

    public void promptSuccessfulAccountCreation()
    {
        JOptionPane.showMessageDialog(null, "You Have Created The Account: " + this.username + "!");
        this.init(1);
    }

    public void promptFailedFriendAdd()
    {
        JOptionPane.showMessageDialog(null, "That user is invalid!");
    }

    public ArrayList<String> getFriendsList()
    {
        return this.fList;
    }

    public void setFriendsList(String[] l)
    {
        ArrayList<String> list = new ArrayList<String>();

        for(String s: l)
        {
            list.add(s);
        }

        this.fList = list;
        this.updateFriendsListView();
    }

    public void updateFriendsListView()
    {
        this.fb.getFriends();
        this.fb.displayFriends();
    }

    public void addFriend(String name)
    {
        this.client.addFriend(name);
    }

    public void removeFriend(String name)
    {
        this.client.removeFriend(name);
    }

    public void sendLoginInformation(String username, String password)
    {
        this.client.sendLoginInfo(username, password);
    }

    public void sendNewAccountInfo(String username, String password)
    {
        this.client.sendNewAccountInfo(username, password);
    }

    public UI(int a) 
    {
        //this.dbc = new DBConnenction();
        init(a);
    }
    
    public UI()
    {
        this.main = new JPanel();
        add(main);
        //this.dbc = new DBConnenction();
        init(0);
    }
}