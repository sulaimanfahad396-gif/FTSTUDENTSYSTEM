package service;

import models.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student){ students.add(student); }

    public List<Student> getAllStudents(){ return students; }

    public Student getStudentByUsername(String username){
        return students.stream()
                .filter(s -> s.getUsername().equals(username))
                .findFirst().orElse(null);
    }
}