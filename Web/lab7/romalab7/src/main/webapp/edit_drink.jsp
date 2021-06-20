<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html contenttype="text/html;charset=UTF-8" lang="${pageContext.session.getAttribute('language')}">
<head>
    <title>Web Programming, lab 3</title>
    <link href="styles.css" rel="stylesheet">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-between">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/main?action=index">Coffee Machine</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <h3 class="nav-link">Edit a drink</h3>
            </li>
        </ul>
    </div>

    <p>Logged in as ${pageContext.session.getAttribute("user").login} &emsp;
        <a href="${pageContext.request.contextPath}/main?action=logout">Log out</a>
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/main?action=edit_drink&language=ru">ru</a> &emsp;
        <a href="${pageContext.request.contextPath}/main?action=edit_drink&language=en">en</a>
    </p>
</nav>

<h3>Edit Drink #${drink.id}</h3>
<div class="page-form">
    <form id="bill-form" action="${pageContext.request.contextPath}/main" method="post">
        <input type="hidden" name="action" id="EditCommand" value="edit_drink">
        <input type="hidden" name="DrinkNo" id="DrinkNo-output" value="${drink.id}">
        <label for="DrinkName-output">Drink name</label>
        <input type="text" class="form-control" name="DrinkName"
               id="DrinkName-output" value="${drink.name}" required/>
        <label for="DrinkCost-output">Drink cost</label>
        <input type="number" class="form-control" name="DrinkCost"
               id="DrinkCost-output" value="${drink.cost}" min="0" step="0.01">
        <input type="submit" class="btn btn-primary" value="Edit">
    </form>
</div>

</body>
</html>