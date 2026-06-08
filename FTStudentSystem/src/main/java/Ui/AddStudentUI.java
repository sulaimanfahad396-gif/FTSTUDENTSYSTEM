package ui;

import models.Student;
import service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class AddStudentUI extends JFrame {

    private JTextField usernameField, firstNameField, lastNameField, phoneField, emailField, registrationField;
    private JComboBox<String> genderCombo;
    private JTextField mathField, scienceField, sstField, englishField, feesField;
    private JLabel photoLabel;
    private JButton choosePhotoBtn, saveBtn;
    private ImageIcon studentPhoto;
    private StudentService studentService;

    public AddStudentUI(StudentService studentService){
        this.studentService = studentService;
        setupUI();
    }

    private void setupUI(){
        setTitle("Add Student - Admin");
        setSize(600,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panel, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx=0; gbc.gridy=0; panel.add(new JLabel("Username:"), gbc);
        gbc.gridx=1; usernameField = new JTextField(15); panel.add(usernameField, gbc);

        gbc.gridx=0; gbc.gridy=1; panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx=1; firstNameField = new JTextField(15); panel.add(firstNameField, gbc);

        gbc.gridx=0; gbc.gridy=2; panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx=1; lastNameField = new JTextField(15); panel.add(lastNameField, gbc);

        gbc.gridx=0; gbc.gridy=3; panel.add(new JLabel("Gender:"), gbc);
        gbc.gridx=1; genderCombo = new JComboBox<>(new String[]{"Male","Female"}); panel.add(genderCombo, gbc);

        gbc.gridx=0; gbc.gridy=4; panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx=1; phoneField = new JTextField(15); panel.add(phoneField, gbc);

        gbc.gridx=0; gbc.gridy=5; panel.add(new JLabel("Email:"), gbc);
        gbc.gridx=1; emailField = new JTextField(15); panel.add(emailField, gbc);

        gbc.gridx=0; gbc.gridy=6; panel.add(new JLabel("Registration #:"), gbc);
        gbc.gridx=1; registrationField = new JTextField(15); panel.add(registrationField, gbc);

        gbc.gridx=0; gbc.gridy=7; panel.add(new JLabel("Fees Paid:"), gbc);
        gbc.gridx=1; feesField = new JTextField(15); panel.add(feesField, gbc);

        gbc.gridx=0; gbc.gridy=8; panel.add(new JLabel("Math:"), gbc);
        gbc.gridx=1; mathField = new JTextField(5); panel.add(mathField, gbc);

        gbc.gridx=0; gbc.gridy=9; panel.add(new JLabel("Science:"), gbc);
        gbc.gridx=1; scienceField = new JTextField(5); panel.add(scienceField, gbc);

        gbc.gridx=0; gbc.gridy=10; panel.add(new JLabel("SST:"), gbc);
        gbc.gridx=1; sstField = new JTextField(5); panel.add(sstField, gbc);

        gbc.gridx=0; gbc.gridy=11; panel.add(new JLabel("English:"), gbc);
        gbc.gridx=1; englishField = new JTextField(5); panel.add(englishField, gbc);

        gbc.gridx=0; gbc.gridy=12; panel.add(new JLabel("Photo:"), gbc);
        gbc.gridx=1;
        JPanel photoPanel = new JPanel(new BorderLayout());
        photoLabel = new JLabel();
        photoLabel.setPreferredSize(new Dimension(100,100));
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        choosePhotoBtn = new JButton("Choose Photo");
        photoPanel.add(photoLabel, BorderLayout.CENTER);
        photoPanel.add(choosePhotoBtn, BorderLayout.SOUTH);
        panel.add(photoPanel, gbc);

        choosePhotoBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                studentPhoto = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH));
                photoLabel.setIcon(studentPhoto);
            }
        });

        gbc.gridx=0; gbc.gridy=13; gbc.gridwidth=2;
        saveBtn = new JButton("Save Student");
        panel.add(saveBtn, gbc);

        saveBtn.addActionListener(e -> saveStudent());
    }

    private void saveStudent(){
        try{
            String username = usernameField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String gender = (String) genderCombo.getSelectedItem();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String reg = registrationField.getText();
            double feesPaid = Double.parseDouble(feesField.getText());
            double math = Double.parseDouble(mathField.getText());
            double science = Double.parseDouble(scienceField.getText());
            double sst = Double.parseDouble(sstField.getText());
            double english = Double.parseDouble(englishField.getText());

            Student student = new Student(username, firstName, lastName);
            student.setGender(gender);
            student.setPhone(phone);
            student.setEmail(email);
            student.setRegistrationNumber(reg);
            student.setFeesPaid(feesPaid);
            student.setGrade("Math", math);
            student.setGrade("Science", science);
            student.setGrade("SST", sst);
            student.setGrade("English", english);
            student.setPhoto(studentPhoto);

            studentService.addStudent(student);
            JOptionPane.showMessageDialog(this,"Student added successfully!");
            dispose();
        } catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
        }
    }
}