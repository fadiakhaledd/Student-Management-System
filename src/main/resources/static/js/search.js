"use strict";

function searchStudents() {
    const firstNameInput = document.getElementById('searchFirstName').value.toLowerCase();
    const gpaInput = document.getElementById('searchGPA').value;

    const queryParams = {
        ...(firstNameInput && { firstname: firstNameInput }),
        ...(gpaInput && { gpa: gpaInput })
    };

    filterStudents(queryParams);
}

async function filterStudents(queryParams) {
    const url = new URL(`${BASE_URL}/students/`);

    Object.keys(queryParams).forEach(key => {
        url.searchParams.set(key, queryParams[key]);
    });

    try {
        const response = await fetch(url);

        if (!response.ok) {
            alert(`HTTP error! Status: ${response.status}`);
        }
        const filteredStudents = await response.json();

        await displayStudents(filteredStudents);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}