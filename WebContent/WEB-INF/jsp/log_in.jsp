<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Index page</title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.error.wrong_login_or_password" var="wrong_login_or_password"/>
<div class="no_main2">
    <div class="margin_main2">
        <div class="content_body2">
            <div class="con2">
                <c:if test="${requestScope.WRONG_LOGIN_OR_PASSWORD eq true}">
                    <c:out value="${wrong_login_or_password}"/>
                </c:if>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="log_in"/>
                    <div class="form-group">
                        <div class="mb-2">
                            <div class="form-inline">
                                <label for="login">Логин:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="login" type="text" name="login" value=""/>
                            </div>
                        </div>
                        <br>
                        <div class="mb-2">
                            <div class="form-inline">
                                <label for="password">Пароль:&nbsp;</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Password" required>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-primary" type="submit">Войти</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
