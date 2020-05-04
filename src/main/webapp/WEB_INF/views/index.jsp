<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <jsp:include page="links.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="nav_bar.jsp"></jsp:include>
    <div>
        <a href="${pageContext.request.contextPath}/inject_data">Inject data</a>
        <a href="${pageContext.request.contextPath}/users/all">All users</a>
        <a href="${pageContext.request.contextPath}/products/add">Add product</a>
        <a href="${pageContext.request.contextPath}/products">Admin product panel</a>
    </div>
    <jsp:include page="scripts.jsp"></jsp:include>
</body>
</html>
