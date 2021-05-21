<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Type</th>
        <th>Country</th>
        <th>Price</th>
        <th>Start date</th>
        <th>End date</th>
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><c:out value="${item.getId()}"/></td>
            <td><c:out value="${item.getType()}"/></td>
            <td><c:out value="${item.getLocation()}"/></td>
            <td><c:out value="${item.getPrice()}"/></td>
            <td><c:out value="${item.getStartDate()}"/></td>
            <td><c:out value="${item.getEndDate()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>