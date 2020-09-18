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
<fmt:message bundle="${loc}" key="local.action.edit" var="edit"/>
<div id="main" class="main">
    <section id="cover">
        <div id="cover-caption">
            <div class="container">
                <div class="col-xl-5 mx-auto text-center p-4">
                    <form class="form-login login-form" action="controller" method="post">
                        <input type="hidden" name="command" value="edit_apartment"/>
                        <input type="hidden" name="id" value="${requestScope.apartment.id}"/>
                        <div class="form-group">
                            <div class="form-row">
                                <label for="apartment_number">${apartment_number}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="apartment_number" type="text"
                                       placeholder="${apartment_number}" value="${requestScope.apartment.roomNumber}"
                                       name="apartment_number" readonly/>
                            </div>
                            <div class="form-row">
                                <label for="floor">${floor}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="floor" type="text" placeholder="${floor}"
                                       value="${requestScope.apartment.floor}" name="floor"/>
                            </div>
                            <div class="form-row">
                                <label for="number_of_beds">${number_of_beds}:&nbsp;&nbsp;&nbsp;</label>
                                <select class="form-control" id="number_of_beds" name="number_of_beds">
                                    <c:forEach var="nob" items="${requestScope.number_of_beds}">
                                        <option value="${nob.id}"
                                        <c:if test="${nob.beds == requestScope.apartment.numberOfBeds}">
                                            selected="selected"
                                        </c:if>
                                        >${nob.beds}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-row">
                                <label for="apartment_type">${apartment_type}:&nbsp;&nbsp;&nbsp;</label>
                                <select class="form-control" id="apartment_type" name="apartment_type">
                                    <c:forEach var="at" items="${requestScope.apartment_types}">
                                        <option value="${at.id}"
                                                <c:if test="${at.type eq requestScope.apartment.apartmentType}">
                                                    selected
                                                </c:if>
                                        >${at.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-row">
                                <label for="number_of_rooms">${number_of_rooms}:&nbsp;&nbsp;&nbsp;</label>
                                <select class="form-control" id="number_of_rooms" name="number_of_rooms">
                                    <c:forEach var="nor" items="${requestScope.number_of_rooms}">
                                        <option value="${nor.id}"
                                                <c:if test="${nor.rooms == requestScope.apartment.numberOfRooms}">
                                                    selected="selected"
                                                </c:if>
                                        >${nor.rooms}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-row">
                                <label for="balcony">${balcony}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="balcony" type="checkbox" name="balcony"
                                        <c:if test="${requestScope.apartment.balcony == true}">
                                            checked
                                        </c:if>/>
                            </div>
                            <div class="form-row">
                                <label for="price">${price}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="price" type="number"
                                       value="${requestScope.apartment.price}" name="price"/>
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