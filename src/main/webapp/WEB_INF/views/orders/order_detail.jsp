<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../links.jsp"></jsp:include>
    <title>Order detail</title>
</head>
<body>
    <jsp:include page="../nav_bar.jsp"></jsp:include>
    <c:forEach var="product" items="${products}">
        <p>Product name: ${product.name}; Product price: ${product.price}</p>
    </c:forEach>
    <p>Total price: ${cost}</p>
    <a href="${pageContext.request.contextPath}/orders_list">
        <button>Back to orders list</button>
    </a>
    <jsp:include page="../scripts.jsp"></jsp:include>
</body>
</html>
