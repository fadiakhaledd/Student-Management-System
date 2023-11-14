"use strict";

const BASE_URL = 'http://localhost:8080';

let studentsList = [];

const StudentLevel = {
    1: "First",
    2: "Second",
    3: "Third",
    4: "Fourth",
};

async function initializeStudentsList() {
    studentsList = await fetchStudentsData();
    await displayStudents(studentsList);

    document.getElementById('searchFirstName').addEventListener('keyup', searchStudents);
    document.getElementById('searchGPA').addEventListener('keyup', searchStudents);

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
                            <button class="deleteButton" onclick="deleteStudent(${student.ID
            })">Delete</button>
                            <button class="viewButton" onclick="openStudentDetailsModal(${student.ID
            })">View</button>
                         </td>`;
    });
}


initializeStudentsList()
