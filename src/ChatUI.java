import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/*
This will be the view for the chat room
From this view, the user will be able to enter a message to send to the connected chat room
This view will display all messages sent and received to the chat room
When this view is closed, the view will be changed back to the SplashUI view
 */
public class ChatUI extends JPanel {
    int MAIN_MARGIN = 10;
    String title = "dev:UI";
    Dimension frameDimension = new Dimension(800,300);
    private JPanel main, input;
    private JTextArea contentDisplay;
    private JTextField msg;
    private JButton send;
    private Client client;

    /**
     * This is to attach an ActionListener to a JButton, b.
     * The actionPerformed method can be edited to change the functionality of the button.
     * @param b - JButton
     */
    public void addButtonListener(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = msg.getText();
                //contentDisplay.append("\n" + str);
                client.sendMsg(str);
                msg.setText("");
                System.out.println(client.getContent());
            }
        });
    }

    /**
     * This initializes the GUI.
     */
    public void init() {
        input = new JPanel();
        contentDisplay = new JTextArea("Welcome to Wispr!", 15, 50);
        msg = new JTextField(50);
        send = new JButton("Send");

        contentDisplay.setEditable(false);
        contentDisplay.setBorder(BorderFactory.createEmptyBorder(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN));

        send.setMnemonic(KeyEvent.VK_ENTER); //Not working but doesn't harm the program to my knowledge
        addButtonListener(send);

        input.add(msg);
        input.add(send);

        this.setLayout(new GridLayout(2,1));
        this.setBorder(BorderFactory.createEmptyBorder(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN));
        this.add(contentDisplay);
        this.add(input);
    }

    public void displayMessage(String s) {
        contentDisplay.append(s);
    }

    public ChatUI(Client c) {
        this.client = c;
        this.init();
    }
}
