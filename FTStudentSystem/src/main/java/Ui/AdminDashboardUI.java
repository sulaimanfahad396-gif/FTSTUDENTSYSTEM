package ui;

import service.AdminService;
import service.StudentService;
import models.Student;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class AdminDashboardUI extends JFrame {

    private AdminService adminService;
    private StudentService studentService;
    private JPanel contentPanel;

    private List<String> events = new ArrayList<>();

    public AdminDashboardUI(AdminService adminService, StudentService studentService){
        this.adminService = adminService;
        this.studentService = studentService;
        setupUI();
    }

    private void setupUI(){
        try { UIManager.setLookAndFeel(new FlatLightLaf()); } catch(Exception e){ e.printStackTrace(); }

        setTitle("Admin Dashboard - Fahad Taylor Academy");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(45, 45, 45));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setLayout(new GridLayout(0,1,0,10));
        JButton btnHome = new JButton("Home");
        JButton btnStudents = new JButton("Students");
        JButton btnFinance = new JButton("Finance");
        JButton btnAcademic = new JButton("Academic");
        JButton btnEvents = new JButton("Events");
        JButton btnAddStudent = new JButton("Add Student");
        JButton btnLogout = new JButton("Logout");
        
        for(JButton btn: new JButton[]{btnHome,btnStudents,btnFinance,btnAcademic,btnEvents,btnAddStudent,btnLogout}){
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(60, 63, 65));
            btn.setFocusPainted(false);
            sidebar.add(btn);
        }
        mainPanel.add(sidebar, BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        showHome();

        btnHome.addActionListener(ae -> showHome());
        btnStudents.addActionListener(ae -> showStudents());
        btnFinance.addActionListener(ae -> showFinance());
        btnEvents.addActionListener(ae -> showEvents());
        btnAddStudent.addActionListener(ae -> {
            new AddStudentUI(studentService).setVisible(true);
        });
        btnLogout.addActionListener(ae -> {
            new LoginUI().setVisible(true);
            dispose();
        });
    }

    private void showHome(){
        contentPanel.removeAll();
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.add(new JLabel("Welcome to Fahad Taylor Academy Admin Dashboard"));
        panel.add(new JLabel("Total Students: " + studentService.getAllStudents().size()));
        panel.add(new JLabel("Upcoming Events: " + events.size()));
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showStudents(){
        contentPanel.removeAll();
        List<Student> students = studentService.getAllStudents();
        JTextArea area = new JTextArea();
        area.setEditable(false);
        for(Student s : students){
            area.append(s.getUsername() + " - " + s.getFirstName() + " " + s.getLastName() +
                    " - Fees Paid: " + s.getFeesPaid() + "/" + s.getTotalFees() +
                    " - Exam Eligible: " + (s.isExamEligible() ? "YES" : "NO") + "\n");
        }
        contentPanel.add(new JScrollPane(area), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showFinance(){
        contentPanel.removeAll();
        JPanel panel = new JPanel(new GridLayout(0,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        List<Student> students = studentService.getAllStudents();
        for(Student s: students){
            int percent = (int)((s.getFeesPaid()/s.getTotalFees())*100);
            panel.add(new JLabel(s.getUsername() + " - " + percent + "% Fees Paid"));
            JProgressBar bar = new JProgressBar(0,100);
            bar.setValue(percent);
            panel.add(bar);
        }
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showEvents(){
        contentPanel.removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        DefaultListModel<String> model = new DefaultListModel<>();
        events.forEach(model::addElement);
        JList<String> list = new JList<>(model);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        JButton addBtn = new JButton("Add Event");
        panel.add(addBtn, BorderLayout.SOUTH);
        addBtn.addActionListener(ae -> {
            String event = JOptionPane.showInputDialog(this,"Enter Event:");
            if(event!=null && !event.isEmpty()){
                events.add(event);
                model.addElement(event);
            }
        });

        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}