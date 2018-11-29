import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FriendsBox extends JFrame
{
    ArrayList<String> friendsList = new ArrayList<>();
    JPanel friends = new JPanel();
    JPanel fMgmt = new JPanel(new GridBagLayout());
    JButton fAdd = new JButton("+");
    JButton fRemove = new JButton("-");
    JLabel fLabel = new JLabel("Friends: ");
    JTextArea fList = new JTextArea(10, 15);
    JTextField fName = new JTextField(15);

    GridBagConstraints c = new GridBagConstraints();
    private UI parent;

    public FriendsBox(UI p)
    {
        try
        {
            this.parent = p;
            this.friendsList = this.parent.getFriendsList();
        }
        catch (Exception e)
        {
            System.out.println(e + "\nFB::constructor");
        }
        init();
    }

    void init()
    {
        fAdd.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addFriend(fName.getText());
                fName.setText("");
            }
        });

        fRemove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                removeFriend(fName.getText());
                fName.setText("");
            }
        });

        fList.setEditable(false);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;

        fMgmt.add(fLabel, c);

        c.gridx = 1;

        fMgmt.add(fName, c);

        c.gridx = 0;
        c.gridy = 1;

        fMgmt.add(fAdd, c);

        c.gridx = 1;

        fMgmt.add(fRemove, c);

        c.gridy = 2;
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0;
        c.gridx = 0;
        c.gridwidth = 3;

        fMgmt.add(fList, c);

        fMgmt.setBackground(new java.awt.Color(51, 153, 255));

        this.add(fMgmt);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        try
        {
            getFriends();
            displayFriends();
        }
        catch (Exception e)
        {
            System.out.println(e + "\nFB::init#friends list aquisition");
        }
    }

    void getFriends()
    {
        try
        {
            this.friendsList = this.parent.getFriendsList();
        }
        catch(Exception e)
        {
            System.out.println("Failed to get friends in FriendsBox");
        }
    }

    void displayFriends()
    {
        try
        {
            this.fList.setText("");
            for(String s : this.friendsList)
            {
                this.fList.append(s + "\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e + "\nFB::displayFriends");
            System.out.println((this.fList == null) + "\n" + (this.friendsList == null));
        }
    }

    void addFriend(String name)
    {
        this.parent.addFriend(name);
        getFriends();
        displayFriends();
    }

    void removeFriend(String name)
    {
        this.parent.removeFriend(name);
        getFriends();
        displayFriends();
    }
}
