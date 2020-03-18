<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>You are logged in like ${requestScope.role}</h1>
${requestScope.path}
<form action="controller" method="post">
    <input type="hidden" name="command" value="log_out">
    <input type="submit" value="Выйти">
</form>
</body>
</html>
