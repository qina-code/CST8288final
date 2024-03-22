<%-- 
    Document   : user_dashboard
    Created on : Mar 21, 2024, 11:56:02 AM
    Author     : User
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Check if user information exists in the session
    User user = (User) session.getAttribute("user");
    if (user == null) {
        // If not authenticated, redirect to landing page
        response.sendRedirect("http://localhost:8080/FWRP");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Dashboard</title>
        <link rel="stylesheet" href="../styles.css">
    </head>
    <body>
        <jsp:include page="../header.jsp" />
        <div class="container">
        <%
            if(user != null) {
                out.println("<h2> Welcome, " + user.getName() + "!</h2>");
                out.println("Your user type is: " + user.getType());

       // Convert user type to lowercase for case-insensitive comparison
                String userType = user.getType().toLowerCase();

                // Show different links based on user type
                if (userType.equals("retailer")) {
        %>
                    <!-- Show links for admin -->
                    <div>
                        <a href="http://localhost:8080/FWRP/inventory/management.html">Inventory Management</a>
                    </div>
        <%
                } else if (userType.equals("consumer")) {
        %>
                    <!-- Show links for customer -->
                    <div>
                        <a href="url">Purchase Food</a>
                    </div>
        <%
                } else if(userType.equals("organization")){
%>
                      <div>
                        <a href="url">Claim Food</a>
                        </div>
             <%       }
            }
        %>
        <div>
            <a href="http://localhost:8080/FWRP/user/LogoutServlet">Log Out</a>
        </div>
        </div>
    </body>
</html>