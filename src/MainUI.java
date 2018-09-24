import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

class MainUI extends JFrame {
    int MAIN_MARGIN = 10;
    String title = "dev:MainUI";
    Dimension frameDimension = new Dimension(1000,1000);
    private JLabel mainLabel, enterUsername, enterPass;
    private JButton joinStart;
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
                UI gui = new UI(new Client());
                dispose();
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
               UI gui = new UI(new Client());
               dispose();
            }
        });
    }

    
    

void init() 
    {
        //JFrame frame = new JFrame("Wispr");
        this.setTitle("Wispr");
        JPanel main = new JPanel(new GridBagLayout());
        this.setSize(frameDimension);
        GridBagConstraints cs = new GridBagConstraints();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        joinStart = new JButton("Click To Login");
        joinStart.setMnemonic(KeyEvent.VK_ENTER); //Not working but doesn't harm the program to my knowledge
        
        mainLabel = new JLabel("Welcome to Wispr!", SwingConstants.CENTER);
        mainLabel.setBorder(BorderFactory.createEmptyBorder(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN));
        cs.gridx = 10;
        cs.gridy= 0;
        cs.gridwidth=1;
        main.add(mainLabel,cs);
        
        cs.gridx = 10;
        cs.gridy=220;
        cs.gridwidth = 1;
        main.add(joinStart,cs);

        enterUsername = new JLabel("Enter Your Username", SwingConstants.CENTER);
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

        enterPass = new JLabel("Enter Your Password", SwingConstants.CENTER);
        enterPass.setBorder(BorderFactory.createEmptyBorder(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN));
        cs.gridx = 0 ;
        cs.gridy= 150 ;
        cs.gridwidth= 1;
        main.add(enterPass,cs);

        JTextField PassIn = new JTextField(50);
        cs.gridx = 10;
        cs.gridy = 150;
        cs.gridwidth = 1;
        main.add(PassIn,cs);
        
        setTitle(title);
        this.add(main,BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    
    public MainUI() 
    {
        init();
        System.out.println("Welcome to Wispr!");
    }
}


