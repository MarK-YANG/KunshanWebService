<%--
  Created by IntelliJ IDEA.
  User: mark
  Date: 12/8/16
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Wifi Manager Home</title>
</head>
<body>
<div align="center">
    <h1>Wifi List</h1>
    <h3><a href="/newWifi">New Wifi</a></h3>
    <table border="1">
        <th>No</th>
        <%--<th>Name</th>--%>
        <%--<th>Email</th>--%>
        <%--<th>Address</th>--%>
        <%--<th>Telephone</th>--%>
        <th>Mac</th>

        <c:forEach var="Wifi" items="${listWifi}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <%--<td>${Wifi.record_id}</td>--%>
                <%--<td>${Wifi.deviceid}</td>--%>
                <td>${Wifi}</td>
                <%--<td>${Wifi.signal}</td>--%>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
