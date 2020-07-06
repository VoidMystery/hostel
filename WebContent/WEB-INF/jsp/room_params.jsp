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
<fmt:message bundle="${loc}" key="local.apartment_params.apartment_type" var="aprtment_type"/>
<fmt:message bundle="${loc}" key="local.apartment_params.number_of_beds" var="number_of_beds"/>
<fmt:message bundle="${loc}" key="local.apartment_params.number_of_rooms" var="number_of_rooms"/>
<fmt:message bundle="${loc}" key="local.table.action" var="action"/>
<fmt:message bundle="${loc}" key="local.action.add" var="add"/>
<fmt:message bundle="${loc}" key="local.action.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.action.delete" var="delete"/>
<div id="main" class="main">
    <div id="accordion">
        <div class="card">
            <div class="card-header" id="headingOne">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true"
                            aria-controls="collapseOne">
                        ${aprtment_type}
                    </button>
                </h5>
            </div>

            <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                <div class="card-body">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="add_type">
                        <input type="hidden" name="id" value="type.id">
                        <button class="btn btn-success" type="submit">${add}</button>
                    </form>
                    <br>
                    <table style="text-align: -webkit-center" class="table table-bordered">
                        <thead>
                        <tr>
                            <td>${aprtment_type}</td>
                            <td>${action}</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="type" items="${requestScope.apartment_types}">
                            <tr>
                                <td>${type.type}</td>
                                <td>
                                    <div class="form-row">
                                        <form action="controller" method="get">
                                            <input type="hidden" name="command" value="edit_type">
                                            <input type="hidden" name="id" value="${type.id}">
                                            <button class="btn btn-primary" type="submit">${edit}</button>
                                        </form>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="delete_type">
                                            <input type="hidden" name="id" value="${type.id}">
                                            <button class="btn btn-danger" type="submit">${delete}</button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingTwo">
                <h5 class="mb-0">
                    <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo"
                            aria-expanded="false" aria-controls="collapseTwo">
                        ${number_of_beds}
                    </button>
                </h5>
            </div>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                <div class="card-body">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="add_number_of_beds">
                        <input type="hidden" name="id" value="type.id">
                        <button class="btn btn-success" type="submit">${add}</button>
                    </form>
                    <br>
                    <table style="text-align: -webkit-center" class="table table-bordered">
                        <thead>
                        <tr>
                            <td>${number_of_beds}</td>
                            <td>${action}</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="nob" items="${requestScope.number_of_beds}">
                            <tr>
                                <td>${nob.beds}</td>
                                <td>
                                    <div class="form-row">
                                        <form action="controller" method="get">
                                            <input type="hidden" name="command" value="edit_number_of_beds">
                                            <input type="hidden" name="id" value="${nob.id}">
                                            <button class="btn btn-primary" type="submit">${edit}</button>
                                        </form>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="delete_nob">
                                            <input type="hidden" name="id" value="${nob.id}">
                                            <button class="btn btn-danger" type="submit">${delete}</button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingThree">
                <h5 class="mb-0">
                    <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree"
                            aria-expanded="false" aria-controls="collapseThree">
                        ${number_of_rooms}
                    </button>
                </h5>
            </div>
            <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                <div class="card-body">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="add_number_of_room">
                        <input type="hidden" name="id" value="type.id">
                        <button class="btn btn-success" type="submit">${add}</button>
                    </form>
                    <br>
                    <table style="text-align: -webkit-center" class="table table-bordered">
                        <thead>
                        <tr>
                            <td>${number_of_rooms}</td>
                            <td>${action}</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="nor" items="${requestScope.number_of_rooms}">
                            <tr>
                                <td>${nor.rooms}</td>
                                <td>
                                    <div class="form-row">
                                        <form action="controller" method="get">
                                            <input type="hidden" name="command" value="edit_number_of_room">
                                            <input type="hidden" name="id" value="${nor.id}">
                                            <button class="btn btn-primary" type="submit">${edit}</button>
                                        </form>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="delete_nob">
                                            <input type="hidden" name="id" value="${nor.id}">
                                            <button class="btn btn-danger" type="submit">${delete}</button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
<script type="text/javascript">
    window.onload = () => setMinHeightOn100Vh('main');
</script>
</html>