import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import org.jdesktop.beansbinding.*;
import org.jdesktop.layout.*;

class MainUI extends JPanel {
    JLabel UsernameLabel = new JLabel();
    TextField userField = new java.awt.TextField();
    JLabel PasswordLabel = new javax.swing.JLabel();
    TextField PassField = new java.awt.TextField();
    JLabel TitleLabel = new javax.swing.JLabel();
    JButton LoginButton = new javax.swing.JButton();
    JButton CreateAccButton = new javax.swing.JButton();
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    private UI parent;

void init() 
    {  
        
        setBackground(new java.awt.Color(51, 153, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setForeground(new java.awt.Color(0, 102, 153));
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        UsernameLabel = new javax.swing.JLabel();
        userField = new java.awt.TextField();
        PasswordLabel = new javax.swing.JLabel();
        PassField = new java.awt.TextField();
        TitleLabel = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        CreateAccButton = new javax.swing.JButton();

        UsernameLabel.setText("Username: ");

        userField.setText("");

        PasswordLabel.setText("Password:");

        PassField.setText("");
        PassField.setEchoChar('*');
        
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, PassField, org.jdesktop.beansbinding.ELProperty.create("${echoChar}"), PassField, org.jdesktop.beansbinding.BeanProperty.create("echoChar"));
        bindingGroup.addBinding(binding);

        TitleLabel.setText("WISPR");

        LoginButton.setText("Login");
        LoginButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                /*if(userField.getText().equals("william") && PassField.getText().equals("holley"))
                {
                    JOptionPane.showMessageDialog(null, "Logging In As " + userField.getText());
                    parent.setUsername(userField.getText());
                    parent.changeState(1);
                }*/
                if(parent.dbc.getLogin(userField.getText(), PassField.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Logging In As " + userField.getText());
                    parent.setUsername(userField.getText());
                    parent.init(1);
                }
                else 
                    JOptionPane.showMessageDialog(null, "You Have Entered The Wrong Credentials!");
            }
        });

        CreateAccButton.setText("Create Account");
        CreateAccButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    parent.dbc.insertDB(userField.getText(), PassField.getText());
                    JOptionPane.showMessageDialog(null, "You Have Created The Account " + userField.getText() + "!");
                    parent.setUsername(userField.getText());
                    parent.init(1);
                }
                catch(Exception ex)
                {
                    System.out.println("Error creating user!");
                }
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(TitleLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(153, 153, 153))
            .add(layout.createSequentialGroup()
                .add(83, 83, 83)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(CreateAccButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(LoginButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(layout.createSequentialGroup()
                            .add(PasswordLabel)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(PassField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                            .add(UsernameLabel)
                            .add(5, 5, 5)
                            .add(userField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(63, 63, 63)
                .add(TitleLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(37, 37, 37)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(userField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(UsernameLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(PasswordLabel)
                    .add(PassField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 30, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(CreateAccButton)
                    .add(LoginButton))
                .add(63, 63, 63))
        );
        bindingGroup.bind();
    }

   public MainUI(UI main)
    {
        this.parent = main;
        this.init();
        System.out.println("Welcome to Wispr!");
    }
}
