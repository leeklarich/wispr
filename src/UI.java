import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class UI extends JFrame {
    private Client client;
    private ChatUI chat;
    private LoginUI login;
    private SplashUI splash;
    private int state; // 0 - login page; 1 - splash page; 2 - chat page

    /**
     * This initializes the GUI.
     */
    public void init() {
        client.start();
        chat = new ChatUI(client);
        setTitle("devUI");
        //setPreferredSize(frameDimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(chat);
        this.state = 2;
        pack();
        setVisible(true);
    }

    public void run() {
        while(true) {
           if(state == 2) {
               String str = client.getResponse();
               if(str != "") {
                   chat.displayMessage("\n" + str);
               }
           }
        }
    }

    public UI(Client c) {
        this.client = c;
        init();
        System.out.println("Congratulations on running your very own copy of Wispr!");
        run();
    }
}
