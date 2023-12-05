package com.example.studentmanagementsystem.Services;

import com.example.studentmanagementsystem.Models.Student;
import com.example.studentmanagementsystem.Models.University;
import com.example.studentmanagementsystem.Utils.ValidationUtils;
import com.example.studentmanagementsystem.Utils.XMLUtils;
import com.example.studentmanagementsystem.dtos.StudentDto;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {
    private final University universityRepository;
    private final StudentService studentService;
    private final XMLUtils xmlUtils = new XMLUtils();
    private final ValidationUtils validationUtils = new ValidationUtils();

    public UniversityService(StudentService studentService) throws JAXBException {
        this.studentService = studentService;
        this.universityRepository = loadUniversityFromXML();
    }

    private University loadUniversityFromXML() throws JAXBException {
        try {
            return xmlUtils.convertXMLToObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load University data from XML", e);
        }
    }

    public void addStudents(List<StudentDto> studentsDto) throws JAXBException {

        validationUtils.validateStudentsInput(studentsDto);

        ArrayList<Student> newStudents = new ArrayList<>();
        for (StudentDto studentDto : studentsDto) {
            Student newStudent = studentService.convertStudentDtoToStudent(studentDto);
            newStudents.add(newStudent);
        }

        this.universityRepository.addStudents(newStudents);
        xmlUtils.convertObjectToXML(this.universityRepository);
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = this.universityRepository.getStudents();
        return mapListOfStudents(students);
    }


    public List<StudentDto> getStudentsByFirstName(String firstName) {
        List<Student> students = this.universityRepository.getStudentsByFirstname(firstName);

        return mapListOfStudents(students);
    }

    public List<StudentDto> getStudentsByGPA(Float gpa) {
        List<Student> gpaFilter = this.universityRepository.getStudentsByGpa(gpa);
        return mapListOfStudents(gpaFilter);
    }

    public List<StudentDto> getStudentsByFirstNameAndGPA(String firstName, Float gpa) {
        List<Student> firstnameFilter = this.universityRepository.getStudentsByFirstname(firstName);
        List<Student> gpaFilter = this.universityRepository.getStudentsByGpa(gpa);

        List<Student> intersection = new ArrayList<>();

        if ((firstnameFilter.size() != 0) && (gpaFilter.size() != 0)) {
            for (Student student : firstnameFilter) {
                if (gpaFilter.contains(student)) {
                    intersection.add(student);
                }
            }
        }
        return mapListOfStudents(intersection);
    }

    private List<StudentDto> mapListOfStudents(List<Student> students) {
        List<StudentDto> studentsDto = new ArrayList<>();
        for (Student student : students) {
            StudentDto studentDto1 = studentService.convertStudentToStudentDto(student);
            studentsDto.add(studentDto1);
        }
        return studentsDto;
    }

    public void deleteStudent(String id) throws JAXBException {
        this.universityRepository.removeStudent(id);
        xmlUtils.convertObjectToXML(this.universityRepository);
    }

    public void updateStudent(String id) throws JAXBException {
        universityRepository.updateStudent(id);
        xmlUtils.convertObjectToXML(this.universityRepository);
    }
}
