<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wipro.route.bean.RouteBean" %>
<!DOCTYPE html>
<html>
<head><title>Display Route</title></head>
<body>
    <%
        String msg = (String) request.getAttribute("message");
        RouteBean bean = (RouteBean) request.getAttribute("routeBean");
        
        if (msg != null) {
            out.print("<h3>" + msg + "</h3>");
        } else if (bean != null) {
    %>
        <h3>Route Details</h3>
        <table border="1">
            <tr><td>Route ID</td><td><%= bean.getRouteId() %></td></tr>
            <tr><td>Route Code</td><td><%= bean.getRouteCode() %></td></tr>
            <tr><td>Route Name</td><td><%= bean.getRouteName() %></td></tr>
            <tr><td>Start Location</td><td><%= bean.getStartLoc() %></td></tr>
            <tr><td>End Location</td><td><%= bean.getEndLoc() %></td></tr>
            <tr><td>Distance (KM)</td><td><%= bean.getDistanceKm() %></td></tr>
            <tr><td>Remarks</td><td><%= bean.getRemarks() %></td></tr>
        </table>
    <% } %>
    <br/><a href="menu.html">Back to Menu</a>
</body>
</html>
