const students = [
    { ID: 1, FirstName: 'John', LastName: 'Doe', Gender: 'Male', GPA: 3.5, Level: 3, Address: '123 Main Street' },
    { ID: 2, FirstName: 'Jane', LastName: 'Doe', Gender: 'Female', GPA: 4.0, Level: 4, Address: '456 Oak Avenue' },
    { ID: 3, FirstName: 'Jane', LastName: 'Doe', Gender: 'Female', GPA: 4.0, Level: 4, Address: '456 Oak Avenue' },
    { ID: 4, FirstName: 'Jane', LastName: 'Doe', Gender: 'Female', GPA: 4.0, Level: 4, Address: '456 Oak Avenue' },
];


// Function to display students in the table
function displayStudents() {
    const tableBody = document.getElementById('studentTableBody');
    tableBody.innerHTML = ''; // Clear existing data

    students.forEach(student => {
        const row = tableBody.insertRow();
        const fullName = `${student.FirstName} ${student.LastName}`;

        row.innerHTML = `<td>${student.ID}</td>
                         <td>${fullName}</td>
                         <td>
                            <button class="deleteButton" onclick="deleteStudent(${student.ID})">Delete</button>
                            <button class="viewButton" onclick="openStudentDetailsModal(${student.ID})">View</button>
                         </td>`;
    });
}

// Function to delete a student (replace this with your delete logic)
function deleteStudent(studentID) {
    // Implement your delete logic here
    console.log(`Deleting student with ID: ${studentID}`);
}

// Function to open student details modal (replace this with your view logic)
function openStudentDetailsModal(studentID) {
    // Implement your view logic here
    console.log(`Viewing details for student with ID: ${studentID}`);
}

// Function to search for students
function searchStudents() {
    const input = document.getElementById('searchInput');
    const filter = input.value.toUpperCase();

}

// Function to open student details modal
function openStudentDetailsModal(studentID) {
    const student = students.find(s => s.ID === studentID);
    const modal = document.getElementById('studentDetailsModal');
    const studentDetailsContainer = document.getElementById('studentDetails');

    if (student) {
        const detailsHTML = `
            <h3> Student's Info </h3>
            <p> <strong>Student's ID:</strong> ${student.ID}</p>
            <p><strong>Firstname:</strong> ${student.FirstName}</p>
            <p><strong>Lastname:</strong> ${student.LastName}</p>
            <p><strong>Level: </strong>${student.Level}</p>
            <p><strong>GPA: </strong>${student.GPA}</p>
            <p><strong>Gender: </strong>${student.Gender}</p>
            <p><strong>Address: </strong>${student.Address}</p>
        `;

        studentDetailsContainer.innerHTML = detailsHTML;
        modal.style.display = 'block';
    }
}

// Function to close student details modal
function closeStudentDetailsModal() {
    const modal = document.getElementById('studentDetailsModal');
    modal.style.display = 'none';
}

function addStudent() {
    // to be implemeted
}

// Close the modal if the user clicks outside of it
window.onclick = function (event) {
    const modal = document.getElementById('studentDetailsModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
};


// Display students when the page loads
window.onload = displayStudents;

