<%-- 
    Document   : header.jsp
    Created on : Mar 22, 2024, 3:16:28 PM
    Author     : User
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FWRP</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<div class="navbar">
    <h2>FWRP</h2>
    <nav >
        
        <%-- Check if user is logged in --%>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
        %>
                <%-- If user is not logged in, show registration and login links --%>
                <a href="http://localhost:8080/FWRP/user/registration.jsp">Registration</a>
                <a href="http://localhost:8080/FWRP/user/login.jsp">Login</a>
        <%
            } else {
        %>
                <%-- If user is logged in, show logout link --%>
                <a href="http://localhost:8080/FWRP/user/LogoutServlet">Logout</a>
        <%
            }
        %>
    </nav>
</div>

</body>
</html>

