<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wipro.route.bean.RouteBean" %>
<!DOCTYPE html>
<html>
<head><title>Display All Routes</title></head>
<body>
    <%
        String msg = (String) request.getAttribute("message");
        List<RouteBean> list = (List<RouteBean>) request.getAttribute("routeList");
        
        if (msg != null) {
            out.print(msg);
        } else if (list != null && !list.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Route ID</th><th>Route Code</th><th>Route Name</th>
                <th>Start Location</th><th>End Location</th><th>Distance (KM)</th><th>Remarks</th>
            </tr>
            <% for(RouteBean bean : list) { %>
            <tr>
                <td><%= bean.getRouteId() %></td>
                <td><%= bean.getRouteCode() %></td>
                <td><%= bean.getRouteName() %></td>
                <td><%= bean.getStartLoc() %></td>
                <td><%= bean.getEndLoc() %></td>
                <td><%= bean.getDistanceKm() %></td>
                <td><%= bean.getRemarks() %></td>
            </tr>
            <% } %>
        </table>
    <% } %>
    <br/><a href="menu.html">Back to Menu</a>
</body>
</html>