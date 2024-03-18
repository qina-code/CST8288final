/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById("inventoryForm").addEventListener("submit", function(event) {
    event.preventDefault();
    // Perform form validation here
    let itemName = document.getElementById("itemName").value;
    let quantity = document.getElementById("quantity").value;
    let expirationDate = document.getElementById("expirationDate").value;

    // Basic validation example: check if all fields are filled
    if (itemName && quantity && expirationDate) {
        // Create a new inventory item object
        let newItem = {
            itemName: itemName,
            quantity: quantity,
            expirationDate: expirationDate
        };

        // Send the new item data to the server (simulated here)
        console.log("New inventory item:", newItem);
        // Here you would send the new item data to your backend server for storage

        // Clear form fields after submission
        document.getElementById("itemName").value = "";
        document.getElementById("quantity").value = "";
        document.getElementById("expirationDate").value = "";
    } else {
        alert("Please fill in all fields");
    }
});


