"use strict";
const BASE_URL = 'http://localhost:8080';

async function addStudents() {
    try {
        const studentsRequestData = retrieveDataFromCards();
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


        const data = await response.json().then(data => {
            return data
        });

        if (response.ok) {
            showSuccessAlert(data.message);
        } else {
            showErrorAlert(`${data.error}`);
        }

    } catch (error) {
        console.error('Error adding students:', error.message);
        showErrorAlert(error.message);
    }
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

            let fieldValue = element.value;

            if (fieldName === 'Gender') {
                fieldValue = fieldValue.toUpperCase();
            }

            studentData[fieldName] = fieldValue;
        });

        studentsList.push(studentData);

    });
    return studentsList;
}
