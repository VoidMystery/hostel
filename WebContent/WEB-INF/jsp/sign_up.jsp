<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Index Page</title>
</head>
<body>
${requestScope.message}
<form action="controller" method="post" >
    <input type="hidden" name="command" value="sign_up" />
    <input type="text"  placeholder="login" name="login" value=""/>
    <input type="password" placeholder="password" name="password" value=""/>
    <input type="password" placeholder="repeat password" name="password2" value=""/>
    <input type="text" placeholder="surname" name="surname" value=""/>
    <input type="text" placeholder="name" name="name" value=""/>
    <input type="text" placeholder="patronymic" name="patronymic" value=""/>
    <input type="submit" value="Sign up" /><br/>
</form>
</body>
</html>