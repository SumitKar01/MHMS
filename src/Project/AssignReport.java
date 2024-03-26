package Project;

import java.util.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssignReport extends JFrame implements ActionListener{
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    
    JPanel bannerimage = new JPanel();
    JPanel fromPanel = new JPanel();
    JPanel addButtonPanel = new JPanel();
    
    JPanel leftSpacer = new JPanel();
    JPanel rightSpacer = new JPanel();
    
    JButton addButton = new JButton("Add Report");
    JButton backButton = new JButton("Back");
    
    JLabel diseaseLabel = new JLabel("Name of Disease");
    JLabel symptompsLabel = new JLabel("Symtomps");
    JLabel testsLabel = new JLabel("Tests Performed");
    JLabel daysLabel = new JLabel("Days Symptomps persisted");
    
    JTextField diseaseField = new JTextField();
    JTextField symptompsField = new JTextField();
    JTextField testsField= new JTextField();
    JTextField daysField = new JTextField();
    
    JLabel imageLabel = new JLabel(new ImageIcon("reg.png"));
    
    int n = 0;
    Person doc, pat;
    
    AssignReport(Person getdoc, Person getpat){
    
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
        
        fromPanel.add(diseaseLabel);
        fromPanel.add(diseaseField);
        fromPanel.add(symptompsLabel);
        fromPanel.add(symptompsField);
        fromPanel.add(testsLabel);
        fromPanel.add(testsField);
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
        if(diseaseField.getText()==null){
            return false;
        }
        else if(symptompsField.getText()==null){
            return false;
        }
        else if(testsField.getText()==null){
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
        File patfile = new File(pat.getNID()+"Report.txt");
        if(!patfile.exists()){
            try {
                patfile.createNewFile();
                FileWriter fr = new FileWriter(patfile, true);
                BufferedWriter br = new BufferedWriter(fr);
                PrintWriter pr = new PrintWriter(br);
                pr.println(diseaseField.getText());
                pr.println(symptompsField.getText());
                pr.println(testsField.getText());
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
                pr.println(diseaseField.getText());
                pr.println(symptompsField.getText());
                pr.println(testsField.getText());
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
