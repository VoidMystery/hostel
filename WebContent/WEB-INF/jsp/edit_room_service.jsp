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
<fmt:message bundle="${loc}" key="local.room_service.room_service_name" var="room_service_name"/>
<fmt:message bundle="${loc}" key="local.room_service.room_service_price" var="price"/>
<fmt:message bundle="${loc}" key="local.action.edit" var="edit"/>
<div id="main" class="main">
    <section id="cover">
        <div id="cover-caption">
            <div class="container">
                <div class="col-xl-5 mx-auto text-center p-4">
                    <form class="form-login login-form" action="controller" method="post">
                        <input type="hidden" name="command" value="edit_room_service"/>
                        <div class="form-group">
                            <div class="form-row">
                                <label for="room_service_name">${room_service_name}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="room_service_name" type="text"
                                       placeholder="${room_service_name}" value="${requestScope.room_service.roomServiceName}"
                                       name="room_service_name" readonly/>
                            </div>
                            <div class="form-row">
                                <label for="price">${price}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="price" type="number" min="0" max="999999" step="0.01" placeholder="${price}"
                                       value="${requestScope.room_service.price}" name="price"/>
                            </div>
                        </div>
                        <button class="btn btn-light" type="submit">${edit}</button>
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