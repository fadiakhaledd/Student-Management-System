async function sortStudents() {
    let sortBy = document.getElementById("sortBy").value;
    let sortOrder = document.getElementById("sortOrder").value;

    console.log(sortBy)
    if (sortBy === "" || sortOrder === "") return;
    try {
        const response = await fetch(`${BASE_URL}/students/sort/${sortBy}/${sortOrder}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            alert(`Request failed with status: ${response.status}`);
        }
    } catch (error) {
        console.error(`Error during the request: ${error.message}`);
        alert(`Error during the request: ${error.message}`);
    }
}