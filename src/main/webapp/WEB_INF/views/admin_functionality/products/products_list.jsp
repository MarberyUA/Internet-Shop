<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../../links.jsp"></jsp:include>
    <title>Title</title>
</head>
<body>
    <jsp:include page="../../nav_bar.jsp"></jsp:include>
    <div>
        <a href="${pageContext.request.contextPath}/products/add">
            <button>Add product</button>
        </a>
    </div>
    <br>
    <c:forEach var ="product" items="${products}">
        <p>Name: ${product.name}; price: ${product.price}
            <a href="${pageContext.request.contextPath}/products/delete?id=${product.id}">
                <button>Delete product</button>
            </a>
        </p>
    </c:forEach>
    <jsp:include page="../../scripts.jsp"></jsp:include>
</body>
</html>
