package com.example.studentmanagementsystem.Controllers;

import com.example.studentmanagementsystem.Services.UniversityService;
import com.example.studentmanagementsystem.dtos.AddStudentsDTO;
import com.example.studentmanagementsystem.dtos.StudentDto;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")

public class UniversityController {
    private final UniversityService universityService;

    // @Autowired is used to inject the bean dependencies automatically.
    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping(value = "/add/{numberOfStudents}")
    public ResponseEntity<Object> AddStudents(
            @PathVariable int numberOfStudents,
            @RequestBody @NotNull AddStudentsDTO requestDto) throws JAXBException {

        if (numberOfStudents < 1) {
            return ResponseEntity.badRequest()
                    .body("{\"error\": \"Number of students must be greater than 0\"}");
        }

        if (requestDto.studentsList.size() != numberOfStudents) {
            return ResponseEntity.badRequest()
                    .body("{\"error\": \"Number of students in the list does not match the specified number.\"}");
        }
        try {
            universityService.addStudents(requestDto.studentsList);
            return ResponseEntity.ok().body("{\"message\": \"Students added successfully.\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("{\"error\": \"Students could not be added. " + e.getMessage() + "\"}");
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAllStudents(
            @RequestParam(value = "firstname", required = false) String firstName,
            @RequestParam(value = "gpa", required = false) Float gpa) throws JAXBException {
        if (firstName != null && gpa != null) {

            List<StudentDto> studentsByFirstNameAndGPA = universityService.getStudentsByFirstNameAndGPA(firstName, gpa);
            return ResponseEntity.ok().body(studentsByFirstNameAndGPA);

        } else if (firstName != null) {
            List<StudentDto> studentsByFirstName = universityService.getStudentsByFirstName(firstName);
            return ResponseEntity.ok().body(studentsByFirstName);

        } else if (gpa != null) {
            List<StudentDto> studentsByGPA = universityService.getStudentsByGPA(gpa);
            return ResponseEntity.ok().body(studentsByGPA);

        } else {
            List<StudentDto> allStudents = universityService.getAllStudents();
            return ResponseEntity.ok().body(allStudents);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> DeleteStudent(@PathVariable String id) throws JAXBException {
        try {
            universityService.deleteStudent(id);
            return ResponseEntity.ok().body("{\"message\": \"Student deleted successfully.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Student could not be deleted. " + e.getMessage() + "\"}");
        }
    }
}
