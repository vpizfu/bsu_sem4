<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    	<meta charset="UTF-8">
    	<title>Web Programming, lab 3</title>
    	<link href="styles.css" rel="stylesheet">
    	<link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">
</head>

<nav class="navbar navbar-light bg-light d-flex justify-content-between">
	<h1 class="navbar-brand">Variant 18, Roman Kharchenko</h1>
	<p>Logged in as ${pageContext.session.getAttribute("user").login} &emsp;
		<a href="${pageContext.request.contextPath}/main?action=logout">Logout</a>
	</p>
</nav>

<body>
<h3>Problem</h3>
<ul>
	<li> A <i>user</i> may have a <i>bill</i>.
	<li> A <i>coffee machine</i> contains a set of <i>drinks</i> with a predefined number of portions and additional <i>ingredients</i>.
	<li> The user can buy one or more drinks.
	<li> The <i>admininstrator</i> of the coffee machine fills it.
</ul>
<h3>Pick an action:</h3>
<div class="list-group">
	<a href="${pageContext.request.contextPath}/main?action=drinks" class="list-group-item list-group-item-action">Get all drinks</a>
	<a href="${pageContext.request.contextPath}/main?action=ingredients" class="list-group-item list-group-item-action">Get all ingredients</a>
	<a href="${pageContext.request.contextPath}/main?action=bill" class="list-group-item list-group-item-action">Get the info on the bill of a user</a>
	<c:if test="${pageContext.session.getAttribute('user').admin}">
	<a href="${pageContext.request.contextPath}/main?action=fill" class="list-group-item list-group-item-action">Fill the coffee machine</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/main?action=buy_drink" class="list-group-item list-group-item-action">Buy a drink</a>
</div>
<div style="position: relative">
	<p style="position: fixed; bottom: 0; width: 100%;">
		<i>Last visiting time: </i> ${cookie['lastEnterTime'].value} <br>
		<i>Total times visited: </i> ${cookie['usageCount'].value}
	</p>
</div>
</body>
</html>