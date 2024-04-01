<%-- 
    Document   : identify_surplus
    Created on : 2024年3月31日, 19:51:36
    Author     : Tianying Le
--%>

<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management - Identify Surplus</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <h1>Surplus Food Identification</h1>
        <form id="surplusForm" class="form" action="IdentifySurplusServlet" method="POST">
            <div class="form-group">
                <label for="surplusItem">Select Surplus Item:</label>
                <select id="surplusItem" name="surplusItem" required>
                    <!-- Options for surplus items will be dynamically populated -->
                    <option value="choice" selected="selected">Please select</option>
                    <c:forEach items="${items}" var="item">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="surplusReason">Reason for Surplus:</label>
                <textarea id="surplusReason" name="surplusReason" rows="4"></textarea>
            </div>
            <button type="submit">Identify Surplus</button>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>
