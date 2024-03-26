package Project;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginFrame extends JFrame implements ActionListener{
    
    //container
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    
    //panels
    JPanel imagePanel = new JPanel();
    JPanel infoPanel = new JPanel();
    
    
    JPanel loginPanel = new JPanel();
    JPanel createPanel = new JPanel();
    
    JPanel buttonPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    
    JPanel namePanel = new JPanel();
    JPanel passPanel = new JPanel();
    
    JPanel loginButtonPanel = new JPanel();
    //label
    JLabel imageLabel = new JLabel(new ImageIcon("LoginBanner.jpg"));
    JLabel forgotLabel = new JLabel("Forgot Password | Forgot Username");
    
    JLabel nameLabel = new JLabel("Username or Email:");
    JLabel passLabel = new JLabel("Password:");
    
    JTextField nameField = new JTextField();
    JPasswordField passField = new JPasswordField();
    
    JButton createButton = new JButton("Create Account");
    
    JButton loginButton = new JButton("Login as");
    
 
    JComboBox accountSelector;
    JCheckBox showpass = new JCheckBox();
    LoginFrame(){
    
        this.setTitle("Medical History Management System");//title name
        this.setBounds(300, 100, 720, 600);//size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close 
        this.setResizable(true);//resizable

        //icon
        this.setIconImage(logo.getImage());
        
        Initialize();
        addActionEvent();
    }
    public void Initialize(){
        
        container.setLayout(new GridLayout(1,2));
        container.setSize(720,600);
        container.setBackground(new Color(180, 228, 251));
                
        imagePanel.setOpaque(false);
        container.add(imagePanel);
        
        imageLabel.setOpaque(false);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel);
        
        infoPanel.setBackground(new Color(94, 191, 244));
        infoPanel.setLayout(new BorderLayout(20,20));
        container.add(infoPanel);
        
        
        topPanel.setPreferredSize(new Dimension(100,100));
        topPanel.setOpaque(false);
        infoPanel.add(topPanel,BorderLayout.NORTH);
        
        leftPanel.setPreferredSize(new Dimension(20,20));
        leftPanel.setOpaque(false);
        infoPanel.add(leftPanel,BorderLayout.EAST);
        
        rightPanel.setPreferredSize(new Dimension(20,20));
        rightPanel.setOpaque(false);
        infoPanel.add(rightPanel,BorderLayout.WEST);
        
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(new Color(255,255,255));
        namePanel.add(nameLabel);
        
        
        namePanel.add(nameField);
        
        namePanel.setLayout(new GridLayout(2,1,10,10));
        namePanel.setOpaque(false);
        loginPanel.add(namePanel);
        
        
        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passLabel.setForeground(new Color(255,255,255));
        passPanel.add(passLabel);
        
        
        passPanel.add(passField);
        
        passPanel.setLayout(new GridLayout(2,1,10,10));
        passPanel.setOpaque(false);
        loginPanel.add(passPanel);
        
        
        
        loginButtonPanel.add(loginButton);
        
        
        
        loginButtonPanel.setLayout(new FlowLayout());
        loginButtonPanel.setOpaque(false);
        loginButtonPanel.add(loginButton);
        loginPanel.add(loginButtonPanel);
        
        String[] accounts = {"Patient","Doctor"};
        accountSelector = new JComboBox(accounts);
        loginButtonPanel.add(accountSelector);
        
        loginPanel.setOpaque(false);
        loginPanel.setLayout(new GridLayout(3,1,10,10));
        //loginPanel.setPreferredSize(new Dimension(400,400));
        infoPanel.add(loginPanel,BorderLayout.CENTER);
        
        createPanel.setPreferredSize(new Dimension(150,150));
        createPanel.setOpaque(false);
        createPanel.setLayout(new BorderLayout(20,20));
        infoPanel.add(createPanel,BorderLayout.SOUTH);
        
        forgotLabel.setHorizontalAlignment(SwingConstants.CENTER);
        forgotLabel.setPreferredSize(new Dimension(createPanel.getWidth(), 30));
        createPanel.add(forgotLabel,BorderLayout.NORTH);
        
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setOpaque(false);
        createPanel.add(buttonPanel,BorderLayout.CENTER);
        buttonPanel.add(createButton);
        
        loginButtonPanel.add(showpass);
        showpass.setToolTipText("Show Password");
        
        forgotLabel.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e){
                forgotLabel.setForeground(Color.blue);
                close();
                Forgot test = new Forgot();
                test.setVisible(true);
                
            } 

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                forgotLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                forgotLabel.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                forgotLabel.setForeground(Color.black);
            }
        });
    }
    public void addActionEvent(){
        loginButton.addActionListener(this);
        createButton.addActionListener(this);
        accountSelector.addActionListener(this);
        showpass.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            loginCheck();
        }
        else if(e.getSource() == createButton){
            this.setVisible(false);
            RegistrationFrame test = new RegistrationFrame();
            test.setVisible(true);
        }
        else if(e.getSource() == showpass){
            if(showpass.isSelected()){
                passField.setEchoChar((char)0);
            }
            else{
                passField.setEchoChar((char)8226);
            }
        }
    }
    public void loginCheck(){
        
        try {
            String name,add,other, arr[];
            JFrame db;
            int x = 0,y=0;
            File login;
            Person[] person = new Person[counter()];
            boolean isSuccessful = false;
            if(accountSelector.getSelectedIndex()==0){
                login = new File("patacc.txt");
            }
            else{
                login = new File("docacc.txt");
            }   
            Scanner  input = new Scanner(login);
            for (int i = 0; i < counter(); i++) {
                name = input.nextLine();
                add = input.nextLine();
                other = input.nextLine();
                arr = other.split(" ");
                person[i] = new Person(name,add,arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9]);
                String uname, upass;
                uname = nameField.getText();
                upass = passField.getText();
                
                if((uname.equals(person[i].getEmail())||uname.equals(person[i].getUsername()))&&upass.equals(person[i].getPassword())){
                    this.setVisible(false);
                    if(accountSelector.getSelectedIndex()==0){
                        
                        db = new PatientDashboard(person[i]);
                    }
                    else{
                        db = new DoctorDashboard(person[i]);
                    }
                    db.setVisible(true);
                    isSuccessful = true;
                    break;
                    
                }
                else if((uname.equals(person[i].getEmail())||uname.equals(person[i].getUsername()))){
                    y++;
                }
                
            }
            if(!isSuccessful){
                if(y>0){
                    JOptionPane.showMessageDialog(null, "Invalid Password", "WARNING",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Username", "WARNING",JOptionPane.WARNING_MESSAGE);
                }
            }
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    } 
        
    
    public int counter() throws FileNotFoundException{
        
        File login;
            
        int x = 0;
        if(accountSelector.getSelectedIndex()==0){
            login = new File("patacc.txt");
        }
        else{
            login = new File("docacc.txt");
        }
        Scanner count = new Scanner(login);
        while (count.hasNextLine()){
            count.nextLine();
            x++;
        }
        x/=3;
        count.close();
        return x;
    }
    public void close(){
        this.setVisible(false);
    }
   
}
