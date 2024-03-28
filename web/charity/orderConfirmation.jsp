<%-- 
    Document   : orderConfirmation
    Created on : Mar 27, 2024, 11:37:33 PM
    Author     : Qina&Kaiwen
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Confirmation</title>
        <link rel="stylesheet" href="../styles.css">
    </head>
    <body>
        <div class="container">
            <h2>Order Confirmation</h2>
            <p>Your order has been placed successfully!</p>
            <p>Order Confirmation Number: <%= session.getAttribute("confirmationNumber") %></p>
            <table>
                <tr>
                    <th>Item Name</th>
                    <th>Quantity</th>
                </tr>
                <% Map<String, Integer> purchasedItems = (Map<String, Integer>) session.getAttribute("purchasedItems");
                    System.out.println("Purchased Items (JSP): " + purchasedItems);
                    if (purchasedItems != null) {
                        for (Map.Entry<String, Integer> entry : purchasedItems.entrySet()) {
                %>
                
                <tr>
                    <td><%= entry.getKey()%></td>
                    <td><%= entry.getValue()%></td>
                </tr>
                <%      }
                    }
                %>
            </table>
            <form action="http://localhost:8080/FWRP/charity/ItemListServlet" method="get">
                <button type="submit">Buy More</button>
            </form>
        </div>
    </body>
</html>