/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();
    // Perform form validation here
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    // Basic validation example: check if all fields are filled
    if ( email && password ) {
        // Send registration data to server
        console.log("Login data:",  email, password);
        //send login request to backend
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/FWRP//user/LoginFormServlet", true); 
        xhr.setRequestHeader("text/html;charset=UTF-8");
        
    } else {
        alert("Please fill in all fields");
    }
});


