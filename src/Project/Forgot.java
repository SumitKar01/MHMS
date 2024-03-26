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

public class Forgot extends JFrame implements ActionListener{
    //container
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    
    //panel
    JPanel imagePanel = new JPanel();
    JPanel infoPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    
    JPanel leftSpacer = new JPanel();
    JPanel rightSpacer = new JPanel();
    JPanel center = new JPanel();
    JPanel topSpacer = new JPanel();
    JPanel bottomSpacer = new JPanel();
    
    
    
    JLabel docLogoLabel = new JLabel(new ImageIcon("forgot.png"));
    
    JLabel verifyLabel = new JLabel("Verify Information");
    JLabel updateLabel = new JLabel("Update Information");
    
    JLabel nidLabel = new JLabel("NID");
    JLabel phoneLabel = new JLabel("Phone");
    
    JTextField nidField = new JTextField();
    JTextField phoneField = new JTextField();
    
    JLabel emailLabel = new JLabel("Username");
    JLabel passLabel = new JLabel("Password");
    
    JTextField emailField = new JTextField();
    JTextField passField = new JTextField();
    
    JButton yesButton = new JButton("Continue");
    JButton noButton = new JButton("Cancel");
    
    Person[] person;
    Doc[] doc;
    int n,m;
    JTextField bmdsField = new JTextField();
    Forgot(){
    
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
        
        center.setLayout(new BorderLayout());
        center.setOpaque(false);
        container.add(center);
        
        infoPanel.setLayout(new GridLayout(5,2,10,10));
        infoPanel.setOpaque(false);
        infoPanel.setPreferredSize(new Dimension(250,250));
        center.add(infoPanel,BorderLayout.CENTER);
        
       infoPanel.add(verifyLabel);
       infoPanel.add(updateLabel);
       
       infoPanel.add(nidLabel);
       infoPanel.add(emailLabel);
       infoPanel.add(nidField);
       infoPanel.add(emailField);
       infoPanel.add(phoneLabel);
       infoPanel.add(passLabel);
       infoPanel.add(phoneField);
       infoPanel.add(passField); 
       
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
            change();
            this.setVisible(false);
            LoginFrame lframe = new LoginFrame();
            lframe.setVisible(true);
        }
    }
    public int counterP() throws FileNotFoundException{
        
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
        person = new Person[x];
        return x;
    }
    public int counterD() throws FileNotFoundException{
        
        File login;
            
        int x = 0;
        
        login = new File("docacc.txt");
        
        Scanner count = new Scanner(login);
        while (count.hasNextLine()){
            count.nextLine();
            x++;
        }
        x/=3;
        count.close();
        doc = new Doc[x];
        return x;
    }
    public void loadP(){
        String name, add,other, arr[];
        try {
            int x = counterP();
            n = counterP();
            Scanner input = new Scanner(new File("patacc.txt"));
            for(int i = 0; i<x; i++){
                name = input.nextLine();
                add = input.nextLine();
                other = input.nextLine();
                arr = other.split(" ");
                person[i] = new Person(name,add,arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9]);
                
            }
            
        }
                 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void loadD(){
        String name, add,other, arr[];
        try {
            int x = counterD();
            m = counterD();
            Scanner input = new Scanner(new File("docacc.txt"));
            for(int i = 0; i<m; i++){
                name = input.nextLine();
                add = input.nextLine();
                other = input.nextLine();
                arr = other.split(" ");
                doc[i] = new Doc(name,add,arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9],arr[10]);
            }
            
        }
                 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void change(){
        loadP();
        loadD();
        boolean matched = false;
        for (int i = 0; i < n; i++) {
            if((person[i].getNID().equals(nidField.getText()))&&(person[i].getPhone().equals(phoneField.getText()))){
                matched = true;
                if(person[i].getUsername().equalsIgnoreCase(emailField.getText())){
                    JOptionPane.showMessageDialog(null, "Username already is use", "WARNING",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    
                    person[i].setUsername(emailField.getText());
                    person[i].setPassword(passField.getText());
                    createPatfile();
                }
                
                break;
                
            }
            
        }
            
            
    
        for (int i = 0; i < m; i++) {
            if((doc[i].getNID().equals(nidField.getText()))&&(doc[i].getPhone().equals(phoneField.getText()))){
                if(doc[i].getUsername().equalsIgnoreCase(emailField.getText())){
                    
                }
                else{
                    doc[i].setUsername(emailField.getText());
                    doc[i].setPassword(passField.getText());
                    
                    createDocfile();
                }
                break;
            }
        }
        if(matched==false){
            JOptionPane.showMessageDialog(null, "Account Not Found", "WARNING",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void createPatfile(){
        File patfile = new File("patacc.txt");
        FileWriter fr =null;
        
        try {

            fr = new FileWriter(patfile);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            for (int i = 0; i < n; i++) {
                
                pr.println(person[i].getName());
                pr.println(person[i].getAddress());
                pr.println(person[i].getPhone()+" "+person[i].getEmail()+" "+person[i].getGender()+" "+person[i].getDOB()+" "+person[i].getNID()+" "+person[i].getBlood()+" "+person[i].getMarital()+" "+person[i].getOccupation()+" "+person[i].getUsername()+" "+person[i].getPassword());
            }
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
            } 
            catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
    }
    public void createDocfile(){
        File patfile = new File("docacc.txt");
        FileWriter fr =null;
        
        try {

            fr = new FileWriter(patfile);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            for (int i = 0; i < m; i++) {
                
                pr.println(doc[i].getName());
                pr.println(doc[i].getAddress());
                pr.println(doc[i].getPhone()+" "+doc[i].getEmail()+" "+doc[i].getGender()+" "+doc[i].getDOB()+" "+doc[i].getNID()+" "+doc[i].getBlood()+" "+doc[i].getMarital()+" "+doc[i].getOccupation()+" "+doc[i].getUsername()+" "+doc[i].getPassword()+" "+doc[i].getBMDS());
            }
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
            } 
            catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
    }
    
}
