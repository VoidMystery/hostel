<%@ page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
                "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Index Page</title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.order.beginning_date" var="beginning_date"/>
<fmt:message bundle="${loc}" key="local.order.end_date" var="end_date"/>
<fmt:message bundle="${loc}" key="local.action.select" var="select"/>
<div id="main" class="main">
    <section id="cover">
        <div id="cover-caption">
            <div class="container">
                <div class="col-xl-5 mx-auto text-center p-4">
                    <form class="form-login login-form" action="controller" method="get">
                        <input type="hidden" name="command" value="select_apartments_with_date"/>
                        <div class="form-group">
                            <div class="form-row">
                                <label for="beginning_date">${beginning_date}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="beginning_date" type="date" name="beginning_date"/>
                            </div>
                            <div class="form-row">
                                <label for="end_date">${end_date}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="end_date" type="date" name="end_date"/>
                            </div>
                        <button class="btn btn-light" type="submit">${select}</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
<jsp:include page="footer.jsp"/>
</body>
<script type="text/javascript">
    window.onload = () => setMinHeightOn100Vh('cover');

</script>
</html>