import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class UI extends JFrame {
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
        client.start();
        main = new JPanel();
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

        main.setLayout(new GridLayout(2,1));
        main.setBorder(BorderFactory.createEmptyBorder(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN));
        main.add(contentDisplay);
        main.add(input);

        setTitle(title);
        //setPreferredSize(frameDimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(main);
        pack();
        setVisible(true);
    }

    public void run() {
        while(true) {
            String str = client.getResponse();
            if(str != "") {
                contentDisplay.append("\n" + str);
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
