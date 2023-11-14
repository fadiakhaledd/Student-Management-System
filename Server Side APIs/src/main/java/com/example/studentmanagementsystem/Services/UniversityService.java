package com.example.studentmanagementsystem.Services;

import com.example.studentmanagementsystem.Models.Student;
import com.example.studentmanagementsystem.Models.University;
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
        ArrayList<Student> newStudents = new ArrayList<>();
        for (StudentDto studentDto : studentsDto) {
            Student newStudent = studentService.convertStudentDtoToStudent(studentDto);
            newStudents.add(newStudent);
        }
        this.universityRepository.addStudents(newStudents);
        xmlUtils.convertObjectToXML(this.universityRepository);
    }

    public void deleteStudent(String id) {
        // pass
    }

    public List<Student> getAllStudents() {
        return null;
    }

    public Student getStudentByID(String id) {
        return null;
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        return null;
    }

    public List<Student> getStudentsByGPA(Float gpa) {
        return null;
    }
}
