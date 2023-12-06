"use strict";
const BASE_URL = 'http://localhost:8080';

async function addStudents() {
    try {
        const studentsRequestData = retrieveDataFromCards();

        if (studentsRequestData.length === 0) {
            return;
        }

        const numberOfStudents = studentsRequestData.length;

        const requestBody = {
            studentsList: studentsRequestData
        }

        const response = await fetch(`${BASE_URL}/students/add/${numberOfStudents}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody)
        })

        const data = await response.text();

        if (response.ok) {
            showSuccessAlert(data);
        } else {
            console.error('Error adding students:', response.status, data);
            showErrorAlert(`Error! ${data}`);
        }

    } catch (error) {
        console.log('Error adding students:', error.message);
        showErrorAlert(error.message);
    }
}



function retrieveDataFromCards() {
    const studentsDataContainer = document.getElementById("studentsData");
    let studentsList = [];

    // Loop through each card
    const cards = studentsDataContainer.querySelectorAll('.card');
    cards.forEach(function (card, index) {
        const studentData = {};

        // Loop through form elements in the current card
        const formElements = card.querySelectorAll('.form-control');

        formElements.forEach(function (element) {
            let fieldName = "";

            // Customize field names based on the ID of the current element
            switch (element.id) {
                case `studentID${index + 1}`:
                    fieldName = "ID";
                    break;
                case `firstName${index + 1}`:
                    fieldName = "FirstName";
                    break;
                case `lastName${index + 1}`:
                    fieldName = "LastName";
                    break;
                case `gender${index + 1}`:
                    fieldName = "Gender";
                    break;
                case `gpa${index + 1}`:
                    fieldName = "GPA";
                    break;
                case `level${index + 1}`:
                    fieldName = "Level";
                    break;
                case `address${index + 1}`:
                    fieldName = "Address";
                    break;
                default:
                    break;
            }

            let fieldValue = element.value.trim(); // Trim to handle spaces

            if (fieldName === 'Gender' && fieldValue !== "") {
                fieldValue = fieldValue.toUpperCase();
            }

            studentData[fieldName] = fieldValue;
        });

        // Validate student data
        if (!validateStudentData(studentData, index)) {
            return;
        }

        studentsList.push(studentData);
    });

    return studentsList;
}

function validateStudentData(studentData, index) {

    for (const fieldName in studentData) {
        const fieldValue = studentData[fieldName].trim();

        if (fieldValue === "") {
            alert(`Error: Student ${index + 1} - ${fieldName} cannot be empty.`);
            return false;
        }

        if ((fieldName === 'FirstName' || fieldName === 'LastName' || fieldName === 'Address') && fieldValue !== "") {
            if (!/^[a-zA-Z]+$/.test(fieldValue)) {
                alert(`Invalid ${fieldName} for student ${index + 1}. Please use only characters (a-z).`);
                return false;
            }
        }

        if (fieldName === 'GPA' && fieldValue !== "") {
            const gpaValue = parseFloat(fieldValue);
            if (isNaN(gpaValue) || gpaValue < 0 || gpaValue > 4) {
                alert(`Invalid GPA for student ${index + 1}. Please enter a value between 0 and 4.`);
                return false;
            }
        }

        if (fieldName === 'Level' && fieldValue !== "") {
            const Level = parseInt(fieldValue);
            if (isNaN(Level) || Level < 1 || Level > 5) {
                alert(`Invalid Level for student ${index + 1}. Please enter a value between 1 and 5.`);
                return false;
            }
        }


    }

    return true;
}

function showSuccessAlert(message) {
    const successAlert = `<div id="successAlert" class="alert alert-success alert-dismissible fade show" role="alert">
        <strong>${message}</strong> </div>`;

    displayAlert(successAlert);

    setTimeout(() => {
        removeAlert('successAlert');
    }, 5000);
}

function showErrorAlert(message) {
    const errorAlert = `<div id="errorAlert" class="alert alert-danger alert-dismissible fade show" role="alert"><strong>${message}</strong></div>`

    displayAlert(errorAlert);

    setTimeout(() => {
        removeAlert('errorAlert');
    }, 5000);
}

function removeAlert(alertId) {
    const alertElement = document.getElementById(alertId);
    if (alertElement) {
        alertElement.remove();
    }
}

function displayAlert(alertHtml) {
    const alertContainer = document.getElementById('alertContainer');
    if (alertContainer) {
        alertContainer.innerHTML = alertHtml;
    }
}