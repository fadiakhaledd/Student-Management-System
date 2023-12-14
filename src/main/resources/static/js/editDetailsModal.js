"use strict";
function openStudentEditDetailsModal(studentID) {
    const student = studentsList.find((student) => parseInt(student.ID) === studentID);

    const modal = document.getElementById("studentDetailsModal");
    const studentDetailsContainer = document.getElementById("studentDetails");

    if (student) {
        studentDetailsContainer.innerHTML = `
        <h3> Edit Student's Info </h3> <br>
        <div id="alertContainer"></div>

        <p><strong>Student's ID:</strong> ${student.ID}</p> 

        <form id="editForm">
            <div class="form-group editModal">

                <div class="form-group">
                    <label for="editFirstName" class="bold-label">First Name:</label>
                    <input type="text" id="editFirstName" name="editFirstName" class="form-control" value="${student.FirstName}">
                </div>

                <div class="form-group">
                    <label for="editLastName" class="bold-label">Last Name:</label>
                    <input type="text" id="editLastName" name="editLastName" class="form-control" value="${student.LastName}">
                </div>

                <div class="form-group">
                    <label for="editGender" class="bold-label">Gender:</label>
                    <select id="editGender" name="editGender" class="form-control">
                        <option value="Male" ${student.Gender === 'MALE' ? 'selected' : ''}>Male</option>
                        <option value="Female" ${student.Gender === 'FEMALE' ? 'selected' : ''}>Female</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="editLevel" class="bold-label">Level:</label>
                    <input type="number" id="editLevel" name="editLevel" class="form-control" value="${student.Level}">
                </div>

                <div class="form-group">
                    <label for="editGPA" class="bold-label">GPA:</label>
                    <input type="number" id="editGPA" name="editGPA" class="form-control" step="0.1" min="0" max="4" value="${student.GPA}">
                </div>

                <div class="form-group">
                    <label for="editAddress" class="bold-label">Address:</label>
                    <input type="text" id="editAddress" name="editAddress" class="form-control" value="${student.Address}">
                </div>

                <button type="button" class="btn btn-primary" onclick="saveChanges(${studentID})">Save Changes</button>

            </div>
        </form>
        `;
        modal.style.display = "block";
    }
}

async function saveChanges(studentID) {

    const updatedFirstName = document.getElementById("editFirstName").value;
    const updatedLastName = document.getElementById("editLastName").value;
    const updatedGender = document.getElementById("editGender").value.toUpperCase();
    const updatedLevel = document.getElementById("editLevel").value;
    const updatedGPA = document.getElementById("editGPA").value;
    const updatedAddress = document.getElementById("editAddress").value;


    let student = {
        ...(updatedFirstName && { FirstName: updatedFirstName }),
        ...(updatedLastName && { LastName: updatedLastName }),
        ...(updatedGender && { Gender: updatedGender }),
        ...(updatedLevel && { Level: updatedLevel }),
        ...(updatedGPA && { GPA: updatedGPA }),
        ...(updatedAddress && { Address: updatedAddress })
    };

    if (!validateInputData(student)) {
        return;
    }

    try {
        const response = await fetch(`${BASE_URL}/students/${studentID}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(student)
        })

        if (!response.ok) {
            console.log(`HTTP error! Status: ${response.status}`);
        } else {
            alert("Student Updated Successfully")
        }

    } catch (error) {
        console.log('Error adding students:', error);
        alert(error.message);
    }

}

function validateInputData(studentData) {
    for (const fieldName in studentData) {
        const fieldValue = studentData[fieldName].trim();

        if ((fieldName === 'FirstName' || fieldName === 'firstname' || fieldName === 'LastName' || fieldName === 'lastname'
            || fieldName === 'Address' || fieldName === 'address')) {
            if (!/^[a-zA-Z]+$/.test(fieldValue)) {
                alert(`Invalid ${fieldName}. Please use only characters (a-z).`);
                return false;
            }
        }

        if (fieldName === 'GPA' || fieldName === 'gpa') {
            const gpaValue = parseFloat(fieldValue);
            if (isNaN(gpaValue) || gpaValue < 0 || gpaValue > 4) {
                alert(`Invalid GPA for student. Please enter a value between 0 and 4.`);
                return false;
            }
        }

        if ((fieldName === 'Level' || fieldName === 'level') && fieldValue !== "") {
            const Level = parseInt(fieldValue);
            if (isNaN(Level) || Level < 1 || Level > 5) {
                alert(`Invalid Level for student. Please enter a value between 1 and 5.`);
                return false;
            }
        }

    }

    return true;
}
