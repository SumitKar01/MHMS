package Project;

import java.util.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Personal extends JFrame implements ActionListener{
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    
    JPanel fromPanel = new JPanel();
    JPanel createButtonPanel = new JPanel();
    
    JPanel leftSpacer = new JPanel();
    JPanel rightSpacer = new JPanel();
    
    
    JButton backButton = new JButton("Back to Dashboard");
    
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
    Person person;
    
   
   
    
    
    Personal(Person getperson){
    
        this.setTitle("Medical History Management System");//title name
        this.setBounds(300, 100, 720, 600);//size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close 
        this.setResizable(true);//resizable

        //icon
        this.setIconImage(logo.getImage());
        person = getperson;
        setLabel();
        Initialize();
        addActionEvent();
    }
    public void Initialize(){
        
        container.setLayout(new BorderLayout(20,20));
        container.setSize(720,600);
        container.setBackground(new Color(255, 255, 255));
        
        
       
        
        fromPanel.add(nameLabel);
        designlabel(nameLabel);
        fromPanel.add(addressLabel);
        designlabel(addressLabel);
        fromPanel.add(phoneLabel);
        designlabel(phoneLabel);
        fromPanel.add(emailLabel);
        designlabel(emailLabel);
        fromPanel.add(genderLabel);
        designlabel(genderLabel);
        fromPanel.add(DOBLabel);
        designlabel(DOBLabel);
        
        fromPanel.add(IDLabel);
        designlabel(IDLabel);
        fromPanel.add(bloodLabel);
        designlabel(bloodLabel);
        fromPanel.add(maritalLabel);
        designlabel(maritalLabel);
        fromPanel.add(occupationLabel);
        designlabel(occupationLabel);
        fromPanel.add(usernameLabel);
        designlabel(usernameLabel);

        
        
        
        
        fromPanel.setLayout(new GridLayout(6,2,40,20));
        fromPanel.setOpaque(false);
        container.add(fromPanel,BorderLayout.CENTER);   
        
        createButtonPanel.add(backButton);
        
        
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
        
        backButton.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==backButton){
            this.setVisible(false);
            PatientDashboard db = new PatientDashboard(person);
            db.setVisible(true);
        }
               
    }
    public void designlabel(JLabel x){
        x.setFont(new Font("Arial",Font.BOLD,16));
    }
    public void setLabel(){
        nameLabel = new JLabel("Name : "+ person.getName() );
        addressLabel = new JLabel("Address : "+ person.getAddress() );
        phoneLabel = new JLabel("Phone Number : "+ person.getPhone());
        emailLabel = new JLabel("Email : "+ person.getEmail());
        genderLabel = new JLabel("Gender : "+ person.getGender());
        DOBLabel = new JLabel("Date of Birth : "+ person.getDOB());
        IDLabel = new JLabel("NID No : "+ person.getNID());
        bloodLabel = new JLabel("Blood Group : "+ person.getBlood());
        maritalLabel = new JLabel("Marital Status : "+ person.getMarital());
        occupationLabel = new JLabel("Occupation : "+ person.getOccupation());
        usernameLabel = new JLabel("User Name : "+ person.getUsername());
    }
    
    
}
