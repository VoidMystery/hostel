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
</body>
</html>