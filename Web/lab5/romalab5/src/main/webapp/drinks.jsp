<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Web Programming, lab 3</title>
    <link href="styles.css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="${pageContext.request.contextPath}/main?action=index">Coffee Machine</a>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <h3 class="nav-link">Drinks</h3>
      </li>
    </ul>
  </div>
</nav>

<c:if test="${!empty drinks}">
<h3>Available drinks</h3>
<div class="page-table">
    <table class="table table-hover table-border">
        <thead class="thead-light">
        <tr>
            <th scope="col">Drink No</th>
            <th scope="col">Drink Name</th>
            <th scope="col">Cost Per Portion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="drink" items="${drinks}">
            <tr>
                <td>${drink.id}</td>
                <td>${drink.name}</td>
                <td>${drink.cost}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</c:if>

</body>
</html>
