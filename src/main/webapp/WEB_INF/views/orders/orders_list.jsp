<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${message}</h1>
    <c:forEach var="order" items="${orders}">
        <div>
            <p>Products in order: ${order.getProductsInOrder().size()}
                <a href="${pageContext.request.contextPath}/order/info?id=${order.getId()}">
                    <button>Get info about order</button>
                </a>
                <a href="${pageContext.request.contextPath}/order/delete?id=${order.getId()}">
                    <button>Cancel order</button>
                </a>
            </p>
        </div>
    </c:forEach>
</body>
</html>
