"use strict";

const BASE_URL = 'http://localhost:8080';

let studentsList = [];

const StudentLevel = {
    1: "First",
    2: "Second",
    3: "Third",
    4: "Fourth",
    5: "Fifth"
};

async function initializeStudentsList() {
    studentsList = await fetchStudentsData();
    await displayStudents(studentsList);
}

async function fetchStudentsData() {
    try {
        const response = await fetch(`${BASE_URL}/students/`);
        return await response.json();
    } catch (error) {
        console.error("Error fetching student data:", error);
        return [];
    }
}

// Function to display students in the table
async function displayStudents(students) {

    const tableBody = document.getElementById("studentTableBody");
    tableBody.innerHTML = ""; // Clear existing data

    students.forEach((student) => {
        const row = tableBody.insertRow();
        const fullName = `${student.FirstName} ${student.LastName}`;

        row.innerHTML = `<td>${student.ID}</td>
                         <td>${fullName}</td>
                         <td>${StudentLevel[student.Level]}</td>
                         <td>${student.GPA}</td>
                         <td>
                            <button  class="btn btn-info" onclick="openStudentDetailsModal(${student.ID})">View</button>
                            <button class="btn btn-primary" onclick="openStudentEditDetailsModal(${student.ID})">Edit</button>
                            <button  class="btn btn-danger" onclick="deleteStudent(${student.ID})">Delete</button>

                         </td>`;
    });
}


initializeStudentsList()
