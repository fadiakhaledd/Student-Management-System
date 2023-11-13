package com.example.studentmanagementsystem.Models;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class University {
    private List<Student> students;
}
