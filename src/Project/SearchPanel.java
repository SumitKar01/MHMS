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

public class SearchPanel extends JFrame implements ActionListener{
    //container
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    Person[] person;
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
    JButton searchButton = new JButton("Search");
    JButton resetButton = new JButton("Reset");
    
    JLabel searchLabel = new JLabel("Search for Patients");
    
    JTextField searchField = new JTextField();
    
    int n = 0;
    SearchPanel(Person getdoc){
        doc = getdoc;
        this.setTitle("Medical History Management System");//title name
        this.setBounds(300, 100, 720, 600);//size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close 
        this.setResizable(true);//resizable

        //icon
        this.setIconImage(logo.getImage());
        
        
        
        Initialize();
        load();
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
        
        searchField.setColumns(20);
        searchBoxPanel.add(searchField,BorderLayout.NORTH);
        
        searchBoxPanel.add(searchButton,BorderLayout.CENTER);
       
        searchBoxPanel.add(resetButton,BorderLayout.SOUTH);
        
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
        searchButton.addActionListener(this);
        resetButton.addActionListener(this);
        courseTable.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent evt) {
                int row = courseTable.rowAtPoint(evt.getPoint());
                String value = courseTable.getModel().getValueAt(row, 1).toString();
                matchID(value);
            }
        });
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            this.setVisible(false);
            DoctorDashboard test = new DoctorDashboard(doc); 
            test.setVisible(true);
        }
        else if(e.getSource() == resetButton){	
            if(!searchField.equals("")){
		model.setRowCount(0); 
		model.setColumnCount(0);
		searchField.setText("");
		LoadTable();
            }
	}
        if(e.getSource() == searchButton){
            String text = searchField.getText();
            model.setRowCount(0); 
			
            for(int i=0; i<n; i++){
		if(person[i].getName().contains(text)||text.equalsIgnoreCase(person[i].getName()) || text.equalsIgnoreCase(person[i].getNID()) || text.equalsIgnoreCase(person[i].getPhone()) ){
                    model.addRow(new Object[]{person[i].getName(),person[i].getNID(),person[i].getGender(),person[i].getPhone(), person[i].getBlood()});
		}
		else if(text.equals("")){
                    model.addRow(new Object[]{person[i].getName(),person[i].getNID(),person[i].getGender(),person[i].getPhone(), person[i].getBlood()});
		}
            }
	}
    }
    public void load(){
        String name, add,other, arr[];
        try {
            int x = counter();
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
    public int counter() throws FileNotFoundException{
        
        File login;
            
        
        login = new File("patacc.txt");
       int x =0;
        Scanner count = new Scanner(login);
        while (count.hasNextLine()){
            count.nextLine();
            x++;
        }
        x/=3;
        person = new Person[x];
        n = x;
        count.close();
        return x;
    }
    public void LoadTable(){
		
        model.addColumn("Patient Name");
	model.addColumn("NID");
	model.addColumn("Gender");
	model.addColumn("Phone Number");
        model.addColumn("Blood Group");
		
	
	for(int i=0; i<n; i++){
            model.addRow(new Object[]{person[i].getName(),person[i].getNID(),person[i].getGender(),person[i].getPhone(), person[i].getBlood()});			
	}
		
    }
    public void matchID(String val){
        for (int i = 0; i < n; i++) {
            if(person[i].getNID().equalsIgnoreCase(val)){
                pat = person[i];
                PatientReport test = new PatientReport(doc,pat);
                this.setVisible(false);
                test.setVisible(true);
                break;
            }
        }
    }
     
}
