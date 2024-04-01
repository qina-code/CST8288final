<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management - Add Item</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <h1>Add New Item</h1>
        <form id="inventoryForm" class="form" action="RetailerTransactionServlet" method="POST">
            <div class="form-group">
                <label for="itemName">Item Name:</label>
                <input type="text" id="itemName" name="itemName" required>
            </div>
            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" required>
            </div>
            <div class="form-group">
                <label for="expirationDate">Expiration Date:</label>
                <input type="date" id="expirationDate" name="expirationDate" required>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="category">Category:</label>
                <select id="category" name="category" required>
                    <option value="">Select Category</option>
                    <option value="meat">Meat</option>
                    <option value="seafood">Seafood</option>
                    <option value="vegetables">Vegetables</option>
                    <option value="fruits">Fruits</option>
                    <option value="grains">Grains</option>
                    <option value="seasonings">Seasonings</option>
                    <option value="cans">Cans</option>
                    <option value="snacks">Snacks</option>
                    <option value="others">Others</option>
                </select>
            </div>
            <button type="submit">Add Item</button>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>
