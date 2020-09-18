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
<fmt:message bundle="${loc}" key="local.navigate.admin.set_room_parameters_button" var="set_room_parameters_button"/>
<fmt:message bundle="${loc}" key="local.room_service.room_service_name" var="room_service_name"/>
<fmt:message bundle="${loc}" key="local.room_service.room_service_price" var="price"/>
<fmt:message bundle="${loc}" key="local.table.action" var="action"/>
<fmt:message bundle="${loc}" key="local.action.add" var="add"/>
<fmt:message bundle="${loc}" key="local.action.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.action.deactivate" var="deactivate"/>
<fmt:message bundle="${loc}" key="local.action.add_service" var="add_service"/>
<div id="main" class="main">
    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
        <div class="card-body">
            <c:if test="${requestScope.role eq 'ROLE_ADMIN'}">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="add_room_service">
                    <input type="hidden" name="id" value="${type.id}">
                    <button class="btn btn-success" type="submit">${add}</button>
                </form>
                <br>
            </c:if>
            <table style="text-align: -webkit-center" class="table table-bordered">
                <thead>
                <tr>
                    <td>${room_service_name}</td>
                    <td>${price}</td>
                    <td>${action}</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="room_service" items="${requestScope.room_services}">
                    <tr>
                        <td>${room_service.roomServiceName}</td>
                        <td>${room_service.price}</td>
                        <td>
                            <c:if test="${requestScope.role eq 'ROLE_ADMIN'}">
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="edit_room_service">
                                    <input type="hidden" name="id" value="${room_service.id}">
                                    <button class="btn btn-success" type="submit">${edit}</button>
                                </form>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="deactivate_room_service">
                                    <input type="hidden" name="id" value="${room_service.id}">
                                    <button class="btn btn-success" type="submit">${deactivate}</button>
                                </form>
                                <br>
                            </c:if>
                            <c:if test="${requestScope.role eq 'ROLE_USER'}">
                                <form action="controller" method="get">
                                    <input type="hidden" name="command" value="add_to_order">
                                    <input type="hidden" name="order_id" value="${requestScope.order_id}">
                                    <input type="hidden" name="room_service_id" value="${room_service.id}">
                                    <button class="btn btn-success" type="submit">${add_service}</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
<script type="text/javascript">
    window.onload = () => setMinHeightOn100Vh('main');
</script>
</html>