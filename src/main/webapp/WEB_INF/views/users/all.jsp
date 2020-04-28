<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiaomi
  Date: 4/27/2020
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
    <h1>All users page</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <c:forEach var ="user" items="${allUsers}">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/user/delete?id=${user.id}">
                        <button type="button">Delete</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
