package com.example.studentmanagementsystem.Utils;

import com.example.studentmanagementsystem.Models.Student;

import java.util.Comparator;


public class ComparatorUtils implements Comparator<Student> {
    private String attributeName;
    private String order;

    public ComparatorUtils(String attributeName, String order) {
        this.attributeName = attributeName.toLowerCase();
        this.order = order;
    }

    @Override
    public int compare(Student student1, Student student2) {
        int result;
        switch (attributeName) {
            case "id":
                result = compareValues(Integer.parseInt(student1.getID()), Integer.parseInt(student2.getID()));
                break;
            case "firstname":
                result = compareValues(student1.getFirstName(), student2.getFirstName());
                break;
            case "lastname":
                result = compareValues(student1.getLastName(), student2.getLastName());
                break;
            case "level":
                result = compareValues(student1.getLevel(), student2.getLevel());
                break;
            case "gpa":
                result = compareValues(student1.getGPA(), student2.getGPA());
                break;
            case "gender":
                result = compareValues(student1.getGender(), student2.getGender());
                break;
            case "address":
                result = compareValues(student1.getAddress(), student2.getAddress());
                break;
            default:
                throw new IllegalArgumentException("Invalid attribute name: " + attributeName);
        }
        return "desc".equals(order) ? -result : result;

    }

    public <T extends Comparable<T>> int compareValues(T value1, T value2) {

        if (value1 == null && value2 == null) {
            return 0;
        } else if (value1 == null) {
            return -1;
        } else if (value2 == null) {
            return 1;
        }
        return value1.compareTo(value2);
        // returns 0 if the two values are equal,
        // 1 if the first value is greater than the second value,
        // -1 otherwise
    }
}
