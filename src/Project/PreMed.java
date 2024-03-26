package Project;

import java.util.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PreMed extends JFrame implements ActionListener{
    //container
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    
    Person doc, pat;
    //panel
    JPanel leftSpacer = new JPanel();
    JPanel rightSpacer = new JPanel();
    JPanel center = new JPanel();
    
    JPanel searchPanel = new JPanel();
    JPanel searchBoxPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    
    DefaultTableModel model = new DefaultTableModel();
    JTable courseTable = new JTable(model);
    JScrollPane scroll = new JScrollPane(courseTable);
    
    JButton backButton = new JButton("Back to Dashboard");
    
    
    JLabel searchLabel = new JLabel("All Medications");
    
    
    
    int n = 0;
    PreMed(Person getdoc, Person getpat){
        doc = getdoc;
        pat = getpat;
        this.setTitle("Medical History Management System");//title name
        this.setBounds(300, 100, 720, 600);//size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close 
        this.setResizable(true);//resizable

        //icon
        this.setIconImage(logo.getImage());
        
        
        
        Initialize();
        
        LoadTable();
        addActionEvent();
    }
    public void Initialize(){
        container.setLayout(new BorderLayout());
        container.setSize(720,600);
        container.setBackground(new Color(180, 228, 251));
        container.add(center,BorderLayout.CENTER);
        

        center.setLayout(new BorderLayout());
        center.setOpaque(false);
        
        searchPanel.setOpaque(false);
        searchPanel.setLayout(new GridLayout(2,1,10,10));
        searchPanel.setPreferredSize(new Dimension(100,100));
        center.add(searchPanel,BorderLayout.NORTH);
        
        searchBoxPanel.setLayout(new FlowLayout());
        searchBoxPanel.setOpaque(false);
        
        searchLabel.setFont(new Font("Arial", Font.BOLD, 18));
        searchLabel.setForeground(new Color(0,0,0));
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchPanel.add(searchLabel);
        
        searchPanel.add(searchBoxPanel);
        
        
        
        
        
        buttonPanel.setPreferredSize(new Dimension(50,50));
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout());
        center.add(buttonPanel,BorderLayout.SOUTH);
        
        buttonPanel.add(backButton);
        
        center.add(scroll,BorderLayout.CENTER);
        
        
        
        
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
            PatientReport test = new PatientReport(doc,pat); 
            test.setVisible(true);
        }
        
    }
    
    public int counter() throws FileNotFoundException{
        
        File login;
            
        
        login = new File(pat.getNID()+".txt");
       int x =0;
        Scanner count = new Scanner(login);
        while (count.hasNextLine()){
            count.nextLine();
            x++;
        }
        x/=6;
        n = x;
        count.close();
        return x;
    }
    public void LoadTable(){
		
        try {
            model.addColumn("Disease");
            model.addColumn("Medicine");
            model.addColumn("Times a day");
            model.addColumn("Number of days");
            model.addColumn("Doctors name");
            model.addColumn("Doctors number");
            
            Scanner input = new Scanner(new File(pat.getNID()+".txt"));
            for(int i=counter()-1; i>=0; i--){
                
                
                model.addRow(new Object[]{input.nextLine(),input.nextLine(),input.nextLine(),input.nextLine(),input.nextLine(),input.nextLine()});
            }
        } 
        catch (FileNotFoundException ex) {
            model.setRowCount(0); 
            model.setColumnCount(0);
        }
		
    }
    
     
}
