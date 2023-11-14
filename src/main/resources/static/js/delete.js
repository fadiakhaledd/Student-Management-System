"use strict";

async function deleteStudent(studentID) {
    console.log(`Deleting student with ID: ${studentID}`);

    try {
        const response = await fetch(`${BASE_URL}/students/${studentID}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (response.status === 200) {
            // Do nothing if the response status is 200
        } else if (response.status === 400) {
            const error = (await response.json()).error || "Unknown error";
            alert(`Error: ${error}`);
        } else {
            alert(`Unexpected response: ${response.status}`);
        }
    } catch (error) {
        console.error(`Error during the request: ${error.message}`);
        alert(`Error during the request: ${error.message}`);
    }
}