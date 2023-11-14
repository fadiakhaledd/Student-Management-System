package com.example.studentmanagementsystem.Models;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Student")
public class Student {
    private String ID;
    private String FirstName;
    private String LastName;
    private String Gender;
    private Float GPA;
    private Integer Level;
    private String Address;

    @XmlAttribute(name = "ID")
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @XmlElement(name = "FirstName")
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    @XmlElement(name = "LastName")
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    @XmlElement(name = "Gender")
    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    @XmlElement(name = "GPA")
    public Float getGPA() {
        return GPA;
    }

    public void setGPA(Float GPA) {
        this.GPA = GPA;
    }

    @XmlElement(name = "Level")
    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        this.Level = level;
    }

    @XmlElement(name = "Address")
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }
}
