<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
<div class="top">
    <div>
        <nav>
            <ul class="nav">
                <li>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="local_change"/>
                        <input type="hidden" name="local" value="en"/>
                        <input type="submit" value="${en_button}"/><br/>
                    </form>
                </li>
                <li>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="local_change"/>
                        <input type="hidden" name="local" value="ru"/>
                        <input type="submit" value="${ru_button}"/><br/>
                    </form>
                </li>
                <c:if test="${empty requestScope.role}">
                    <li>
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="log_in"/>
                            <input class="btn btn-light" type="submit" value="Войти"/>
                        </form>
                    </li>
                </c:if>
                <c:if test="${empty requestScope.role}">
                    <li>
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="sign_up"/>
                            <input class="btn btn-light" type="submit" value="Регистрация"/>
                        </form>
                    </li>
                </c:if>
                <c:if test="${not empty requestScope.role}">
                    <li class="dropdown">
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="profile"/>
                            <input type="submit" value="Мой профиль"/>
                        </form>
                        <ul>
                            <li>
                            <li>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="log_out"/>
                                    <input type="submit" value="Выйти"/>
                                </form>
                            </li>
                            </li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
