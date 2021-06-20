<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${pageContext.session.getAttribute('language')}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${pageContext.session.getAttribute('language')}">
<head>
    	<meta charset="UTF-8">
    	<title><fmt:message key="pageHeader"/></title>
    	<link href="styles.css" rel="stylesheet">
    	<link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">
</head>

<nav class="navbar navbar-light bg-light d-flex justify-content-between">
	<h1 class="navbar-brand"><fmt:message key="loginVariantName"/></h1>
	<p><fmt:message key="loggedInAs"/> ${pageContext.session.getAttribute("user").login} &emsp;
		<a href="${pageContext.request.contextPath}/main?action=logout"><fmt:message key="logout"/> </a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/main?action=index&language=ru">ru</a> &emsp;
		<a href="${pageContext.request.contextPath}/main?action=index&language=en">en</a>
	</p>
</nav>

<body>
<h3><fmt:message key="loginProblem"/></h3>
<ul>
	<li> <fmt:message key="loginBill"/>
	<li> <fmt:message key="loginDrinks"/>
	<li> <fmt:message key="loginBuy"/>
	<li> <fmt:message key="loginAdmin"/>
</ul>
<h3><fmt:message key="actionPick"/></h3>
<div class="list-group">
	<a href="${pageContext.request.contextPath}/main?action=drinks" class="list-group-item list-group-item-action"><fmt:message key="getAllDrinks"/></a>
	<a href="${pageContext.request.contextPath}/main?action=ingredients" class="list-group-item list-group-item-action"><fmt:message key="getAllIngredients"/></a>
	<a href="${pageContext.request.contextPath}/main?action=bill" class="list-group-item list-group-item-action"><fmt:message key="getUserBill"/></a>
	<c:if test="${pageContext.session.getAttribute('user').admin}">
	<a href="${pageContext.request.contextPath}/main?action=fill" class="list-group-item list-group-item-action"><fmt:message key="fillTheMachine"/></a>
	</c:if>
	<a href="${pageContext.request.contextPath}/main?action=buy_drink" class="list-group-item list-group-item-action"><fmt:message key="buyDrink"/></a>
</div>
<div style="position: relative">
	<p style="position: fixed; bottom: 0; width: 100%;">
		<fmt:message key="lastVisitingTime"/> ${cookie['lastEnterTime'].value} <br>
		<fmt:message key="totalTimesVisited"/> ${cookie['usageCount'].value}
	</p>
</div>
</body>
</html>