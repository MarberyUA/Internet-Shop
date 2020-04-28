<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach var ="product" items="${products}">
        <p>Name: ${product.name}; price: ${product.price} <a
                href="${pageContext.request.contextPath}/products/toCard?id=${product.id}">
            <button>add to card</button>
        </a></p>
    </c:forEach>
</body>
</html>
