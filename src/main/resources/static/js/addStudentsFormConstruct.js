"use strict";

function navigateToStudentForm() {
    let numStudents = document.getElementById("numStudents").value;
    if (numStudents > 0) {
        window.location.href = "studentForm.html?numStudents=" + numStudents;
    } else {
        alert("Please enter a valid number of students.");
    }
}

// Function to create form elements for each student
function createStudentForms(numStudents) {
    const studentsDataContainer = document.getElementById("studentsData");

    // Clear previous content
    studentsDataContainer.innerHTML = "";

    for (let i = 1; i <= numStudents; i++) {
        const studentDiv = document.createElement("div");
        studentDiv.classList.add("student-container", "border", "p-4", "rounded", "mb-3");

        studentDiv.innerHTML = `
            <div class="card">
                <div class="card-header" id="heading${i}">
                    <h4 class="mb-0">
                        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">
                            Student ${i}
                        </button>
                    </h4>
                </div>

                <div id="collapse${i}" class="collapse" aria-labelledby="heading${i}" data-parent="#studentsData">
                    <div class="card-body">
                        <div class="form-group">
                            <label for="studentID${i}">Student ID <span class="text-danger">*</span>:</label>
                            <input type="text" id="studentID${i}" name="studentID${i}" class="form-control" placeholder="Enter student ID" required>
                        </div>
                        <div class="form-group">
                            <label for="firstName${i}">First Name <span class="text-danger">*</span>:</label>
                            <input type="text" id="firstName${i}" name="firstName${i}" class="form-control" placeholder="Enter first name (characters a-z)" required>
                        </div>

                        <div class="form-group">
                            <label for="lastName${i}">Last Name <span class="text-danger">*</span>:</label>
                            <input type="text" id="lastName${i}" name="lastName${i}" class="form-control" placeholder="Enter last name (characters a-z)" required>
                        </div>

                        <div class="form-group">
                            <label for="gender${i}">Gender <span class="text-danger">*</span>:</label>
                            <select id="gender${i}" name="gender${i}" class="form-control" required>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="level${i}">Level <span class="text-danger">*</span>:</label>
                            <input type="number" id="level${i}" name="level${i}" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="gpa${i}">GPA (0 to 4):<span class="text-danger">*</span>:</label>
                            <input type="number" id="gpa${i}" name="gpa${i}" step="0.1" class="form-control" placeholder="Enter GPA (0 to 4)" step="0.01" min="0" max="4" required>
                        </div>


                        <div class="form-group">
                            <label for="address${i}">Address <span class="text-danger">*</span>:</label>
                            <input type="text" id="address${i}" name="address${i}" class="form-control" required>
                        </div>
                    </div>
                </div>
            </div>`;
        studentsDataContainer.appendChild(studentDiv);
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const addStudentForm = document.getElementById("addStudentForm");
    const submitBtn = document.getElementById("submitBtn");
    submitBtn.disabled = true;

    addStudentForm.addEventListener("input", function () {
        const isValid = isFormValid(addStudentForm);
        submitBtn.disabled = !isValid;
    });

    function isFormValid(form) {
        const elements = form.elements;

        for (let i = 0; i < elements.length; i++) {
            if (elements[i].hasAttribute("required") && !elements[i].value) {
                return false;
            }
        }
        return true;
    }
});


