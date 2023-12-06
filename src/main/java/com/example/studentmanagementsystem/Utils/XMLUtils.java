package com.example.studentmanagementsystem.Utils;

import com.example.studentmanagementsystem.Models.Student;
import com.example.studentmanagementsystem.Repositories.UniversityRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class XMLUtils {
    private static final String FILE_PATH = "src\\main\\resources\\StudentsData.xml";
    private static final File xmlFile = new File(FILE_PATH);
    private final JAXBContext jaxbContext;
    private final UniversityRepository universityRepository;

    public XMLUtils() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(UniversityRepository.class, Student.class);
        this.universityRepository = convertXMLToObject();
    }

    // Marshalling: Converting Java object to XML. (Serialization)
    public void convertObjectToXML(UniversityRepository universityRepository) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(universityRepository, xmlFile);
    }

    // Unmarshalling: Converting XML to Java object. (Deserialization)
    public UniversityRepository convertXMLToObject() throws JAXBException {

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        if (!xmlFile.exists() || xmlFile.length() == 0) {
            return new UniversityRepository();
        }

        return (UniversityRepository) unmarshaller.unmarshal(xmlFile);
    }

    public UniversityRepository getUniversity() {
        return universityRepository;
    }
}
