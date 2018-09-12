import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class MainUI extends JFrame {
    int MAIN_MARGIN = 10;
    String title = "dev:MainUI";
    Dimension frameDimension = new Dimension(1000,1000);
    private JLabel mainLabel, hostLabel, joinLabel,enterUsername;
    private JButton hostStart, joinStart;
    private String input;
    private String IP;

     public void hostButtonListener(JButton b,JTextField a) 
     {
        b.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
               input = a.getText();
                if(input.isEmpty())
               {
                   input = "Unknown";
               }
               System.out.println("Creating Host Environment as " + input);
             
            }
        });
    }
     
    public void joinButtonListener(JButton b,JTextField a, JTextField d) 
     {
        b.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
               IP = d.getText();
               input = a.getText();
               if(input.isEmpty())
               {
                   input = "Unknown";
               }
               System.out.println("Joining Host " + IP +  " as "  + input);
            }
        });
    }

    
    

void init() 
    {
        JFrame frame = new JFrame("Wispr");
        JPanel main = new JPanel(new GridBagLayout());
        frame.setSize(frameDimension);
        GridBagConstraints cs = new GridBagConstraints();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        hostStart = new JButton("Click To Host");
        hostStart.setMnemonic(KeyEvent.VK_ENTER); //Not working but doesn't harm the program to my knowledge
       
        
        joinStart = new JButton("Click To Join");
        joinStart.setMnemonic(KeyEvent.VK_ENTER); //Not working but doesn't harm the program to my knowledge
      
        
        
        
        mainLabel = new JLabel("Welcome to Wispr!", SwingConstants.CENTER);
        mainLabel.setBorder(BorderFactory.createEmptyBorder(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN));
        cs.gridx = 10;
        cs.gridy= 0;
        cs.gridwidth=1;
        main.add(mainLabel,cs);
        
        hostLabel = new JLabel("Host A Server ", SwingConstants.CENTER);
        hostLabel.setBorder(BorderFactory.createEmptyBorder(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN));
        cs.gridx = 0;
        cs.gridy= 15;
        cs.gridwidth=1;
        main.add(hostLabel,cs);
        cs.gridx = 10;
        cs.gridy= 15;
        cs.gridwidth=1;
        main.add(hostStart,cs);
        
        joinLabel = new JLabel("Join A Server (Enter Host IP)", SwingConstants.CENTER);
        joinLabel.setBorder(BorderFactory.createEmptyBorder(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN));
        cs.gridx = 0;
        cs.gridy=30;
        cs.gridwidth=1;
        main.add(joinLabel,cs);
    
        
        JTextField IpIn = new JTextField(50);
        cs.gridx = 10;
        cs.gridy = 30;
        cs.gridwidth = 1;
        main.add(IpIn,cs);
        
        cs.gridx = 10;
        cs.gridy=50;
        cs.gridwidth = 1;
        main.add(joinStart,cs);
        
      
        
        enterUsername = new JLabel("Enter Your Username (Optional)", SwingConstants.CENTER);
        enterUsername.setBorder(BorderFactory.createEmptyBorder(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN));
        cs.gridx = 0 ;
        cs.gridy= 100 ;
        cs.gridwidth= 1;
        main.add(enterUsername,cs);
        
        JTextField UserIn = new JTextField(50);
        cs.gridx = 10;
        cs.gridy = 100;
        cs.gridwidth = 1;
        main.add(UserIn,cs);
        
        hostButtonListener(hostStart,UserIn);
        joinButtonListener(joinStart,UserIn, IpIn);
        
        
        setTitle(title);
        frame.add(main,BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    
    public MainUI() 
    {
        init();
        System.out.println("Welcome to Wispr!");
    }
}


