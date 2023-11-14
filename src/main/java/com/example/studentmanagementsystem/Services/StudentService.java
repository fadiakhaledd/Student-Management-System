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
        if (studentDto.Gender == Gender.FEMALE) {
            student.setGender("Female");
        } else {
            student.setGender("Male");
        }
        student.setGPA(studentDto.GPA);
        student.setLevel(studentDto.Level);
        student.setAddress(studentDto.Address);
        return student;
    }

    StudentDto convertStudentToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.ID = student.getID();
        studentDto.FirstName = student.getFirstName();
        studentDto.LastName = student.getLastName();
        if (student.getGender().equals("Female")) {
            studentDto.Gender = Gender.FEMALE;
        } else {
            studentDto.Gender = Gender.MALE;
        }
        studentDto.Level = student.getLevel();
        studentDto.GPA = student.getGPA();
        studentDto.Address = student.getAddress();
        return studentDto;
    }
}
