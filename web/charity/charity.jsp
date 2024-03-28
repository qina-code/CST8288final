<%-- 
    Document   : charity
    Created on : Mar 25, 2024, 11:27:28 PM
    Author     : Qina&Kaiwen
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Food Waste Reduction Platform - Consumer</title>
        <link rel="stylesheet" href="../styles.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#purchaseForm').on('submit', function (e) {
                    e.preventDefault();

                    var formData = $(this).serialize(); // Serialize the form data for AJAX submission

                    $.ajax({
                        url: 'PurchaseServlet',
                        type: 'POST',
                        data: formData,
                        success: function (response) {

                            alert("Purchase successful!");

                        },
                        error: function () {
                            // Handle error
                            alert("Purchase failed!");
                        }
                    });
                });
            });
        </script>
    </head>

    <body>
        <jsp:include page="../header.jsp" />
        <div class="container">
            <h2>Available Items for Purchase</h2>
            <form action="PurchaseServlet" method="post" id="purchaseForm">
                <div class="item-header">
                    <span class="item-name-header">Name</span>
                    <span class="item-quantity-header">Quantity</span>
                </div>
                <div id="itemsList">
                    <%
                        List<Item> items = (List<Item>) request.getAttribute("items");
                        if (items != null && !items.isEmpty()) {
                            for (Item item : items) {
                    %>
                    <div class="item-row">
                        <span class="item-name"><%= item.getName() %></span>
                        <div class="quantity-area">
                            <input type="number" class="item-quantity" id="quantity_<%= item.getId() %>" name="quantity_<%= item.getId() %>" value="0" min="0" max="<%= item.getQuantity() %>">
                            <span class="availability">Available: <%= item.getQuantity() %></span>
                        </div>
                    </div>

                    <%
                            }
                        } else {
                    %>
                    <p>No items available for purchase.</p>
                    <%
                        }
                    %>
                </div>
                <div class="submit-area">
                    <button type="submit">Purchase Selected Items</button>
                </div>
            </form>
        </div>
        <jsp:include page="../footer.jsp" />

    </body>
</html>
