<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>View Route</title></head>
<body>
    <h3>Search Route</h3>
    <form action="${pageContext.request.contextPath}/MainServlet" method="post">
        <input type="hidden" name="operation" value="viewRecord">
        Route Code: <input type="text" name="routeCode"><br/>
        Route Name: <input type="text" name="routeName"><br/>
        <input type="submit" value="View Route">
    </form>
</body>
</html>
