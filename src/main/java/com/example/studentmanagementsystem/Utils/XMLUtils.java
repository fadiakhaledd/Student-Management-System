package com.example.studentmanagementsystem.Utils;

import com.example.studentmanagementsystem.Models.Student;
import com.example.studentmanagementsystem.Models.University;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class XMLUtils {
    private static final String FILE_PATH = "src\\main\\resources\\StudentsData.xml";
    private static final File xmlFile = new File(FILE_PATH);
    private final JAXBContext jaxbContext;
    private final University university;

    public XMLUtils() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(University.class, Student.class);
        this.university = convertXMLToObject();
    }

    // Marshalling: Converting Java object to XML. (Serialization)
    public void convertObjectToXML(University university) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(university, xmlFile);
    }

    // Unmarshalling: Converting XML to Java object. (Deserialization)
    public University convertXMLToObject() throws JAXBException {

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        if (!xmlFile.exists() || xmlFile.length() == 0) {
            return new University();
        }

        return (University) unmarshaller.unmarshal(xmlFile);
    }

    public University getUniversity() {
        return university;
    }
}
