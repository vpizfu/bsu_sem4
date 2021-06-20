<%@ page contentType="text/html;charset=UTF-8" %>
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

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/main?action=no_login"><fmt:message key="projectName"/></a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <h3 class="nav-link"><fmt:message key="signUp"/> </h3>
            </li>
        </ul>
    </div>
    <p>
        <a href="${pageContext.request.contextPath}/main?action=sign_up&language=ru">ru</a> &emsp;
        <a href="${pageContext.request.contextPath}/main?action=sign_up&language=en">en</a>
    </p>
</nav>

<h3><fmt:message key="signUp"/></h3>

<div class="page-form">
    <form id="signup-form">
        <input type="hidden" name="action" id="Action" value="sign_up">
        <label for="Login-input"><fmt:message key="login"/></label>
        <input type="text" class="form-control" placeholder="<fmt:message key='login'/>"
               name="Login" id="Login-input" required>
        <label for="Password-input"><fmt:message key="password"/></label>
        <input type="password" class="form-control"
               name="Password" id="Password-input" required>
        <label for="ConfirmPassword-input"><fmt:message key="confirmPassword"/></label>
        <input type="password" class="form-control"
               name="Confirmation" id="ConfirmPassword-input" required>
        <input type="submit" class="btn btn-primary" value="<fmt:message key='submit'/>">
    </form>
</div>

</body>
</html>