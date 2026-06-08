package ui;

import models.Student;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class StudentDashboardUI extends JFrame {

    private Student student;

    public StudentDashboardUI(Student student){
        this.student = student;
        setupUI();
    }

    private void setupUI(){
        try{ UIManager.setLookAndFeel(new FlatLightLaf()); }catch(Exception e){}

        setTitle("FT Academic System - Student Dashboard");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(26,31,113));
        sidebar.setPreferredSize(new Dimension(180,getHeight()));
        sidebar.setLayout(new GridLayout(6,1,0,10));

        String[] tabs = {"Home","Academic Info","Finance","Events","Settings","Logout"};
        for(String t : tabs){
            JButton btn = new JButton(t);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(26,31,113));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setOpaque(true);
            btn.addActionListener(ae -> handleTab(t));
            sidebar.add(btn);
        }

        add(sidebar, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        profilePanel.setBackground(new Color(230,230,250));
        profilePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,120));
        JLabel photoLabel = new JLabel();
        if(student.getPhoto()!=null){
            ImageIcon img = new ImageIcon(student.getPhoto().getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
            photoLabel.setIcon(img);
        } else { 
            photoLabel.setText("No Photo");
            photoLabel.setPreferredSize(new Dimension(100,100));
            photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        profilePanel.add(photoLabel);
        JLabel nameLabel = new JLabel("<html>Welcome, <b>"+student.getFirstName()+" "+student.getLastName()+"</b><br>Reg#: "+student.getUsername()+"</html>");
        nameLabel.setFont(new Font("Arial",Font.BOLD,16));
        profilePanel.add(nameLabel);
        mainPanel.add(profilePanel);

        JPanel feesPanel = new JPanel();
        feesPanel.setBackground(new Color(173,216,230));
        feesPanel.setBorder(BorderFactory.createTitledBorder("Fees Status"));
        feesPanel.setLayout(new GridLayout(2,1));
        double paid = student.getFeesPaid();
        double total = student.getTotalFees();
        int percent = (int)((paid/total)*100);
        JProgressBar progressBar = new JProgressBar(0,100);
        progressBar.setValue(percent);
        progressBar.setStringPainted(true);
        feesPanel.add(progressBar);
        JLabel eligibleLabel = new JLabel(student.isExamEligible() ? "Eligible for exams ✅" : "Not eligible for exams ❌");
        feesPanel.add(eligibleLabel);
        mainPanel.add(feesPanel);

        JPanel gradesPanel = new JPanel();
        gradesPanel.setLayout(new GridLayout(2,2,10,10));
        gradesPanel.setBorder(BorderFactory.createTitledBorder("Academic Grades"));
        Map<String,Double> grades = student.getGrades();
        for(String subject : grades.keySet()){
            JPanel card = new JPanel();
            card.setBackground(new Color(100,149,237));
            card.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            JLabel lbl = new JLabel(subject+": "+grades.get(subject));
            lbl.setFont(new Font("Arial",Font.BOLD,16));
            lbl.setForeground(Color.WHITE);
            card.add(lbl);
            gradesPanel.add(card);
        }
        mainPanel.add(gradesPanel);
    }

    private void handleTab(String tab){
        if(tab.equals("Logout")){
            new LoginUI().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,"You clicked: "+tab);
        }
    }
}