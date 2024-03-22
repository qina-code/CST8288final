document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("registrationForm");

    form.addEventListener("submit", function(event) {
        event.preventDefault(); 

        // get values from the form
        const name = document.getElementById("name").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const userType = document.getElementById("userType").value;
        const subscribed = document.getElementById("subscribed").checked;

        // validate
        if (name.trim() === "" || email.trim() === "" || password.trim() === "" || userType.trim() === "") {
            alert("Please fill in the required blanks");
            return;
        }

        // construct data
        const formData = new FormData();
        formData.append("name", name);
        formData.append("email", email);
        formData.append("password", password);
        formData.append("userType", userType);
        formData.append("subscribed", subscribed);



        // send the data to correct url
        // send login request to backend
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/FWRP/user/RegistrationFormServlet", true); 

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert("Successfully registered!");
                } else {
                    alert("Registration failed!");
                }
            }
        };

        xhr.send(formData);
    });
});
