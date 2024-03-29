<%-- 
    Document   : orderConfirmation
    Created on : Mar 27, 2024, 11:37:33 PM
    Author     : Qina&Kaiwen
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
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
        <title>Order Confirmation</title>
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
            <h1>Order Confirmation</h1>
            <div class="order-details">
                <h3>Thank you <%= user.getName()%>!</h3>
                <p>Your order has been placed successfully!</p>  
                <p>Order Confirmation Number: <%= session.getAttribute("confirmationNumber")%></p>
                <p>Order Date and Time: <%= session.getAttribute("charityordertime")%></p>
            </div>
            <div class="table-container">
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
            </div>
            <div class="submit-area">
                <form action="http://localhost:8080/FWRP/charity/ItemListServlet" method="get">
                    <button type="submit">Buy More</button>
                </form>

                <form action="http://localhost:8080/FWRP/user/TransactionServlet" method="get">
                    <button type="submit">View Transaction</button>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>