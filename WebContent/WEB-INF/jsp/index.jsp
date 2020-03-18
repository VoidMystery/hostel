<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Index Page</title>
</head>
<body>
<h1><c:out value="${requestScope.message}"/></h1>
<form action="controller" method="post">
    <input type="hidden" name="command" value="login" />
    <input type="text" name="login" value=""/>
    <input type="password" name="password" value=""/>
    <input type="submit" value="Log in" /><br/>
</form>
<form action="controller" method="get">
    <input type="hidden" name="command" value="sign_up" />
    <input type="submit" value="Sign up" /><br/>
</form>
${requestScope.role}
<c:if test='${!requestScope.role.equals("guest") && !requestScope.role.equals("")}'>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="profile" />
        <input type="submit" value="Профиль" /><br/>
    </form>
</c:if>
</body>
</html>