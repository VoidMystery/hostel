<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<div class="no_main2">
    <div class="margin_main2">
        <div class="content_body2">
            <div class="con2">
                Информация о ${requestScope.user.login}<br>
                Имя: ${requestScope.user.name}<br>
                Фамилия: ${requestScope.user.surname}<br>
                Отчество: ${requestScope.user.patronymic}<br>
                <br>
                <br>
                Ваша текущая скидка ${requestScope.user.discount}%
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="log_out">
                    <input type="submit" value="Выйти">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
