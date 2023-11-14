package com.example.studentmanagementsystem.Services;

import com.example.studentmanagementsystem.Models.Gender;
import com.example.studentmanagementsystem.Models.Student;
import com.example.studentmanagementsystem.dtos.StudentDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    Student convertStudentDtoToStudent(StudentDto studentDto) {

        Student student = new Student();
        student.setID(studentDto.ID);
        student.setFirstName(studentDto.FirstName);
        student.setLastName(studentDto.LastName);
        if (studentDto.Gender == Gender.MALE) {
            student.setGender("Male");
        }else {
            student.setGender("Female");
        }
        student.setGPA(studentDto.GPA);
        student.setLevel(studentDto.Level);
        student.setAddress(studentDto.Address);
        return student;

    }

}
