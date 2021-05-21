<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 05.04.2021
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<main class="" id="main-collapse">
    <form class="reveal-content" action="${pageContext.request.contextPath}/superServ">
        <label for="clientId" style="display:block">Client id</label><input style="display:block; width:200px"
                                                                            class="form-control" type="number"
                                                                            name="clientId" id="clientId">
        <label style="display:block" for="tourId">
            Tour id
        </label>
        <input style="display:block; width:200px" class="form-control" type="number" name="tourId" id="tourId">

        <label>
            <input type="hidden" name="commandId"
                   value="bookTour">
        </label>
        <button type="submit" class="btn btn-info btn-lg">Book</button>
    </form>
</main>
</body>
</html>
