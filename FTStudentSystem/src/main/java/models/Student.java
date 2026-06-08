package models;

import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String username;
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;
    private String registrationNumber;
    private double totalFees = 2000;
    private double feesPaid;
    private Map<String,Double> grades = new HashMap<>();
    private ImageIcon photo;

    public Student(String username,String firstName,String lastName){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        grades.put("Math",0.0);
        grades.put("Science",0.0);
        grades.put("SST",0.0);
        grades.put("English",0.0);
    }

    public String getUsername(){ return username; }
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public String getGender(){ return gender; }
    public void setGender(String gender){ this.gender = gender; }
    public String getPhone(){ return phone; }
    public void setPhone(String phone){ this.phone = phone; }
    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
    public String getRegistrationNumber(){ return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber){ this.registrationNumber = registrationNumber; }
    public double getTotalFees(){ return totalFees; }
    public double getFeesPaid(){ return feesPaid; }
    public void setFeesPaid(double feesPaid){ this.feesPaid = feesPaid; }
    public Map<String,Double> getGrades(){ return grades; }
    public void setGrade(String subject,double mark){ grades.put(subject, mark); }
    public ImageIcon getPhoto(){ return photo; }
    public void setPhoto(ImageIcon photo){ this.photo = photo; }
    public boolean isExamEligible(){ return feesPaid>=totalFees; }
}