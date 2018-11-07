import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FriendsBox extends JFrame {
    ArrayList<String> friendsList = new ArrayList<>();
    String[] users = {"lee", "will", "user", "wholley", "sydney", "jawad"};
    JPanel friends = new JPanel();
    JPanel fMgmt = new JPanel(new GridBagLayout());
    JButton fAdd = new JButton("+");
    JButton fRemove = new JButton("-");
    JLabel fLabel = new JLabel("Friends: ");
    JTextArea fList = new JTextArea(10, 15);
    JTextField fName = new JTextField(15);

    GridBagConstraints c = new GridBagConstraints();
    private UI parent;

    public FriendsBox(UI p) {
        this.parent = p;
        init();
    }

    void init() {
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

        this.add(fMgmt);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        getFriends();
    }

    void getFriends() {
        fList.setText("");
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("./lst.txt"));
            line = br.readLine();
            System.out.println(line);
            String[] list = line.split(",");
            for(String s : list) {
                friendsList.add(s);
            }
            displayFriends();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        /*Scanner scan = new Scanner("lst.txt");
        scan.next();
        if(scan.hasNext()) {
            String[] list = scan.next().split(",");
            for(String s : list) {
                friendsList.add(s);
                fList.append(s + "\n");
            }
            scan.close();
        }*/
    }

    void updateFriends() {
        String str = "";
        for(String s : friendsList) {
            str += s + ",";
        }
        str = str.substring(0,str.length());
        File file;
        try {
            file = new File("./lst.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(str);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void displayFriends() {
        fList.setText("");
        for(String s : friendsList) {
            fList.append(s + "\n");
        }
    }

    int addFriend(String name) {
        for(String s : users) {
            if(s.equals(name)) {
                System.out.println("Added friend: " + name);
                friendsList.add(name);
                displayFriends();
                return 1;
            }
        }
        JOptionPane.showMessageDialog(null, "User " + name + " does not exist!");
        return 0;
    }

    void removeFriend(String name) {
        if(friendsList.contains(name)) {
            friendsList.remove(name);
            displayFriends();
        }
        else {
            fName.setText("");
        }
    }
}
