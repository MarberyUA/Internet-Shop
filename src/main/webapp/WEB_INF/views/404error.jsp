<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="links.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="nav_bar.jsp"></jsp:include>
    <div class="alert alert-danger" role="alert">
        Access denied. You have no permission to this page!
    </div>
    <jsp:include page="scripts.jsp"></jsp:include>
</body>
</html>
