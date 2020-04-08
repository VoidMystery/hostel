<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Index page</title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.error.wrong_login_or_password" var="wrong_login_or_password"/>
<fmt:message bundle="${loc}" key="local.user.login" var="login"/>
<fmt:message bundle="${loc}" key="local.user.password" var="password"/>
<fmt:message bundle="${loc}" key="local.navigate.log_in_button" var="log_in_button"/>
<div id="main" class="main">

    <c:if test="${requestScope.WRONG_LOGIN_OR_PASSWORD eq true}">
        <c:out value="${wrong_login_or_password}"/>
    </c:if>
    <section id="cover">
        <div id="cover-caption">
            <div class="container">
                <div class="col-xl-5 mx-auto text-center p-4">
                    <form class="form-login login-form" action="controller" method="post">
                        <input type="hidden" name="command" value="log_in"/>
                        <div class="form-group">
                            <div class="form-row">
                                <label for="login">${login}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="login" type="text" placeholder="${login}" name="login"
                                       value=""/>
                            </div>
                            <br>
                            <div class="form-row">
                                <label for="password">${password}:&nbsp;</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="${password}" required>
                            </div>
                        </div>
                        <button class="btn btn-light" type="submit">${log_in_button}</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="footer.jsp"/>
</body>
<script type="text/javascript">
    window.onload = () => setMinHeightOn100Vh('cover')
</script>
</html>
