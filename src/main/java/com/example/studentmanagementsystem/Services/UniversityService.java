package com.example.studentmanagementsystem.Services;

import com.example.studentmanagementsystem.Models.Gender;
import com.example.studentmanagementsystem.Models.Student;
import com.example.studentmanagementsystem.Repositories.UniversityRepository;
import com.example.studentmanagementsystem.Utils.ComparatorUtils;
import com.example.studentmanagementsystem.Utils.ValidationUtils;
import com.example.studentmanagementsystem.Utils.XMLUtils;
import com.example.studentmanagementsystem.dtos.SearchStudentDTO;
import com.example.studentmanagementsystem.dtos.StudentDto;
import com.example.studentmanagementsystem.dtos.UpdateStudentDto;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;
    private final StudentService studentService;
    private final XMLUtils xmlUtils = new XMLUtils();
    private final ValidationUtils validationUtils = new ValidationUtils();

    public UniversityService(StudentService studentService) throws JAXBException {
        this.studentService = studentService;
        this.universityRepository = loadUniversityFromXML();
    }

    private UniversityRepository loadUniversityFromXML() throws JAXBException {
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

    public Student updateStudent(String id, UpdateStudentDto updateStudentDto) throws JAXBException {
        Student oldStudent = universityRepository.getStudentById(id);
        if (oldStudent == null) {
            throw new NullPointerException("Student with ID: " + id + " does not exist");
        }

        String firstname = Optional.ofNullable(updateStudentDto.FirstName).orElse(oldStudent.getFirstName());
        String lastname =  Optional.ofNullable(updateStudentDto.LastName).orElse(oldStudent.getLastName());
        Gender gender =  Optional.ofNullable(updateStudentDto.Gender).orElse(Gender.valueOf(oldStudent.getGender()));
        float gpa =  Optional.ofNullable(updateStudentDto.GPA).orElse(oldStudent.getGPA());
        int level =  Optional.ofNullable(updateStudentDto.Level).orElse(oldStudent.getLevel());
        String address =  Optional.ofNullable(updateStudentDto.Address).orElse(oldStudent.getAddress());

        if (!validationUtils.isLettersOnly(firstname)) {
            throw new IllegalArgumentException("First name must contain only letters");
        }

        if (!validationUtils.isLettersOnly(lastname)) {
            throw new IllegalArgumentException("Last name must contain only letters");
        }

        if (!validationUtils.isLettersOnly(address)) {
            throw new IllegalArgumentException("Address must contain only letters");
        }

        if (!validationUtils.isGpaValid(gpa)) {
            throw new IllegalArgumentException("GPA must be between 0 and 4");
        }

        Student newStudent = new Student(oldStudent.getID(), firstname, lastname, gender.toString(), gpa, level, address);

        universityRepository.updateStudent(id, newStudent);
        xmlUtils.convertObjectToXML(this.universityRepository);
        return newStudent;
    }

    public void sortStudents(String attribute, String order) throws JAXBException {
        List<Student> students = this.universityRepository.getStudents();
        ComparatorUtils comparatorUtils = new ComparatorUtils(attribute, order);
        Collections.sort(students, comparatorUtils);
        universityRepository.setStudentsList(students);
        xmlUtils.convertObjectToXML(this.universityRepository);
    }

    public List<StudentDto> applySearchFilters(SearchStudentDTO studentSearchDTO) {
        List<Student> studentsList = universityRepository.getStudents();
        List<Student> filteredStudentsList = studentsList.stream()
                .filter(student ->
                        (studentSearchDTO.id == null || student.getID().contains(studentSearchDTO.id))
                                && (studentSearchDTO.firstname == null || student.getFirstName().toLowerCase()
                                .contains(studentSearchDTO.firstname.toLowerCase()))
                                && (studentSearchDTO.lastname == null || student.getLastName().toLowerCase()
                                        .contains(studentSearchDTO.lastname.toLowerCase()))
                                && (studentSearchDTO.gender == null || student.getGender().equals(studentSearchDTO.gender))
                                && (studentSearchDTO.gpa == null || student.getGPA().equals(studentSearchDTO.gpa))
                                && (studentSearchDTO.level == null || student.getLevel().equals(studentSearchDTO.level))
                                && (studentSearchDTO.address == null || student.getAddress().toLowerCase()
                                        .contains(studentSearchDTO.address.toLowerCase())))
                .toList();

        List<StudentDto> filteredStudentsDto = new ArrayList<>();
        for (Student student : filteredStudentsList) {
            StudentDto studentDto = studentService.convertStudentToStudentDto(student);
            filteredStudentsDto.add(studentDto);
        }
        return filteredStudentsDto;
    }
}
