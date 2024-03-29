<%-- 
    Document   : orderReview
    Created on : Mar 27, 2024, 11:37:33 PM
    Author     : Qina&Kaiwen
--%>

<%@page import="java.util.List"%>
<%@page import="dataaccesslayer.ItemDAOImpl"%>
<%@page import="dataaccesslayer.ItemDAO"%>
<%@page import="model.Item"%>
<%@page import="java.util.HashMap"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@page import="java.math.BigDecimal"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        // If not authenticated, redirect to landing page
        response.sendRedirect("http://localhost:8080/FWRP");
    }
%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Review</title>
        <link rel="stylesheet" href="../styles.css">
        <style>
            .submit-area {
                display: flex;
                justify-content: space-between;
                padding: 50px;
                margin-bottom: 100px;
                margin-top: 30px;
            }
            .submit-area button {
                flex: 1;
                margin: 0 10px;
                padding: 10px 20px;
                font-size: 1em;
                height: 40px;
            }

            .submit-area button:first-child {
                margin-right: 20px;
            }

            /* Specifically target the last button if needed */
            .submit-area button:last-child {
                margin-left: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../header.jsp" />
        <div class="container">
            <h1>Order Information</h1>
            <form action="CustomerPurchaseServlet" method="post" onsubmit="return confirmPurchase();">

                <div class="table-container">
                    <table>
                        <tr>
                            <th>Item Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>

                        </tr>

                        <%
                            BigDecimal totalPrice = new BigDecimal("0.00");
                            int totalQuantity = 0;
                            ItemDAO itemDAO = new ItemDAOImpl();
                            Map<Integer, Integer> quantities = new HashMap<>();

                            List<Item> items = itemDAO.getItems();
                            for (Item item : items) {
                                String quantityParam = "quantity_" + item.getId();
                                String quantityString = request.getParameter(quantityParam);
                                if (quantityString != null && !quantityString.isEmpty()) {
                                    int quantity = Integer.parseInt(quantityString);
                                    // Add to the map if the quantity is greater than 0
                                    if (quantity > 0) {
                                        quantities.put(item.getId(), quantity);

                                        System.out.println("Item ID: " + item.getId() + ", Quantity: " + quantity);
                                    }
                                }
                            }

                            boolean anyQuantitySelected = quantities.values().stream().anyMatch(qty -> qty > 0);
                            if (!anyQuantitySelected) {
                                request.getSession().setAttribute("customererror", "You have not selected any items.");
                                response.sendRedirect("ItemListCustomerServlet");
                            }

                            for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
                                int itemId = entry.getKey();
                                int requestedQuantity = entry.getValue();
                                // Fetch the current stock from the database
                                Item item = itemDAO.getItemById(itemId);
                                if (item == null || item.getQuantity() < requestedQuantity) {
                                    System.out.println("Insufficient stock for Item ID: " + itemId);
                                    break;
                                } else {
                                    BigDecimal lineTotal = item.getPrice().multiply(new BigDecimal(entry.getValue()));
                                    totalQuantity += entry.getValue();
                                    totalPrice = totalPrice.add(lineTotal);
                        %>
                        <tr>
                            <td><%= item.getName()%></td>
                            <td>$<%= item.getPrice()%></td>
                            <td>
                                <%= entry.getValue()%>
                                <!-- Hidden field to send the quantity to the CustomerPurchaseServlet -->
                                <input type="hidden" name="quantity_<%= item.getId()%>" value="<%= entry.getValue()%>" />
                            </td>
                            <td><%= lineTotal%></td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        <!-- Totals row -->
                        <tr>
                            <td colspan="2" style="text-align:right;"><strong>Total</strong></td>
                            <td><strong><%= totalQuantity%></strong></td>
                            <td><strong>$<%= totalPrice%></strong></td>
                        </tr>
                        
                    </table>
                </div>
                <div class="submit-area">
                    <button type="button" onclick="history.back()">Go Back</button>
                    <button type="submit">Proceed and Pay</button>
                </div>
        </div>
    </form>
    <jsp:include page="../footer.jsp" />
</body>

<script>
    function confirmPurchase() {
        var totalCost = <%= totalPrice%>; 
        var confirmationMessage = "You are going to buy " + <%= totalQuantity%> + " item(s) with a total cost of $" + totalCost.toFixed(2) + ". Are you sure?";
        return confirm(confirmationMessage);
    }
</script>
</html>