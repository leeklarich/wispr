import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class UI extends JFrame {
    private Client client = new Client();
    private ChatUI chat;
    private MainUI login;
    private SplashUI splash;
    private int state; // 0 - login page; 1 - splash page; 2 - chat page

    /**
     * This initializes the GUI.
     */
    public void init(int a) {
        if (a == 0)
        {
            client.start();
            login = new MainUI();
            setTitle("Login");
            //setPreferredSize(frameDimension);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(login);
            pack();
            setVisible(true);
        }
        
        if (a == 1)
        {
            //client.start();
            splash = new SplashUI();
            setTitle("Splash Page");
            //setPreferredSize(frameDimension);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(splash);
            pack();
            setVisible(true);
        }
        
        if (a == 2)
        {
            //client.start();
            chat = new ChatUI();
            setTitle("Chat");
            //setPreferredSize(frameDimension);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(chat);
            pack();
            setVisible(true);
        }
    }
    /*
    public void run() {
        while(true) {
           if(state == 2) {
               client.start();
               String str = client.getResponse();
               if(str != "") {
                   chat.displayMessage("\n" + str);
               }
           }
        }
    }
*/
    public UI(int a) 
    {
        init(a);
        System.out.println();
    }
    
    public UI()
    {
        init(0);
    }
}