<%-- 
    Document   : user_dashboard
    Created on : Mar 21, 2024, 11:56:02 AM
    Author     : User
--%>

<%@page import="model.User"%>
<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Check if user information exists in the session
    User user = (User) session.getAttribute("user");
    if (user == null) {
        // If not authenticated, redirect to landing page
        response.sendRedirect("http://localhost:8080/FWRP");
    }
%>
<% List<Item> items = (List<Item>)session.getAttribute("LoginItemNameList");
    StringBuilder itemNames = new StringBuilder();
    if (items != null && !items.isEmpty()) {
        for (Item itemname : items) {
            itemNames.append(itemname.getName());
            itemNames.append(", ");
        }
        // 移除最后一个逗号和空格
        itemNames.setLength(itemNames.length() - 2);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Dashboard</title>
        <link rel="stylesheet" href="../styles.css">
    </head>
    <body>
        <jsp:include page="../header.jsp" />
        <div class="container">
            <%
                if (user != null) {
                    out.println("<h2> Welcome, " + user.getName() + "!</h2>");
                    out.println("<p>Your user type is: " + user.getType() + ".</p>");
//                    out.println("<p>the retailer user recently add these items：" + itemNames.toString()  +"</p>\n\n");

                    // Convert user type to lowercase for case-insensitive comparison
                    String userType = user.getType().toLowerCase();
                    boolean subscribed = user.getSubscribed();
                    // Show different links based on user type
                    if (userType.equals("retailer")) {
            %>
            <!-- Show links for admin -->

            <a class="dashboard_btn" href="http://localhost:8080/FWRP/inventory/ItemNameServlet">Inventory Management</a>
            <%
            } else if (userType.equals("consumer")) {
            %>
            <!-- Show links for customer -->


            <a class="dashboard_btn" href="http://localhost:8080/FWRP/customer/ItemListCustomerServlet">Purchase Food</a>

            <%
            } else if (userType.equals("charitable_organization")) {
            %>

            <a class="dashboard_btn" href="http://localhost:8080/FWRP/charity/ItemListServlet">Claim Food</a>


            <%       }

                if (subscribed) {
            %>
            <a class="dashboard_btn" href="http://localhost:8080/FWRP/inventory/ShowItemNameServlet">Notification</a>

            <%
                    }

                }
            %>

            <a class="dashboard_btn" href="http://localhost:8080/FWRP/user/TransactionServlet">View Transaction</a>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>