<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Add Route</title></head>
<body>
    <form action="${pageContext.request.contextPath}/MainServlet" method="post">
        <input type="hidden" name="operation" value="newRecord">
        Route Code: <input type="text" name="routeCode"><br/>
        Route Name: <input type="text" name="routeName"><br/>
        Start Location: <input type="text" name="startLoc"><br/>
        End Location: <input type="text" name="endLoc"><br/>
        Distance (KM): <input type="text" name="distanceKm"><br/>
        Remarks: <input type="text" name="remarks"><br/>
        <input type="submit" value="Add Route">
    </form>
</body>
</html>