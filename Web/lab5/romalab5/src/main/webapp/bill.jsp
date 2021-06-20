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
        <h3 class="nav-link">User's bill</h3>
      </li>
    </ul>
  </div>
</nav>

<h3>Select a user</h3>
<div class="page-form">
    <form id="bill-by-user-form">
    </form>
</div>

<c:if test="${!empty billModel}">
<h3 id="table-header">The user's bill</h3>
<div class="page-table">
    <table class="table table-hover table-border">
        <thead class="thead-light">
        <tr>
            <th scope="col">Drink Name</th>
            <th scope="col">Portions drank</th>
            <th scope="col">Total cost</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${billModel.billElements}">
            <tr>
                <td>${item.drink.name}</td>
                <td>${item.billElement.drinkAmount}</td>
                <td>${item.drink.cost * item.billElement.drinkAmount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</c:if>

<script src="${pageContext.request.contextPath}/script.js"></script>

</body>
</html>
