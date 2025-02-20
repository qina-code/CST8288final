<%-- 
    Document   : registration
    Created on : Mar 22, 2024, 3:47:49 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Waste Reduction Platform - Registration</title>
    <link rel="stylesheet" href="../styles.css">
</head>

<body>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <h2>Registration</h2>
                 <%
        // Check if there is an error parameter in the URL
        String error = request.getParameter("error");
        if (error != null && error.equals("fail")) {
    %>
        <script>
            alert("Registration failed! Please try again!");
        </script>
    <%
        }
    %>
    <%
     if (error != null && error.equals("email_exists")) {
    %>
        <script>
            alert("Email already being used!");
        </script>
    <%
        }
    %>
    
        <!--<form id="registrationForm" method="POST">-->
        <form id="registrationForm" action="RegistrationFormServlet"  method="POST">

            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="userType">User Type:</label>
                <select id="userType" name="userType" required>
                    <option value="">Select User Type</option>
                    <option value="retailer">Retailer</option>
                    <option value="consumer">Consumer</option>
                    <option value="charitable_organization">Charitable Organization</option>
                </select>
            </div>
           <div class="form-group">
                <label for="city">City:</label>
                <input type="input" id="city" name="city" required>
            </div>
            <div class="form-group">
                <label for="postal">Postal Code(ANA NAN): </label>
                <input type="input" id="postal" name="postal" required>
            </div>

            
            <div class="form-group">
            <label for="category">Preference:</label>
            <input type="checkbox" id="category" name="category" value = "1">Meat
            <input type="checkbox" id="category" name="category" value = "2">Seafood 
            <input type="checkbox" id="category" name="category" value = "3">Vegetables
            <input type="checkbox" id="category" name="category" value = "4">Fruits 
            <input type="checkbox" id="category" name="category" value = "5">Grains
            <input type="checkbox" id="category" name="category" value = "6">Seasonings 
            <input type="checkbox" id="category" name="category" value = "7">Cans
            <input type="checkbox" id="category" name="category" value = "8">Snacks 
            <input type="checkbox" id="category" name="category" value = "9">Others 
            </div>
            
            <div class="form-group">
            <label for="subscribed">Subscribe?</label>
            <input type="checkbox" id="subscribed" name="subscribed">Check to get notification through email!    
            </div>
            <button type="submit">Register</button>
        </form>
    </div>
        <jsp:include page="../footer.jsp" />
    <!--<script src="registration_script.js"></script>-->
</body>
</html>
