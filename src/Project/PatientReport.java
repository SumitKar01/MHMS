package Project;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class PatientReport extends JFrame implements ActionListener{
    
    //container
    Container container = getContentPane();
    
    //logo
    ImageIcon logo = new ImageIcon("logo.png");
    
    //Panel
    JPanel greetPanel = new JPanel();
    JPanel dashboardPanel = new JPanel();
    
    JPanel leftSpacer = new JPanel();
    JPanel rightSpacer = new JPanel();
    JPanel center = new JPanel();
    JPanel topSpacer = new JPanel();
    JPanel bottomSpacer = new JPanel();
    
    JPanel photoPanel = new JPanel();
    JPanel welcomePanel = new JPanel();
    JPanel logoutPanel = new JPanel();
    
    JPanel assignMedPanel = new JPanel();

    JPanel previousMedPanel = new JPanel();
    
    JLabel assignMedLabel = new JLabel("Assign Medications");
    JLabel previousMedLabel = new JLabel("All Medications");
    
    JPanel assignReportPanel = new JPanel();

    JPanel allReportPanel = new JPanel();
    
    JLabel assignReportLabel = new JLabel("Assign Report");
    JLabel allReportLabel = new JLabel("All Report");

    
    
    JButton assignMedButton = new JButton(new ImageIcon("addmed70.png"));

    JButton previousMedButton = new JButton(new ImageIcon("allmed70.png"));
    
    JButton assignReportButton = new JButton(new ImageIcon("addreport70.png"));

    JButton allReportButton = new JButton(new ImageIcon("allreport70.png"));
    
    JButton logoutButton = new JButton("Back to Patient List");
    
    JLabel bannerLabel = new JLabel(new ImageIcon("medreport.png"));
    JPanel bannerPanel = new JPanel();

    Person doc,pat;
    
    
    PatientReport(Person getdoc, Person getpat){
    
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
        
        container.setLayout(new BorderLayout());
        container.setSize(720,600);
        container.setBackground(new Color(94, 191, 244));
        
        greetPanel.setLayout(new BorderLayout());
        greetPanel.setPreferredSize(new Dimension(720,250));
        greetPanel.setBackground(new Color(1, 127, 144));
        container.add(greetPanel,BorderLayout.NORTH);
        
        
        
        greetPanel.add(bannerPanel);
        bannerPanel.setBackground(new Color(111, 212, 224));
        bannerPanel.add(bannerLabel);
        
        
        logoutPanel.add(logoutButton);
        
        
        
        logoutPanel.setLayout(new FlowLayout());
        logoutPanel.setOpaque(false);
        logoutPanel.setPreferredSize(new Dimension(50,50));
        container.add(logoutPanel,BorderLayout.SOUTH);
        
        
        center.setLayout(new BorderLayout());
        center.setOpaque(false);
        container.add(center,BorderLayout.CENTER);
        
        assignMedPanel.setLayout(new BorderLayout(10,10));
        assignMedPanel.setOpaque(false);
        labelDesign(assignMedLabel);
        assignMedPanel.add(assignMedLabel,BorderLayout.SOUTH);
        assignMedButton.setContentAreaFilled(false);
        assignMedButton.setBorderPainted(false);
        assignMedPanel.add(assignMedButton,BorderLayout.CENTER);
        dashboardPanel.add(assignMedPanel);
        
        previousMedPanel.setLayout(new BorderLayout(10,10));
        previousMedPanel.setOpaque(false);
        labelDesign(previousMedLabel);
        previousMedPanel.add(previousMedLabel,BorderLayout.SOUTH);
        previousMedButton.setContentAreaFilled(false);
        previousMedButton.setBorderPainted(false);
        previousMedPanel.add(previousMedButton,BorderLayout.CENTER);
        dashboardPanel.add(previousMedPanel);
        
        assignReportPanel.setLayout(new BorderLayout(10,10));
        assignReportPanel.setOpaque(false);
        labelDesign(assignReportLabel);
        assignReportPanel.add(assignReportLabel,BorderLayout.SOUTH);
        assignReportButton.setContentAreaFilled(false);
        assignReportButton.setBorderPainted(false);
        assignReportPanel.add(assignReportButton,BorderLayout.CENTER);
        dashboardPanel.add(assignReportPanel);
        
        allReportPanel.setLayout(new BorderLayout(10,10));
        allReportPanel.setOpaque(false);
        labelDesign(allReportLabel);
        allReportPanel.add(allReportLabel,BorderLayout.SOUTH);
        allReportButton.setContentAreaFilled(false);
        allReportButton.setBorderPainted(false);
        allReportPanel.add(allReportButton,BorderLayout.CENTER);
        dashboardPanel.add(allReportPanel);
       

        
        
        dashboardPanel.setLayout(new GridLayout(2,2,10,20));
        dashboardPanel.setOpaque(false);
        center.add(dashboardPanel,BorderLayout.CENTER);
        
        leftSpacer.setPreferredSize(new Dimension(20,20));
        leftSpacer.setOpaque(false);
        center.add(leftSpacer,BorderLayout.EAST);
        rightSpacer.setOpaque(false);
        rightSpacer.setPreferredSize(new Dimension(20,20));
        center.add(rightSpacer,BorderLayout.WEST);
        topSpacer.setOpaque(false);
        topSpacer.setPreferredSize(new Dimension(20,20));
        center.add(topSpacer,BorderLayout.NORTH);
        bottomSpacer.setOpaque(false);
        bottomSpacer.setPreferredSize(new Dimension(20,20));
        center.add(bottomSpacer,BorderLayout.SOUTH);
        
        
    }
    public void labelDesign(JLabel x){
        x.setHorizontalAlignment(SwingConstants.CENTER);
        x.setFont(new Font("Arial", Font.BOLD, 14));
        x.setForeground(new Color(255,255,255));
    }
    public void addActionEvent(){
        logoutButton.addActionListener(this);
        assignMedButton.addActionListener(this);

        previousMedButton.addActionListener(this);
        assignReportButton.addActionListener(this);

        allReportButton.addActionListener(this);
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logoutButton){
            this.setVisible(false);
            SearchPanel lframe = new SearchPanel(doc);
            lframe.setVisible(true);
            
        }
        else if(e.getSource()==assignMedButton){
            this.setVisible(false);
            AssignMed lframe = new AssignMed(doc,pat);
            lframe.setVisible(true);
            
        }
        else if(e.getSource()==previousMedButton){
            this.setVisible(false);
            PreMed lframe = new PreMed(doc,pat);
            lframe.setVisible(true);
        }
        else if(e.getSource()==assignReportButton){
            this.setVisible(false);
            AssignReport lframe = new AssignReport(doc,pat);
            lframe.setVisible(true);
        }
        else if(e.getSource()==allReportButton){
            this.setVisible(false);
            AllReport lframe = new AllReport(doc,pat);
            lframe.setVisible(true);
        }
        
    }
    
    
}
