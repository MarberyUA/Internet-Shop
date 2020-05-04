<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="links.jsp"></jsp:include>
    <title>Registration</title>
</head>
<body>
    <jsp:include page="nav_bar.jsp"></jsp:include>
    <h1 style="text-align: center;">Hello! Please provide your user details</h1>
    <h4 style="color: red; text-align: center">${message}</h4>
    <form method="post" action="${pageContext.request.contextPath}/registration" style="width: 100%;">
        <div style="margin: 0 auto; width: 50%">
            <p style="text-align: center; margin-bottom: 0px; padding-bottom: 0px;">
                Please provide your login:
            </p>
            <br>
            <input type="text" name="login" style="display: block; margin: 0 auto">
            <div>
                <p style="text-align: center; margin-bottom: 0px; padding-bottom: 0px;">
                    Please provide your password:
                </p>
                <br>
                <input type="password" name="password" style="display: block; margin: 0 auto">
                <br>
                <p style="text-align: center; margin-bottom: 0px; padding-bottom: 0px;">
                    Please repeat your password:
                </p>
                <br>
                <input type="password" name="password-repeat" style="display: block; margin: 0 auto">
                <br>
                <button type="submit" style="display: block; margin: 0 auto">Register</button>
            </div>
        </div>
    </form>
    <jsp:include page="scripts.jsp"></jsp:include>
</body>
</html>
