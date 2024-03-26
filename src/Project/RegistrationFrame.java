package Project;

import java.util.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationFrame extends JFrame implements ActionListener{
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    
    JPanel bannerimage = new JPanel();
    JPanel fromPanel = new JPanel();
    JPanel createButtonPanel = new JPanel();
    
    JPanel leftSpacer = new JPanel();
    JPanel rightSpacer = new JPanel();
    
    JButton createButton = new JButton("Create Account");
    JButton backButton = new JButton("Back to Login");
    
    JLabel nameLabel = new JLabel("Name");
    JLabel addressLabel = new JLabel("Address");
    JLabel phoneLabel = new JLabel("Phone Number");
    JLabel emailLabel = new JLabel("Email");
    JLabel genderLabel = new JLabel("Gender");
    JLabel DOBLabel = new JLabel("Date of Birth");
    JLabel IDLabel = new JLabel("NID No");
    JLabel bloodLabel = new JLabel("Blood Group");
    JLabel maritalLabel = new JLabel("Marital Status");
    JLabel occupationLabel = new JLabel("Occupation");
    JLabel usernameLabel = new JLabel("User Name");
    JLabel passLabel = new JLabel("Password");
    
    
    JTextField nameField = new JTextField();
    JTextField addressField = new JTextField();
    JTextField phoneField= new JTextField();
    JTextField emailField = new JTextField();
    JTextField genderField = new JTextField();
    JTextField DOBField = new JTextField();
    JTextField IDField = new JTextField();
    JTextField bloodField = new JTextField();
    JTextField maritalField = new JTextField();
    JTextField occupationField = new JTextField();
    JTextField usernameField = new JTextField();
    JTextField passField = new JTextField();
    
    JLabel imageLabel = new JLabel(new ImageIcon("reg.png"));
    Person[] person;
    int n = 0;
    
    RegistrationFrame(){
    
        this.setTitle("Medical History Management System");//title name
        this.setBounds(300, 100, 720, 600);//size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close 
        this.setResizable(true);//resizable

        //icon
        this.setIconImage(logo.getImage());
        
        Initialize();
        load();
        addActionEvent();
    }
    public void Initialize(){
        
        container.setLayout(new BorderLayout(20,20));
        container.setSize(720,600);
        container.setBackground(new Color(94, 191, 244));
        
        bannerimage.setPreferredSize(new Dimension(180,180));
        container.add(bannerimage,BorderLayout.NORTH);
       
        bannerimage.add(imageLabel);
        
        fromPanel.add(nameLabel);
        fromPanel.add(nameField);
        fromPanel.add(addressLabel);
        fromPanel.add(addressField);
        fromPanel.add(phoneLabel);
        fromPanel.add(phoneField);
        fromPanel.add(emailLabel);
        fromPanel.add(emailField);
        fromPanel.add(genderLabel);
        fromPanel.add(genderField);
        fromPanel.add(DOBLabel);
        fromPanel.add(DOBField);
        
        fromPanel.add(IDLabel);
        fromPanel.add(IDField);
        fromPanel.add(bloodLabel);
        fromPanel.add(bloodField);
        fromPanel.add(maritalLabel);
        fromPanel.add(maritalField);
        fromPanel.add(occupationLabel);
        fromPanel.add(occupationField);
        fromPanel.add(usernameLabel);
        fromPanel.add(usernameField);
        fromPanel.add(passLabel);
        fromPanel.add(passField);
        
        
        
        
        fromPanel.setLayout(new GridLayout(6,4,40,20));
        fromPanel.setOpaque(false);
        container.add(fromPanel,BorderLayout.CENTER);   
        
        createButtonPanel.add(backButton);
        createButtonPanel.add(createButton);
        
        createButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        createButtonPanel.setOpaque(false);
        createButtonPanel.setPreferredSize(new Dimension(50,50));
        container.add(createButtonPanel,BorderLayout.SOUTH);
        
        leftSpacer.setPreferredSize(new Dimension(30,30));
        leftSpacer.setOpaque(false);
        container.add(leftSpacer,BorderLayout.EAST);
        
        rightSpacer.setPreferredSize(new Dimension(30,30));
        rightSpacer.setOpaque(false);
        container.add(rightSpacer,BorderLayout.WEST);
        
        
        
        
        
    }
    public void addActionEvent(){
        createButton.addActionListener(this);
        backButton.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createButton){
            if(isNotNull()&&isEmailValid()&&isNumberValid()&&isDoctor()&&isNotDuplicate()){
                
                this.setVisible(false);
                createPatAccount();
                
                BMDS test = new BMDS();
                test.setVisible(true);
                
            }
            else if(isNotNull()&&isEmailValid()&&isNumberValid()&&isNotDuplicate()){
                
                this.setVisible(false);
                createPatAccount();
                LoginFrame test = new LoginFrame();
                test.setVisible(true);
            }
            
        }
        else if(e.getSource() == backButton){
            this.setVisible(false);
            LoginFrame test = new LoginFrame();
            test.setVisible(true);
        }
    }
    public boolean isNotNull(){
        if(nameField.getText()==null){
            return false;
        }
        else if(addressField.getText()==null){
            return false;
        }
        else if(addressField.getText()==null){
            return false;
        }
        else if(phoneField.getText()==null){
            return false;
        }
        else if(emailField.getText()==null){
            return false;
        }
        else if(IDField.getText()==null){
            return false;
        }
        else if(usernameField.getText()==null){
            return false;
        }
        else if(passField.getText()==null){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean isEmailValid(){
        if(emailField.getText().contains("@")){
            return true;
        }
        return false;
    }
    public boolean isNumberValid(){
        if(phoneField.getText().length()==11){
            return true;
        }
        return false;
    }
    public boolean isDoctor(){
        if(occupationField.getText().equalsIgnoreCase("doctor")){
            return true;
        }
        return false;
    }
    public boolean isNotDuplicate(){
        
            
        for(int i = 0; i<n;i++){
            if((IDField.getText().equals(person[i].getNID()))||(emailField.getText().equals(person[i].getEmail()))||(usernameField.getText().equals(person[i].getUsername()))||(phoneField.getText().equals(person[i].getPhone()))){
                JOptionPane.showMessageDialog(null, "Duplicate Data Found", "WARNING",JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
            return true;  
    }
    public void load(){
        String name, add,other, arr[];
        try {
            int x = counter();
            n = counter();
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
    public void createPatAccount(){
        File patfile = new File("patacc.txt");
        if(!patfile.exists()){
            try {
                patfile.createNewFile();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            FileWriter fr = null;
            try {
                fr = new FileWriter(patfile, true);
                BufferedWriter br = new BufferedWriter(fr);
                PrintWriter pr = new PrintWriter(br);
                pr.println(nameField.getText());
                pr.println(addressField.getText());
                pr.println(phoneField.getText()+" "+emailField.getText()+" "+genderField.getText()+" "+DOBField.getText()+" "+IDField.getText()+" "+bloodField.getText()+" "+maritalField.getText()+" "+occupationField.getText()+" "+usernameField.getText()+" "+passField.getText());
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
        person = new Person[x];
        return x;
    }
    
}
