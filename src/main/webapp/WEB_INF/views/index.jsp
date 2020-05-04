<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello boyz! Current time is ${time}</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/inject_data">Inject data</a>
        <a href="${pageContext.request.contextPath}/users/all">All users</a>
        <a href="${pageContext.request.contextPath}/registration">Register user</a>
        <a href="${pageContext.request.contextPath}/products/all">All products</a>
        <a href="${pageContext.request.contextPath}/products/add">Add product</a>
        <a href="${pageContext.request.contextPath}/products">Admin product panel</a>
        <a href="${pageContext.request.contextPath}/shopping_card">Products in card</a>
        <a href="${pageContext.request.contextPath}/order/all">Orders</a>
    </nav>
</body>
</html>
