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
<fmt:message bundle="${loc}" key="local.apartment.apartment_number" var="order_number"/>
<fmt:message bundle="${loc}" key="local.apartment.apartment_number" var="apartment_number"/>
<fmt:message bundle="${loc}" key="local.message.not_paid" var="not_paid"/>
<fmt:message bundle="${loc}" key="local.message.paid" var="paid"/>
<fmt:message bundle="${loc}" key="local.table.action" var="action"/>
<fmt:message bundle="${loc}" key="local.order.beginning_date" var="beginning_date"/>
<fmt:message bundle="${loc}" key="local.order.end_date" var="end_date"/>
<fmt:message bundle="${loc}" key="local.order.status" var="status"/>
<fmt:message bundle="${loc}" key="local.order.price" var="price"/>
<fmt:message bundle="${loc}" key="local.room_service.room_service_name" var="room_service_name"/>
<fmt:message bundle="${loc}" key="local.room_service.cost" var="cost"/>
<fmt:message bundle="${loc}" key="local.action.confirm_payment" var="confirm_payment"/>
<fmt:message bundle="${loc}" key="local.action.add_service" var="add_service"/>
<jsp:useBean id="date" class="java.util.Date"/>
<fmt:formatDate value="${date}" var="now" pattern="yyyy-MM-dd"/>
<div id="main" class="main">
    <br>
    <table class="table-secondary table-bordered">
        <thead>
        <tr>
            <td>${order_number}</td>
            <td>${apartment_number}</td>
            <td>${beginning_date}</td>
            <td>${end_date}</td>
            <td>${status}</td>
            <td>${price}</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${requestScope.order.id}</td>
            <td>${requestScope.order.apartmentNumber}</td>
            <td>${requestScope.order.beginDate}</td>
            <td>${requestScope.order.endDate}</td>
            <td>
                <c:if test="${requestScope.order.status ne 'paid'}">
                    ${not_paid}
                </c:if>
                <c:if test="${requestScope.order.status eq 'paid'}">
                    ${paid}
                </c:if>
            </td>
            <td>${requestScope.order.price}</td>
        </tr>
        </tbody>
    </table>
    <br>
    <table class="table-secondary table-bordered">
        <thead>
        <tr>
            <td>${room_service_name}</td>
            <td>${price}</td>
            <td>${status}</td>
            <c:if test="${requestScope.role eq 'ROLE_ADMIN' and requestScope.order.status eq 'paid'}">
                <td>${action}</td>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room_service" items="${requestScope.room_services}">
            <tr>
                <td>${room_service.roomServiceName}</td>
                <c:if test="${room_service.cost == 0}">
                    <td>${room_service.price}</td>
                    <td>${not_paid}</td>
                </c:if>
                <c:if test="${room_service.cost > 0}">
                    <td>${room_service.cost}</td>
                    <td>${paid}</td>
                </c:if>
                <c:if test="${requestScope.role eq 'ROLE_ADMIN' and requestScope.order.status eq 'paid' and room_service.cost == 0}">
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="confirm_service_payment">
                            <input type="hidden" name="sho_id" value="${room_service.shoId}">
                            <button type="submit">${confirm_payment}</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <c:if test="${requestScope.role eq 'ROLE_USER' and requestScope.order.endDate > now and requestScope.order.status ne 'paid'}">
        <div>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="add_room_service_to_order">
                <input type="hidden" name="order_id" value="${requestScope.order.id}">
                <select name="room_service_id">
                    <c:forEach var="roomService" items="${requestScope.all_room_services}">
                        <option value="${roomService.id}">${roomService.roomServiceName}</option>
                    </c:forEach>
                </select>
                <button type="submit">${add_service}</button>
            </form>
        </div>
    </c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
<script type="text/javascript">
    window.onload = () => setMinHeightOn100Vh('main');
</script>
</html>
