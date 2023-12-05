package com.example.studentmanagementsystem.Utils;

import com.example.studentmanagementsystem.dtos.StudentDto;

import java.util.List;

public class ValidationUtils {

    public void validateStudentsInput(List<StudentDto> studentsDto) {
        for (StudentDto studentDto : studentsDto) {
            boolean isEmptyOrNull = isAnyFieldNullOrEmpty(studentDto);
            boolean firstnameCheck = isLettersOnly(studentDto.getFirstName());
            boolean lastnameCheck = isLettersOnly(studentDto.getLastName());
            boolean gpaCheck = isGpaValid(studentDto.getGPA());

            if (isEmptyOrNull) {
                throw new IllegalArgumentException("One or more fields are empty or null for student with ID: " + studentDto.getID());
            }
            if (!firstnameCheck) {
                throw new IllegalArgumentException("Invalid first name for student with ID: " + studentDto.getID());
            }
            if (!lastnameCheck) {
                throw new IllegalArgumentException("Invalid last name for student with ID: " + studentDto.getID());
            }
            if (!gpaCheck) {
                throw new IllegalArgumentException("Invalid GPA for student with ID: " + studentDto.getID());
            }
        }
    }

    public boolean isAnyFieldNullOrEmpty(StudentDto studentDto) {
        return isEmptyOrNull(studentDto.getID())
                || isEmptyOrNull(studentDto.getFirstName())
                || isEmptyOrNull(studentDto.getLastName())
                || isEmptyOrNull(studentDto.getAddress())
                || studentDto.getGender() == null
                || studentDto.getGPA() == null
                || studentDto.getLevel() == null;
    }

    public boolean isEmptyOrNull(String value) {
        return value == null || value.isEmpty();
    }

    public boolean isLettersOnly(String value) {
        return value.chars().allMatch(Character::isLetter);
    }

    public boolean isGpaValid(Float gpa) {
        return gpa >= 0 && gpa <= 4;
    }
}
