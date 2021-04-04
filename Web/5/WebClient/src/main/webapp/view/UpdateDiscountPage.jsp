<%--
  Created by IntelliJ IDEA.
  User: Роман
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
        <label for="ordersId" style="display:block">Orders number</label><input min="0"
                                                                                style="display:block; width:200px"
                                                                                class="form-control" type="number"
                                                                                name="ordersId" id="ordersId">
        <label style="display:block" for="discountId">
            Discount %
        </label>
        <input style="display:block; width:500px" class="form-control" min="0" max="100" type="number" name="discountId"
               id="discountId">

        <label>
            <input type="hidden" name="commandId"
                   value="updateDiscount">
        </label>
        <button type="submit" class="btn btn-info btn-lg">Update</button>
    </form>
</main>
</body>
</html>
