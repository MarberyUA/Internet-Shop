<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../links.jsp"></jsp:include>
    <title>All users</title>
</head>
<body>
    <jsp:include page="../nav_bar.jsp"></jsp:include>
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
    <jsp:include page="../scripts.jsp"></jsp:include>
</body>
</html>
