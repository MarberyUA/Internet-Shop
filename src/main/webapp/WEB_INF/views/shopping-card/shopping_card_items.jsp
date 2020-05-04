<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card</title>
</head>
<body>
<c:forEach var ="item" items="${card.getProductsInShopping()}">
    <p>Name: ${item.name}; price: ${item.price}
        <a href="${pageContext.request.contextPath}/shopping_card/product/remove?itemId=${item.id}">
            <button>remove form card</button>
        </a>
    </p>
</c:forEach>
<a href="${pageContext.request.contextPath}/order/create?userId=${card.getUser().getId()}">
    <button>Make order</button>
</a>
</body>
</html>
