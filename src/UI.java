import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class UI extends JFrame {
    public Client client = new Client();
    private ChatUI chat;
    private MainUI login;
    private SplashUI splash;
    private JPanel main;
    private int state; // 0 - login page; 1 - splash page; 2 - chat page
    public String username;
    public DBConnenction dbc;

    /**
     * This initializes the GUI.
     */
    public void init(int a) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clearPanel();
        if (a == 0)
        {
            client.start();
            login = new MainUI(this);
            main.add(login);
            setTitle("Login");
        }

        if (a == 1)
        {
            splash = new SplashUI(this);
            main.add(splash);
            FriendsBox fb = new FriendsBox(this);
            setTitle("Splash Page");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        if (a == 2)
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
        try {
            main.removeAll();
        } catch(Exception e) {
            System.out.println("Couldn't remove panel!");
        }
    }

    public void setUsername(String s)
    {
        this.username = s;
    }

    public UI(int a) 
    {
        this.dbc = new DBConnenction();
        init(a);
    }
    
    public UI()
    {
        this.main = new JPanel();
        add(main);
        this.dbc = new DBConnenction();
        init(0);
    }
}