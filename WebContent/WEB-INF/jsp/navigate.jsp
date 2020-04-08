<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
    <script src="../../js/jquery-3.3.1.slim.min.js"></script>
    <script src="../../js/popper.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/viewport.js"></script>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
<fmt:message bundle="${loc}" key="local.navigate.log_in_button" var="log_in_button"/>
<fmt:message bundle="${loc}" key="local.navigate.sign_up_button" var="sign_up_button"/>
<fmt:message bundle="${loc}" key="local.navigate.my_account" var="my_account"/>
<fmt:message bundle="${loc}" key="local.navigate.my_account.menu.profile" var="profile"/>
<fmt:message bundle="${loc}" key="local.navigate.my_account.menu.log_out_button" var="log_out_button"/>
<div style="background-color: #257496;">
    <nav class="navbar navbar-dark navbar-expand-md justify-content-xl-end navigation-clean"
         style="height: 35px">
        <div class="container">
            <div class="container d-flex float-right justify-content-xl-end">
                <form class="form-inline text-right d-xl-flex justify-content-xl-end" method="post" action="controller">
                    <input type="hidden" class="form-control" name="command" value="local_change"/>
                    <input type="hidden" class="form-control" name="local" value="en"/>
                    <button class="btn btn-light btn-sm" type="submit"
                            style="margin-left: 5px;">${en_button}</button>
                </form>
                <form class="form-inline text-right d-xl-flex justify-content-xl-end" method="post" action="controller">
                    <input type="hidden" class="form-control" name="command" value="local_change"/>
                    <input type="hidden" class="form-control" name="local" value="ru"/>
                    <button class="btn btn-light btn-sm" type="submit"
                            style="margin-left: 5px;">${ru_button}</button>
                </form>
            </div>
        </div>
    </nav>
    <nav class="navbar navbar-light navbar-expand-md navigation-clean-button" style="height: 50px;">
        <div class="container">
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="nav navbar-nav mr-auto">
                    <li role="presentation" class="nav-item"></li>
                    <li role="presentation" class="nav-item"></li>
                </ul>
                <div class="container d-flex justify-content-xl-end">
                    <c:if test="${empty requestScope.role}">
                        <form class="form-inline" method="get" action="controller">
                            <input type="hidden" class="form-control" name="command" value="log_in"/>
                            <button class="btn btn-light action-button" type="submit"
                                    style="background-color: rgb(37,123,123);width: 150px;border-radius: 10px;font-weight: bold;font-style: normal;">
                                    ${log_in_button}
                            </button>
                        </form>
                        <form class="form-inline" action="controller" method="get">
                            <input type="hidden" class="form-control" name="command" value="sign_up"/>
                            <button class="btn btn-light action-button" type="submit"
                                    style="background-color: rgb(37,123,123);margin-left: 9px;width: 150px;border-radius: 10px;font-style: normal;font-weight: bold;">
                                    ${sign_up_button}
                            </button>
                        </form>
                    </c:if>

                    <c:if test="${not empty requestScope.role}">
                        <div class="dropdown">
                            <button type="button" class="btn btn-link dropdown-toggle" id="dropdownMenuLink"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                    style="text-decoration: none; color: white; font-weight: bold">
                                    ${my_account}
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="profile"/>
                                    <a class="dropdown-item" onclick="this.parentNode.submit()">
                                            ${profile}
                                    </a>
                                </form>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="log_out"/>
                                    <a class="dropdown-item" onclick="this.parentNode.submit()">
                                            ${log_out_button}
                                    </a>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</div>
</body>
</html>
