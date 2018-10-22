import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.jdesktop.layout.*;
/*
This will be the view class for the chat room and friends list
The user will be able to join a chat room, leave a chat room, or enter a chat room on this page
The user will also be able to add/delete/start private chat with friends from this page
Once a chat is entered, this view will be replaced in the client application with the chat room UI view

 */
public class SplashUI extends JPanel {
    private UI parent;
    JPanel splash = new JPanel(new GridBagLayout());
     
     
     void init() 
    {
        setBackground(new java.awt.Color(51, 153, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setForeground(new java.awt.Color(0, 102, 153));
        JLabel TitleLabel = new javax.swing.JLabel();
        JButton Enter1 = new javax.swing.JButton();
        JLabel RoomName1 = new javax.swing.JLabel();
        JLabel RoomName2 = new javax.swing.JLabel();
        JLabel RoomName3 = new javax.swing.JLabel();
        JLabel RoomName4 = new javax.swing.JLabel();
        JButton Enter2 = new javax.swing.JButton();
        JButton Enter3 = new javax.swing.JButton();
        JButton Enter4 = new javax.swing.JButton();
        JButton JoinARoom = new javax.swing.JButton();
        JButton Exit = new javax.swing.JButton();
        JLabel RoomCount1 = new javax.swing.JLabel();
        JLabel RoomCount3 = new javax.swing.JLabel();
        JLabel RoomCount2 = new javax.swing.JLabel();
        JLabel RoomCount4 = new javax.swing.JLabel();

        TitleLabel.setText("WISPR");

        

        RoomName1.setText("The Batcave ");

        RoomName2.setText("Room Two");

        RoomName3.setText("Meme Depot");

        RoomName4.setText("Room Four");
        
        
        Enter1.setText("Enter");
        Enter1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                   System.out.println("Entering " + RoomName1.getText());
                   parent.init(2);
            }
        });

        Enter2.setText("Enter");
        Enter2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                   System.out.println("Entering " + RoomName2.getText());
                   parent.init(2);
            }
        });


        Enter3.setText("Enter");
        Enter3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                   System.out.println("Entering " + RoomName3.getText());
                   parent.init(2);
            }
        });
      

        Enter4.setText("Enter");
        Enter4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                   System.out.println("Entering " + RoomName4.getText());
                   parent.init(2);
            }
        });

        JoinARoom.setText("Join A Chat Room");

        Exit.setText("Exit");
        Exit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(1);
            }
        });

        RoomCount1.setText("");

        RoomCount3.setText("");

        RoomCount2.setText("");

        RoomCount4.setText("");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(TitleLabel))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(Exit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(JoinARoom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(RoomName4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(RoomName3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(RoomName2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(RoomName1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(85, 85, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, Enter3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(RoomCount4)
                                    .add(RoomCount3)
                                    .add(RoomCount1)
                                    .add(RoomCount2))
                                .add(63, 63, 63)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, Enter1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, Enter2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, Enter4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                .add(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(TitleLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(31, 31, 31)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Enter1)
                    .add(RoomName1)
                    .add(RoomCount1))
                .add(36, 36, 36)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(RoomName2)
                    .add(Enter2)
                    .add(RoomCount2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(37, 37, 37)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Enter3)
                    .add(RoomName3)
                    .add(RoomCount3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(32, 32, 32)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Enter4)
                    .add(RoomName4)
                    .add(RoomCount4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 104, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Exit)
                    .add(JoinARoom))
                .addContainerGap())
        );
    }// </editor-fold>                        
    
 public SplashUI(UI main)
    {
        this.parent = main;
        this.init();
    }
}

