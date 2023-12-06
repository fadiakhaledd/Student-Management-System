package com.example.studentmanagementsystem.Controllers;

import com.example.studentmanagementsystem.Models.Gender;
import com.example.studentmanagementsystem.Models.Student;
import com.example.studentmanagementsystem.Services.UniversityService;
import com.example.studentmanagementsystem.dtos.SearchStudentDTO;
import com.example.studentmanagementsystem.dtos.StudentDto;
import com.example.studentmanagementsystem.dtos.StudentsListDto;
import com.example.studentmanagementsystem.dtos.UpdateStudentDto;
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

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping(value = "/add/{numberOfStudents}")
    public ResponseEntity<Object> AddStudents(
            @PathVariable int numberOfStudents,
            @RequestBody @NotNull StudentsListDto studentsListDto) throws JAXBException {


        if (numberOfStudents < 1) {
            return new ResponseEntity<>("Number of students must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        if (studentsListDto.studentsList.size() != numberOfStudents) {
            return new ResponseEntity<>("Number of students in the list does not match the specified number.",
                    HttpStatus.BAD_REQUEST);
        }

        try {
            universityService.addStudents(studentsListDto.studentsList);
            return new ResponseEntity<>("Students added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> UpdateStudent(@PathVariable String id,
                                                @RequestBody UpdateStudentDto updateStudentDto) {
        try {
            Student newStudent = universityService.updateStudent(id, updateStudentDto);
            return new ResponseEntity<>(newStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> DeleteStudent(@PathVariable String id) throws JAXBException {
        try {
            universityService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/sort/{attribute}/{order}")
    public ResponseEntity<Object> SortStudents(@PathVariable String attribute, @PathVariable String order) throws JAXBException {
        try {
            this.universityService.sortStudents(attribute, order);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getStudents(@RequestParam(required = false) String id,
                                              @RequestParam(required = false) String firstname,
                                              @RequestParam(required = false) String lastname,
                                              @RequestParam(required = false) String gender,
                                              @RequestParam(required = false) Float gpa,
                                              @RequestParam(required = false) Integer level,
                                              @RequestParam(required = false) String address) throws JAXBException {
        try {

            SearchStudentDTO studentSearchDTO = new SearchStudentDTO();
            studentSearchDTO.id = id;
            studentSearchDTO.firstname = firstname;
            studentSearchDTO.lastname = lastname;
            studentSearchDTO.gender = gender;
            studentSearchDTO.gpa = gpa;
            studentSearchDTO.level = level;
            studentSearchDTO.address = address;

            List<StudentDto> students;
            students = this.universityService.applySearchFilters(studentSearchDTO);

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
