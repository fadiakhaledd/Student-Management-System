package com.example.studentmanagementsystem.Services;

import com.example.studentmanagementsystem.Models.Gender;
import com.example.studentmanagementsystem.Models.Student;
import com.example.studentmanagementsystem.dtos.StudentDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    Student convertStudentDtoToStudent(StudentDto studentDto) {

        Student student = new Student();
        student.setID(studentDto.getID());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        if (studentDto.getGender() == Gender.FEMALE) {
            student.setGender("Female");
        } else {
            student.setGender("Male");
        }
        student.setGPA(studentDto.getGPA());
        student.setLevel(studentDto.getLevel());
        student.setAddress(studentDto.getAddress());
        return student;
    }

    StudentDto convertStudentToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setID(student.getID());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setLevel(student.getLevel());
        studentDto.setGPA(student.getGPA());
        student.setAddress(studentDto.getAddress());
        if (student.getGender().equals("Female")) {
            studentDto.setGender(Gender.FEMALE);
        } else {
            studentDto.setGender(Gender.MALE);
        }

        return studentDto;
    }
}
