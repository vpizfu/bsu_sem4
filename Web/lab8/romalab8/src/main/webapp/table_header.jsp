<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-hover table-border">
    <thead class="thead-light">
    <tr>
        <th scope="col">Drink No</th>
        <th scope="col">Drink Name</th>
        <th scope="col">Cost Per Portion</th>
        <c:if test="${pageContext.session.getAttribute('user').admin}">
            <th scope="col">Edit</th>
        </c:if>
    </tr>
    </thead>
    <tbody>