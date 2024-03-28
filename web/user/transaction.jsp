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
    <ul>
        <% 
            List<Transaction> transactions = (List<Transaction>) session.getAttribute("transactions");
            if (transactions != null) {
                for (Transaction transaction : transactions) { 
        %>
            <li><%= transaction %></li>
        <% 
                } 
            } else { 
        %>
            <li>No transactions available</li>
        <% } %>
    </ul>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>