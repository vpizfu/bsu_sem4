<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 05.04.2021
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="reveal-content" action="${pageContext.request.contextPath}/superServ">
    <label for="clientId">Client id</label><input type="number" name="clientId" id="clientId">
    <label>
        <input type="hidden" name="commandId" value="clientInfo">
    </label>
    <button type="submit">Search</button>
</form>
</body>
</html>
