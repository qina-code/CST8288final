<%-- 
    Document   : transaction.jsp
    Created on : Mar 24, 2024, 12:27:22 PM
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
        <title>Transaction History</title>
        <link rel="stylesheet" href="../styles.css">
    </head>
    <body>
        <jsp:include page="../header.jsp" />
        <div class="container">
        <%
                   // Convert user type to lowercase for case-insensitive comparison
            String userType = user.getType().toLowerCase();
              
            if(user != null) {
                out.println("<h2> View your" + userType + "transaction</h2>");

                // Show different links based on user type
                if (userType.equals("retailer")) {
        %>
                    <!-- Show retailer transaction -->
                    <p>retailer history here: Date, time, item name, quantity, price/per, total price</p>
                    <p>modify item db, add owner, and price/per  </p>

                    
        <%
                } else if (userType.equals("consumer")) {
        %>
                    <!-- Show consumer transaction -->
                    <p>consumer history here</p>

        <%
                } else if(userType.equals("charitable_organization")){
%>
<!-- Show organization transaction -->
                    <p>charitable organization history here</p>
             <%       }
            }
        %>
            
        </div>
            <jsp:include page="../footer.jsp" />
    </body>
</html>