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
<fmt:message bundle="${loc}" key="local.apartment.apartment_number" var="apartment_number"/>
<fmt:message bundle="${loc}" key="local.apartment.floor" var="floor"/>
<fmt:message bundle="${loc}" key="local.apartment.number_of_beds" var="number_of_beds"/>
<fmt:message bundle="${loc}" key="local.apartment.apartment_type" var="apartment_type"/>
<fmt:message bundle="${loc}" key="local.apartment.balcony" var="balcony"/>
<fmt:message bundle="${loc}" key="local.apartment.number_of_rooms" var="number_of_rooms"/>
<fmt:message bundle="${loc}" key="local.apartment.price" var="price"/>
<fmt:message bundle="${loc}" key="local.action.add" var="add"/>
<fmt:message bundle="${loc}" key="local.action.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.action.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.action.reservation" var="reservation"/>

<div id="main" class="main">
    <br>
    <c:if test="${requestScope.role eq 'ROLE_ADMIN'}">
    <div style="text-align: center">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="add_apartment">
            <button class="btn btn-primary" type="submit">${add}</button>
        </form>
    </div>
    <br>
    </c:if>
    <table class="table-secondary table-bordered">
        <thead>
        <tr>
            <td>${apartment_number}</td>
            <td>${floor}</td>
            <td>${number_of_beds}</td>
            <td>${number_of_rooms}</td>
            <td>${apartment_type}</td>
            <td>${balcony}</td>
            <td>${price}</td>
            <c:if test="${not empty requestScope.role}">
                <td>${choose}</td>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="apartment" items="${requestScope.apartments}">
            <tr>
                <td>${apartment.roomNumber}</td>
                <td>${apartment.floor}</td>
                <td>${apartment.numberOfBeds}</td>
                <td>${apartment.numberOfRooms}</td>
                <td>${apartment.apartmentType}</td>
                <td>${apartment.balcony}</td>
                <td>${apartment.price}</td>
                <td>
                    <c:if test="${requestScope.role eq 'ROLE_ADMIN'}">
                    <div class="form-row">
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="edit_apartment">
                            <input type="hidden" name="id" value="${apartment.id}">
                            <button class="btn btn-primary" type="submit">${edit}</button>
                        </form>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="delete_apartment">
                            <input type="hidden" name="id" value="${apartment.id}">
                            <button class="btn btn-danger" type="submit">${delete}</button>
                        </form>
                    </div>
                    </c:if>
                    <c:if test="${requestScope.role eq 'ROLE_USER'}">
                        <div class="form-row">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="reservation">
                                <input type="hidden" name="id" value="${apartment.id}">
                                <input type="hidden" name="beginning_date" value="${requestScope.beginning_date}">
                                <input type="hidden" name="end_date" value="${requestScope.end_date}">
                                <button class="btn btn-primary" type="submit">${reservation}</button>
                            </form>
                        </div>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
<script type="text/javascript">
    window.onload = () => setMinHeightOn100Vh('main');
</script>
</html>