<%-- 
    Document   : transaction.jsp
    Created on : Mar 24, 2024, 12:27:22 PM
    Author     : User
--%>
<%-- Check if user information exists in the session --%>
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
<%@page import="java.util.List"%>
<%@page import="model.Transaction"%>

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
            <h1>Transactions</h1>
            <h3>Hi <%= user.getName()%>, Your role is: <%= user.getType()%> </h3>
            <h3>Here's your transaction </h3>


            <table border="1">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Item ID</th>

                        <th>Quantity</th>
                        <th>Transaction Time</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
                        if (transactions != null) {
                            for (Transaction transaction : transactions) {
                    %>
                    <tr>
                        <td><%= transaction.getOrderId()%></td>
                        <td><%= transaction.getItemName()%></td>

                        <td><%= transaction.getQuantity()%></td>
                        <td><%= transaction.getTransactionTime()%></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4">No transactions available</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
        <div class="submit-area">
            <button type="button" onclick="history.back()">Go Back</button>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>