package Project;

import java.util.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssignMed extends JFrame implements ActionListener{
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    
    JPanel bannerimage = new JPanel();
    JPanel fromPanel = new JPanel();
    JPanel addButtonPanel = new JPanel();
    
    JPanel leftSpacer = new JPanel();
    JPanel rightSpacer = new JPanel();
    
    JButton addButton = new JButton("Add Medication");
    JButton backButton = new JButton("Back");
    
    JLabel issueLabel = new JLabel("Name of Disease");
    JLabel medicineLabel = new JLabel("Prescribed Medicine");
    JLabel timesLabel = new JLabel("Times a Day");
    JLabel daysLabel = new JLabel("Days");
    
    JTextField issueField = new JTextField();
    JTextField medicineField = new JTextField();
    JTextField timesField= new JTextField();
    JTextField daysField = new JTextField();
    
    JLabel imageLabel = new JLabel(new ImageIcon("reg.png"));
    
    int n = 0;
    Person doc, pat;
    
    AssignMed(Person getdoc, Person getpat){
    
        this.setTitle("Medical History Management System");//title name
        this.setBounds(300, 100, 720, 600);//size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close 
        this.setResizable(true);//resizable

        //icon
        this.setIconImage(logo.getImage());
        
        Initialize();

        addActionEvent();
        doc = getdoc;
        pat = getpat;
    }
    public void Initialize(){
        
        container.setLayout(new BorderLayout(20,20));
        container.setSize(720,600);
        container.setBackground(new Color(94, 191, 244));
        
        bannerimage.setPreferredSize(new Dimension(180,180));
        container.add(bannerimage,BorderLayout.NORTH);
       
        bannerimage.add(imageLabel);
        
        fromPanel.add(issueLabel);
        fromPanel.add(issueField);
        fromPanel.add(medicineLabel);
        fromPanel.add(medicineField);
        fromPanel.add(timesLabel);
        fromPanel.add(timesField);
        fromPanel.add(daysLabel);
        fromPanel.add(daysField);
        
        
        
        
        
        fromPanel.setLayout(new GridLayout(4,2,40,20));
        fromPanel.setOpaque(false);
        container.add(fromPanel,BorderLayout.CENTER);   
        
        addButtonPanel.add(backButton);
        addButtonPanel.add(addButton);
        
        addButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        addButtonPanel.setOpaque(false);
        addButtonPanel.setPreferredSize(new Dimension(50,50));
        container.add(addButtonPanel,BorderLayout.SOUTH);
        
        leftSpacer.setPreferredSize(new Dimension(30,30));
        leftSpacer.setOpaque(false);
        container.add(leftSpacer,BorderLayout.EAST);
        
        rightSpacer.setPreferredSize(new Dimension(30,30));
        rightSpacer.setOpaque(false);
        container.add(rightSpacer,BorderLayout.WEST);
        
        
        
        
        
    }
    public void addActionEvent(){
        addButton.addActionListener(this);
        backButton.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton){
            if(isNotNull()){
                createEntry();
                this.setVisible(false);
                PatientReport test = new PatientReport(doc,pat);
                test.setVisible(true);
            }
            
        }
        else if(e.getSource() == backButton){
            this.setVisible(false);
            PatientReport test = new PatientReport(doc,pat);
            test.setVisible(true);
        }
    }
    public boolean isNotNull(){
        if(issueField.getText()==null){
            return false;
        }
        else if(medicineField.getText()==null){
            return false;
        }
        else if(timesField.getText()==null){
            return false;
        }
        else if(daysField.getText()==null){
            return false;
        }
        
        else{
            return true;
        }
    }
    
    
    
    public void createEntry(){
        File patfile = new File(pat.getNID()+".txt");
        if(!patfile.exists()){
            try {
                patfile.createNewFile();
                FileWriter fr = new FileWriter(patfile, true);
                BufferedWriter br = new BufferedWriter(fr);
                PrintWriter pr = new PrintWriter(br);
                pr.println(issueField.getText());
                pr.println(medicineField.getText());
                pr.println(timesField.getText());
                pr.println(daysField.getText());
                pr.println(doc.getName());
                pr.println(doc.getPhone());
                pr.close();
                br.close();
                fr.close();
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
                pr.println(issueField.getText());
                pr.println(medicineField.getText());
                pr.println(timesField.getText());
                pr.println(daysField.getText());
                pr.println(doc.getName());
                pr.println(doc.getPhone());
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

    
}
