package ui;

import models.Student;
import service.AdminService;
import service.StudentService;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AdminService adminService;
    private StudentService studentService;

    public LoginUI(AdminService adminService, StudentService studentService){
        this.adminService = adminService;
        this.studentService = studentService;
        setupUI();
    }

    public LoginUI() {
        this.adminService = new AdminService();
        this.studentService = new StudentService();
        
        // Add demo students
        DemoData.addDemoStudents(studentService);
        
        setupUI();
    }

    private void setupUI(){
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        setTitle("FT Academic System - Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton loginBtn = new JButton("Login");
        panel.add(loginBtn, gbc);

        loginBtn.addActionListener(ae -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if(adminService.isValidAdmin(username, password)){
                new AdminDashboardUI(adminService, studentService).setVisible(true);
                dispose();
                return;
            }

            Student student = studentService.getStudentByUsername(username);
            if(student != null && password.equals("password")){
                new StudentDashboardUI(student).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Invalid credentials");
            }
        });
    }
}