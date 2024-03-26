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

public class BMDS extends JFrame implements ActionListener{
    //container
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    
    //panel
    JPanel imagePanel = new JPanel();
    JPanel bmdsPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    
    JPanel leftSpacer = new JPanel();
    JPanel rightSpacer = new JPanel();
    JPanel center = new JPanel();
    JPanel topSpacer = new JPanel();
    JPanel bottomSpacer = new JPanel();
    
    JLabel docLogoLabel = new JLabel(new ImageIcon("docs.png"));
    
    JButton yesButton = new JButton("Yes Please");
    JButton noButton = new JButton("No Thanks");
    
    JLabel bmdsLabel1 = new JLabel("Hey you have selected your occupation as Doctor");
    JLabel bmdsLabel2 = new JLabel("Please add your BMDS Number to create a Doctor Account");
    JTextField bmdsField = new JTextField();
    BMDS(){
    
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
        container.setLayout(new BorderLayout());
        container.setSize(720,600);
        container.setBackground(new Color(180, 228, 251));
        
        imagePanel.setOpaque(false);
        imagePanel.setPreferredSize(new Dimension(250,250));
        container.add(imagePanel,BorderLayout.NORTH);
        
        imagePanel.add(docLogoLabel);
        
        bmdsPanel.setLayout(new GridLayout(3,1));
        bmdsPanel.setOpaque(false);
        bmdsPanel.setPreferredSize(new Dimension(250,250));
        container.add(bmdsPanel,BorderLayout.CENTER);
        
        bmdsLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        bmdsLabel1.setForeground(new Color(0,0,0));
        bmdsLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        //bmdsLabel1.setVerticalAlignment(SwingConstants.BOTTOM);
        bmdsPanel.add(bmdsLabel1);
        bmdsLabel2.setFont(new Font("Arial", Font.BOLD, 16));
        bmdsLabel2.setForeground(new Color(0,0,0));
        bmdsLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        //bmdsLabel2.setVerticalAlignment(SwingConstants.TOP);
        bmdsPanel.add(bmdsLabel2);
       
        bmdsPanel.add(center);
        center.setLayout(new BorderLayout());
        center.setOpaque(false);
        center.add(bmdsField,BorderLayout.CENTER);
        
        leftSpacer.setPreferredSize(new Dimension(100,100));
        leftSpacer.setOpaque(false);
        center.add(leftSpacer,BorderLayout.EAST);
        rightSpacer.setOpaque(false);
        rightSpacer.setPreferredSize(new Dimension(100,100));
        center.add(rightSpacer,BorderLayout.WEST);
        topSpacer.setOpaque(false);
        topSpacer.setPreferredSize(new Dimension(20,20));
        center.add(topSpacer,BorderLayout.NORTH);
        bottomSpacer.setOpaque(false);
        bottomSpacer.setPreferredSize(new Dimension(20,20));
        center.add(bottomSpacer,BorderLayout.SOUTH);
        
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        buttonsPanel.setPreferredSize(new Dimension(100,100));
        container.add(buttonsPanel,BorderLayout.SOUTH);
        
        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);
    }
    public void addActionEvent(){
        yesButton.addActionListener(this);
        noButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == noButton){
            this.setVisible(false);
            LoginFrame lframe = new LoginFrame();
            lframe.setVisible(true);
        }
        else if(e.getSource() == yesButton){
            createDocAccount();
            this.setVisible(false);
            LoginFrame lframe = new LoginFrame();
            lframe.setVisible(true);
        }
    }
    public void createDocAccount(){
        try {
            File patfile = new File("docacc.txt");
            Person person[] = new Person[counter()];
            if(!patfile.exists()){
                try {
                    patfile.createNewFile();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else{
                try {
                    File login = new File("patacc.txt");
                    Scanner  input = new Scanner(login);
                    for (int i = 0; i < counter(); i++) {
                        String name,add,other,arr[];
                        
                        name = input.nextLine();
                        add = input.nextLine();
                        other = input.nextLine();
                        arr = other.split(" ");
                        person[i] = new Person(name,add,arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9]);
                    }
                    FileWriter fr = null;
                    try {
                        int x = counter()-1;
                        
                        fr = new FileWriter(patfile, true);
                        BufferedWriter br = new BufferedWriter(fr);
                        PrintWriter pr = new PrintWriter(br);
                        pr.println(person[x].getName());
                        pr.println(person[x].getAddress());
                        pr.println(person[x].getPhone()+" "+person[x].getEmail()+" "+person[x].getGender()+" "+person[x].getDOB()+" "+person[x].getNID()+" "+person[x].getBlood()+" "+person[x].getMarital()+" "+person[x].getOccupation()+" "+person[x].getUsername()+" "+person[x].getPassword()+" "+bmdsField.getText());
                        pr.close();
                        br.close();
                        fr.close();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    finally {
                        try {
                            fr.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                catch (FileNotFoundException ex) {
                    Logger.getLogger(BMDS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BMDS.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public int counter() throws FileNotFoundException{
        
        File login;
            
        int x = 0;
        login = new File("patacc.txt");
       
        Scanner count = new Scanner(login);
        while (count.hasNextLine()){
            count.nextLine();
            x++;
        }
        x/=3;
        count.close();
        return x;
    }
}
