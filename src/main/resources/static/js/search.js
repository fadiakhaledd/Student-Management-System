"use strict";

function applySearchFilter() {
    const id = document.getElementById('searchID').value;
    const firstname = document.getElementById('searchFirstName').value;
    const lastname = document.getElementById('searchLastName').value;
    const gender = document.getElementById('searchGender').value;
    const gpa = document.getElementById('searchGPA').value;
    const level = document.getElementById('searchLevel').value;
    const address = document.getElementById('searchAddress').value;

    const queryParams = {
        ...(id && { id }),
        ...(firstname && { firstname }),
        ...(lastname && { lastname }),
        ...(gender && { gender }),
        ...(level && { level }),
        ...(gpa && { gpa }),
        ...(address && { address }),
    };

    if (!validateInputData(queryParams)) {
        return;
    }

    searchStudentsRequest(queryParams);
}

async function searchStudentsRequest(queryParams) {
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

        document.getElementById("resultCount").innerText = `Found ${filteredStudents.length} students.`;

        await displayStudents(filteredStudents);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}