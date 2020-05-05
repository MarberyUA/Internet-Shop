<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../links.jsp"></jsp:include>
    <title>Login</title>
</head>
<body>
    <jsp:include page="../nav_bar.jsp"></jsp:include>
    <h3 style="color: red">${message}</h3>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <div>
            <p>Enter you login: </p>
            <input name="login" type="text">
        </div>
        <div>
            <p>Enter your password: </p>
            <input name="password" type="password">
        </div>
        <button type="submit">Login</button>
    </form>
    <jsp:include page="../scripts.jsp"></jsp:include>
</body>
</html>
