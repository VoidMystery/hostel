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
<fmt:message bundle="${loc}" key="local.apartment_params.apartment_type" var="apratment_type"/>
<fmt:message bundle="${loc}" key="local.navigate.authorised.room_menu.add_room_button" var="add_room_button"/>

<div id="main" class="main">
    <section id="cover">
        <div id="cover-caption">
            <div class="container">
                <div class="col-xl-5 mx-auto text-center p-4">
                    <form id="sign_up_form" class="form-login login-form" action="controller" method="post">
                        <input type="hidden" name="command" value="add_type"/>
                        <div class="form-group">
                            <div class="form-row">
                                <label for="type">${apratment_type}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="type" type="text" placeholder="${login}" name="type"
                                       value=""/>
                            </div>
                        </div>
                        <button class="btn btn-light" type="submit">${add_room_button}</button>
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