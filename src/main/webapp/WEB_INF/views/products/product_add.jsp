<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/products/add">
        <span>Enter a product name: </span>
        <br>
        <input type="text" name="prd-name">
        <br>
        <span>Enter a product price: </span>
        <br>
        <input type="text" name="prd-price">
        <br>
        <button type="submit">Create product</button>
    </form>

</body>
</html>
