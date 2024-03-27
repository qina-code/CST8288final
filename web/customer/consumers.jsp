<%-- 
    Document   : consumers
    Created on : Mar 25, 2024, 11:27:28 PM
    Author     : Qina&Kaiwen
--%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Waste Reduction Platform - Consumer</title>
    <link rel="stylesheet" href="../styles.css">
</head>

<body>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <h2>Purchase Food</h2>
             
    
        <!--<form id="registrationForm" method="POST">-->
        <form id="registrationForm" action="RegistrationFormServlet"  method="POST">

            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
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
            <label for="subscribed">Subscribed:</label>
            <input type="checkbox" id="subscribed" name="subscribed">    
            </div>
            
            <button type="submit">Register</button>
        </form>
    </div>
        <jsp:include page="../footer.jsp" />
    <!--<script src="registration_script.js"></script>-->
</body>
</html>