package com.example.studentmanagementsystem.Utils;

import com.example.studentmanagementsystem.dtos.StudentDto;

import java.util.List;

public class ValidationUtils {

    public void validateStudentsInput(List<StudentDto> studentsDto) {
        for (StudentDto studentDto : studentsDto) {
            boolean isEmptyOrNull = isAnyFieldNullOrEmpty(studentDto);
            boolean firstnameCheck = isLettersOnly(studentDto.FirstName);
            boolean lastnameCheck = isLettersOnly(studentDto.LastName);
            boolean addressCheck = isLettersOnly(studentDto.Address);
            boolean gpaCheck = isGpaValid(studentDto.GPA);

            if (isEmptyOrNull) {
                throw new IllegalArgumentException("One or more fields are empty or null for student with ID: " + studentDto.ID);
            }
            if (!firstnameCheck) {
                throw new IllegalArgumentException("Invalid first name for student with ID: " + studentDto.ID);
            }
            if (!lastnameCheck) {
                throw new IllegalArgumentException("Invalid last name for student with ID: " + studentDto.ID);
            }
            if (!addressCheck) {
                throw new IllegalArgumentException("Invalid address for student with ID: " + studentDto.ID);
            }
            if (!gpaCheck) {
                throw new IllegalArgumentException("Invalid GPA for student with ID: " + studentDto.ID);
            }
        }
    }

    public boolean isAnyFieldNullOrEmpty(StudentDto studentDto) {
        return isEmptyOrNull(studentDto.ID)
                || isEmptyOrNull(studentDto.FirstName)
                || isEmptyOrNull(studentDto.LastName)
                || isEmptyOrNull(studentDto.Address)
                || studentDto.Gender == null
                || studentDto.GPA == null
                || studentDto.Level == null;
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
