<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items you have added!</title>
</head>
<body>
<c:forEach var ="item" items="${cardItems}">
    <p>Name: ${item.name}; price: ${item.price}</p>
</c:forEach>
</body>
</html>
