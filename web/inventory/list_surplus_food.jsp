<%-- 
    Document   : list_surplus_food
    Created on : 2024年3月31日, 19:52:16
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
    <title>Inventory Management - List Surplus Food</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <h1>List Surplus Food Items</h1>
        <form id="listSurplusItemsForm" class="form" action="ListSurplusFoodServlet" method="POST">
            <div class="form-group">
                <label for="listedItem">Surplus Item:</label>
                <select id="listedItem" name="listedItem" required>
                    <!-- Options for listed surplus items will be dynamically populated -->
                    <option value="choice" selected="selected">Please select</option>
                    <c:forEach items="${items}" var="item">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="listingType">Listing Type:</label>
                <select id="listingType" name="listingType" required>
                    <option value="donation">Donation</option>
                    <option value="sale">Sale (Discounted Price)</option>
                </select>
            </div>
            <button type="submit">List Item</button>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
   
</body>
</html>


