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
<% List<Item> items = (List<Item>)session.getAttribute("ShowItemNameList");
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
                    out.println("<p>the retailer user recently add these items：" + itemNames.toString()  +"</p>\n\n");

            %>
    </body>
</html>