package com.example.studentmanagementsystem.Repositories;

import com.example.studentmanagementsystem.Models.Student;
import jakarta.xml.bind.ValidationException;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "University")
public class UniversityRepository {
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

    public Student getStudentById(String id) {
        for (Student student : studentsList) {
            if (Objects.equals(student.getID(), id)) {
                return student;
            }
        }
        return null;
    }

    public void removeStudent(String id) {
        boolean removed = studentsList.removeIf(student -> student.getID().equals(id));
        if (!removed) {
            throw new RuntimeException("Student with ID: " + id + " does not exist");
        }
    }

    public void updateStudent(String id, Student student) {
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getID().equals(id)) {
                studentsList.set(i, student);
                return;
            }
        }
    }

    public void setStudentsList(List<Student> students) {
        this.studentsList = students;
    }
}
