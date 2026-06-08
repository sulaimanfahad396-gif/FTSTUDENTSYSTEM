package ui;

import models.Student;
import service.StudentService;

public class DemoData {

    public static void addDemoStudents(StudentService studentService) {
        Student s1 = new Student("student1", "John", "Doe");
        s1.setFeesPaid(2000);
        s1.setGrade("Math", 85);
        s1.setGrade("Science", 90);
        s1.setGrade("SST", 80);
        s1.setGrade("English", 88);

        Student s2 = new Student("student2", "Mary", "Jane");
        s2.setFeesPaid(1500);
        s2.setGrade("Math", 70);
        s2.setGrade("Science", 65);
        s2.setGrade("SST", 75);
        s2.setGrade("English", 78);

        studentService.addStudent(s1);
        studentService.addStudent(s2);
    }
}