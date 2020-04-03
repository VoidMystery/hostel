<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Index Page</title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<div class="no_main2">
    <div class="margin_main2">
        <div class="content_body2">
            <div class="con2">
            </div>
        </div>
    </div>
</div>
<%--<h1><c:out value="${requestScope.message}"/></h1>--%>
<%--<c:if test='${empty requestScope.role}'>--%>
<%--    <form action="controller" method="post">--%>
<%--        <input type="hidden" name="command" value="login"/>--%>
<%--        <input type="text" name="login" value=""/>--%>
<%--        <input type="password" name="password" value=""/>--%>
<%--        <input type="submit" value="Log in"/><br/>--%>
<%--    </form>--%>
<%--    <form action="controller" method="get">--%>
<%--        <input type="hidden" name="command" value="sign_up"/>--%>
<%--        <input type="submit" value="Sign up"/><br/>--%>
<%--    </form>--%>
<%--</c:if>--%>
<%--<c:if test='${not empty requestScope.role}'>--%>
<%--    <form action="controller" method="get">--%>
<%--        <input type="hidden" name="command" value="profile"/>--%>
<%--        <input type="submit" value="Мой профиль"/><br/>--%>
<%--    </form>--%>
<%--</c:if>--%>
</body>
</html>