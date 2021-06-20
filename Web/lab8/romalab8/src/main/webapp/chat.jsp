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
    <link href="styles.css">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/main?action=index"><fmt:message key="projectName"/></a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <h3 class="nav-link"><fmt:message key="chat"/></h3>
            </li>
        </ul>
    </div>
    <ul class="nav navbar-nav flex-row justify-content-md-center justify-content-start flex-nowrap">
        <fmt:message key="loggedInAs"/> ${pageContext.session.getAttribute("user").login}
        <a href="${pageContext.request.contextPath}/main?action=logout"><fmt:message key="logout"/></a>
    </ul>
    <p>
        <a href="${pageContext.request.contextPath}/main?action=chat&language=ru">ru</a> &emsp;
        <a href="${pageContext.request.contextPath}/main?action=chat&language=en">en</a>
    </p>
</nav>

<script>
    var socket;

    function init() {
        document.getElementById('chat-text-field').value = '';

        socket = new WebSocket("ws://localhost:8080/romalab5-1.0-SNAPSHOT/chat/${pageContext.session.getAttribute('user').login}");
        socket.onmessage = function (event) {
            console.log(event.data);
            let obj = JSON.parse(event.data);
            document.getElementById('chat-text-field').value +=
                '[' + obj.sendDateTime + '] ' + obj.sender + ': ' + obj.contents + '\r\n';
        };

        document.getElementById('chat-message-handle').removeEventListener('click', init);
        document.getElementById('chat-message-handle').addEventListener('click', close);
        document.getElementById('chat-message-handle').value = '<fmt:message key="leave"/>';
    }

    function sendMessage() {
        let userLogin = "${pageContext.session.getAttribute('user').login}";
        let message = {
            contents: document.getElementById('chat-message-input').value,
            sender: userLogin,
            sendDateTime: new Date(),
        };
        document.getElementById('chat-message-input').value = '';
        socket.send(JSON.stringify(message));
    }

    function close() {
        socket.close();
        document.getElementById('chat-message-handle').removeEventListener('click', close);
        document.getElementById('chat-message-handle').addEventListener('click', init);
        document.getElementById('chat-message-handle').value = '<fmt:message key="enter"/>';
    }

    window.addEventListener('pageshow', init);
    window.addEventListener('pagehide', close);
</script>

<textarea id="chat-text-field" rows="25" cols="80"></textarea>
<form id="chat-form">
    <input type="text" id="chat-message-input" placeholder=""/>
    <input type="button" id="chat-message-send" onclick="sendMessage()" value="<fmt:message key='send'/>"/>

    <input type="button" id="chat-message-handle" onclick="close()" value="<fmt:message key='leave'/>"/>

</form>

</body>
</html>
