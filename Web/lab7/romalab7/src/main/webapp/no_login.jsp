<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

<nav class="navbar navbar-light bg-light">
	<h1 class="navbar-brand"><fmt:message key="loginVariantName"/></h1>
	<p>
		<a href="${pageContext.request.contextPath}/main?action=no_login&language=ru">ru</a> &emsp;
		<a href="${pageContext.request.contextPath}/main?action=no_login&language=en">en</a>
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
<h3><fmt:message key="noLogin"/></h3>
</body>
</html>