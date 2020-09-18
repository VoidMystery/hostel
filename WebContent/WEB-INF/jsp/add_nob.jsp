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
<fmt:message bundle="${loc}" key="local.apartment.number_of_beds" var="number_of_beds"/>
<fmt:message bundle="${loc}" key="local.apartment.apartment_type" var="apartment_type"/>
<fmt:message bundle="${loc}" key="local.apartment.number_of_rooms" var="number_of_rooms"/>
<fmt:message bundle="${loc}" key="local.action.edit" var="edit"/>
<div id="main" class="main">
    <section id="cover">
        <div id="cover-caption">
            <div class="container">
                <div class="col-xl-5 mx-auto text-center p-4">
                    <form class="form-login login-form" action="controller" method="post">
                        <input type="hidden" name="command" value="add_nob"/>
                        <div class="form-group">
                            <div class="form-row">
                                <label for="number_of_bed">${number_of_beds}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="number_of_bed" type="number" min="1" step="1"
                                       name="nob"/>
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