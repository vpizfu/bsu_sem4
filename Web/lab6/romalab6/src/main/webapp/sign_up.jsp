<%@ page contentType="text/html;charset=UTF-8" %>
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

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/main?action=indexNoLogin">Coffee Machine</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <h3 class="nav-link">Sign up</h3>
            </li>
        </ul>
    </div>
</nav>

<h3>Sign up</h3>

<div class="page-form">
    <form id="signup-form">
        <input type="hidden" name="action" id="Action" value="sign_up">
        <label for="Login-input">Login</label>
        <input type="text" class="form-control" placeholder="Login"
               name="Login" id="Login-input" required>
        <label for="Password-input">Password</label>
        <input type="password" class="form-control"
               name="Password" id="Password-input" required>
        <label for="ConfirmPassword-input">Confirm password</label>
        <input type="password" class="form-control"
               name="Confirmation" id="ConfirmPassword-input" required>
        <input type="submit" class="btn btn-primary" value="Submit">
    </form>
</div>

<script src="scripts/script.js"></script>

</body>
</html>