package com.example.studentmanagementsystem.dtos;


import com.example.studentmanagementsystem.Models.Gender;

public class StudentDto {
    public String ID;
    public String FirstName;
    public String LastName;
    public Gender Gender;
    public Float GPA;
    public Integer Level;
    public String Address;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public com.example.studentmanagementsystem.Models.Gender getGender() {
        return Gender;
    }

    public void setGender(com.example.studentmanagementsystem.Models.Gender gender) {
        Gender = gender;
    }

    public Float getGPA() {
        return GPA;
    }

    public void setGPA(Float GPA) {
        this.GPA = GPA;
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }



}
