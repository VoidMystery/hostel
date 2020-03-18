<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="user" class="by.jwd.lemesheuski.hostel.bean.User"/>
<jsp:setProperty property="login" name="user"/>
<%--<jsp:setProperty property="role" name="user"/>--%>
<c:if test="${requestScope.role == 'guest'}">
    <h1>Hello , ${requestScope.role} !!! </h1>
</c:if>
<c:if test="${requestScope.role != 'guest'}">
    <h1>Hello , ${user.login} !!! </h1>
</c:if>
<a href="/hostel">На главную</a>
</h1>
</body>
</html>