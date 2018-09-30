import javax.swing.*;
/*
This class will create an alert box to warn the user of necessary messages
Once the ok button is pressed, the alert box will disappear
 */
public class AlertBox extends JFrame {
    private JButton ok;
    private JLabel msg;
    private JPanel main;

    public void init() {
        ok = new JButton("OK");
        msg = new JLabel();
    }
}
