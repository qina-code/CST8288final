<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<Item> items = (List<Item>)request.getAttribute("ItemNameList"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management</title>
    <link rel="stylesheet" href="../styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <form id="linventorySectionForm" action="RetailerTransactionServlet" method="POST">
    <div class="container">
        <h1>Inventory Management</h1>
        <section id="inventorySection">
            <h2>Add New Item</h2>
            <form id="inventoryForm" class="form">
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
                <button type="submit">Add Item</button>
            </form>
        </section>

        <section id="surplusSection">
            <h2>Surplus Food Identification</h2>

                <div class="form-group">
                    <label for="surplusItem">Select Surplus Item:</label>
                    <select id="surplusItem" name="surplusItem" required>
                        <!-- Options for surplus items will be dynamically populated -->
                        <option value="choice" selected="selected">Please select</option>
                         <c:forEach items="<%=items %>" var="items">
                             <option value='${items.id}'> ${items.name} </option>
                         </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="surplusReason">Reason for Surplus:</label>
                    <textarea id="surplusReason" name="surplusReason" rows="4"></textarea>
                </div>
                <button type="submit">Identify Surplus</button>

        </section>

        <section id="listSurplusSection">
            <h2>List Surplus Food Items</h2>
            <form id="listSurplusItemsForm" class="form">
                <div class="form-group">
                    <label for="listedItem">Surplus Item:</label>
                        <!-- Options for listed surplus items will be dynamically populated -->
                        <select id="listedItem" name="listedItem" required>
                            <!-- Options for surplus items will be dynamically populated -->
                            <option value="choice" selected="selected">Please select</option>
                            <c:forEach items="<%=items %>" var="items">
                                <option value='${items.id}'> ${items.name} </option>
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
        </section>
    </div>
    </form>
    <jsp:include page="../footer.jsp" />

</body>
</html>