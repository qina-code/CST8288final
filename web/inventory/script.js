/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function() {
    // Event listener for surplus identification form submission
    document.getElementById("surplusIdentificationForm").addEventListener("submit", function(event) {
        event.preventDefault();
        let surplusItem = document.getElementById("surplusItem").value;
        let surplusReason = document.getElementById("surplusReason").value;

        // Basic validation example: check if all fields are filled
        if (surplusItem && surplusReason) {
            // Create a new object to store surplus identification data
            let surplusIdentification = {
                surplusItem: surplusItem,
                surplusReason: surplusReason
            };

            // Send the surplus identification data to the server (simulated here)
            console.log("Surplus identification data:", surplusIdentification);
            // Here you would send the data to your backend server for processing

            // Clear form fields after submission
            document.getElementById("surplusItem").value = "";
            document.getElementById("surplusReason").value = "";
        } else {
            alert("Please fill in all fields");
        }
    });

    // Event listener for listing surplus items form submission
    document.getElementById("listSurplusItemsForm").addEventListener("submit", function(event) {
        event.preventDefault();
        let listedItem = document.getElementById("listedItem").value;
        let listingType = document.getElementById("listingType").value;

        // Basic validation example: check if all fields are filled
        if (listedItem && listingType) {
            // Create a new object to store listed surplus item data
            let listedSurplusItem = {
                listedItem: listedItem,
                listingType: listingType
            };

            // Send the listed surplus item data to the server (simulated here)
            console.log("Listed surplus item data:", listedSurplusItem);
            // Here you would send the data to your backend server for processing

            // Clear form fields after submission
            document.getElementById("listedItem").value = "";
            document.getElementById("listingType").value = "";
        } else {
            alert("Please fill in all fields");
        }
    });
});
