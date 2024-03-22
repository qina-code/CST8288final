<%-- 
    Document   : login.jsp
    Created on : Mar 22, 2024, 3:46:33 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Waste Reduction Platform - Log in</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <h2>Log in</h2>
         <%
        // Check if there is an error parameter in the URL
        String error = request.getParameter("error");
        if (error != null && error.equals("invalid")) {
    %>
        <script>
            alert("Invalid username or password!");
        </script>
    <%
        }
    %>
        <form id="loginForm" ACTION="LoginFormServlet" method="POST">

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <button type="submit">Log in</button>
        </form>
    </div>
            <jsp:include page="../footer.jsp" />
    <!--<script src="login_script.js"></script>-->
</body>
</html>
