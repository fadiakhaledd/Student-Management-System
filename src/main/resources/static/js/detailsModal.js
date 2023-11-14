"use strict";

function openStudentDetailsModal(studentID) {
    const student = studentsList.find(
        (student) => parseInt(student.ID) === studentID
    );

    const modal = document.getElementById("studentDetailsModal");
    const studentDetailsContainer = document.getElementById("studentDetails");

    if (student) {
        studentDetailsContainer.innerHTML = `
            <h3> Student's Info </h3>
            <p> <strong>Student's ID:</strong> ${student.ID}</p>
            <p><strong>Firstname:</strong> ${student.FirstName}</p>
            <p><strong>Lastname:</strong> ${student.LastName}</p>
            <p><strong>Level: </strong>${student.Level}</p>
            <p><strong>GPA: </strong>${student.GPA}</p>
            <p><strong>Gender: </strong>${student.Gender}</p>
            <p><strong>Address: </strong>${student.Address}</p>
        `;
        modal.style.display = "block";
    }
}

// Close the modal if the user clicks outside it
window.onclick = function (event) {
    const modal = document.getElementById("studentDetailsModal");
    if (event.target === modal) {
        modal.style.display = "none";
    }
};