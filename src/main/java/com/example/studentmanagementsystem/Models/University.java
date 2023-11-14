package com.example.studentmanagementsystem.Models;

import jakarta.xml.bind.ValidationException;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "University")
public class University {
    List<Student> studentsList = new ArrayList<>();

    @XmlElement(name = "Student")
    public List<Student> getStudents() {
        return studentsList;
    }

    public void addStudents(ArrayList<Student> students) throws ValidationException {
        for (Student student : students) {
            validateStudent(student);
        }
        studentsList.addAll(students);
    }

    private void validateStudent(Student student) throws ValidationException {

        String studentId = student.getID();
        boolean existingId = studentsList.stream().anyMatch(s -> s.getID().equals(studentId));
        if (existingId) {
            throw new ValidationException("Student with ID: " + student.getID() + " already exists");
        }
    }

    public List<Student> getStudentsByFirstname(String firstName) {
        List<Student> students = new ArrayList<>();
        for (Student student : studentsList) {
            if (student.getFirstName().toLowerCase().equals(firstName.toLowerCase())) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> getStudentsByGpa(Float gpa) {
        List<Student> students = new ArrayList<>();
        for (Student student : studentsList) {
            if (student.getGPA().equals(gpa)) {
                students.add(student);
            }
        }
        return students;
    }

    public void removeStudent(String id) {
        boolean removed = studentsList.removeIf(student -> student.getID().equals(id));
        if (!removed) {
            throw new RuntimeException("Student with ID: " + id + " does not exist");
        }
    }
}
