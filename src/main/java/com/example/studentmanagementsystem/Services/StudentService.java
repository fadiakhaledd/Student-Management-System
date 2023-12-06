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
        student.setGPA(studentDto.GPA);
        student.setLevel(studentDto.Level);
        student.setAddress(studentDto.Address);

        if (studentDto.Gender == Gender.FEMALE) {
            student.setGender(Gender.FEMALE.toString());
        } else {
            student.setGender(Gender.MALE.toString());
        }
        return student;
    }

    StudentDto convertStudentToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.ID = student.getID();
        studentDto.FirstName = student.getFirstName();
        studentDto.LastName = student.getLastName();
        studentDto.Level = student.getLevel();
        studentDto.GPA = student.getGPA();
        studentDto.Address = student.getAddress();

        student.setAddress(studentDto.Address);
        if (student.getGender().equals(Gender.FEMALE.toString())) {
            studentDto.Gender = Gender.FEMALE;
        } else {
            studentDto.Gender = Gender.MALE;
        }

        return studentDto;
    }
}
