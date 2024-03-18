/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.getElementById("registrationForm").addEventListener("submit", function(event) {
    event.preventDefault();
    // Perform form validation here
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let userType = document.getElementById("userType").value;

    // Basic validation example: check if all fields are filled
    if (name && email && password && userType) {
        // Send registration data to server
        console.log("Registration data:", name, email, password, userType);
        // Here you would send the registration data to your backend server
    } else {
        alert("Please fill in all fields");
    }
});

