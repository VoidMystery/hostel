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
<fmt:message bundle="${loc}" key="local.order.beginning_date" var="beginning_date"/>
<fmt:message bundle="${loc}" key="local.order.end_date" var="end_date"/>
<fmt:message bundle="${loc}" key="local.order.status" var="status"/>
<fmt:message bundle="${loc}" key="local.order.price" var="price"/>

<div id="main" class="main">
    <br>
    <table class="table-secondary table-bordered">
        <thead>
        <tr>
            <td>${apartment_number}</td>
            <td>${beginning_date}</td>
            <td>${end_date}</td>
            <td>${status}</td>
            <td>${price}</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="o" items="${requestScope.orders}">
            <tr>
                <td>${o.apartmentNumber}</td>
                <td>${o.beginDate}</td>
                <td>${o.endDate}</td>
                <td>${o.status}</td>
                <td>${o.price}</td>
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
