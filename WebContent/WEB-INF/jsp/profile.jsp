<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.error.password_is_wrong" var="password_is_wrong"/>
<fmt:message bundle="${loc}" key="local.error.passwords_do_not_match" var="passwords_do_not_match"/>
<fmt:message bundle="${loc}" key="local.password.current_pass" var="current_pass"/>
<fmt:message bundle="${loc}" key="local.password.new_pass" var="new_pass"/>
<fmt:message bundle="${loc}" key="local.password.new_pass2" var="new_pass2"/>
<fmt:message bundle="${loc}" key="local.profile.information" var="information"/>
<fmt:message bundle="${loc}" key="local.profile.name" var="name"/>
<fmt:message bundle="${loc}" key="local.profile.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.profile.patronymic" var="patronymic"/>
<fmt:message bundle="${loc}" key="local.profile.sale" var="sale"/>
<fmt:message bundle="${loc}" key="local.profile.sale_info" var="sale_info"/>
<div class="no_main2">
    <div class="margin_main2">
        <div class="content_body2">
            <div class="con2">
                <br>
                ${information} ${requestScope.user.login}<br>
                ${name}: ${requestScope.user.name}<br>
                ${surname}: ${requestScope.user.surname}<br>
                ${patronymic}: ${requestScope.user.patronymic}<br>
                <br>
                <br>
                ${sale_info}
                <br>
                ${sale}: ${requestScope.user.discount}%
                <br>
                <br>
                <form id = "pass_change_form"action="controller" method="post">
                    <input type="hidden" name="command" value="password_change">
                    <label for="current_pass">${current_pass}</label><br>
                    <input type="password" id = "current_pass" name="current_pass"><br>
                    <label for="new_pass">${new_pass}</label><br>
                    <input type="password" id = "new_pass" name="new_pass"><br>
                    <label for="new_pass2">${new_pass2}</label><br>
                    <input type="password" id = "new_pass2" name="new_pass2"><br>
                    <button type="submit" class="btn btn-primary">OK</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    let curPass = document.getElementById('current_pass');
    let password = document.getElementById('new_pass');
    let password2 = document.getElementById('new_pass2');
    let sign_up_form = document.getElementById('pass_change_form');

    let password_pattern = /^[a-zA-Z1-9_]{3,20}$/;

    curPass.addEventListener('input',()=>{
        resetErrors();
    },false);
    password.addEventListener('input',()=>{
        resetErrors();
    },false);
    password2.addEventListener('input',()=>{
        resetErrors();
    },false);

    function resetErrors(){
        curPass.setCustomValidity("");
        password.setCustomValidity("");
        password2.setCustomValidity("");
    }

    sign_up_form.addEventListener('submit', (event) => {
        let valid = true;
        if (!(password.value.length === 0 || password_pattern.test(password.value))) {
            password.setCustomValidity("${password_is_wrong}");
            valid = false;
        }
        if (!(password.value === password2.value)) {
            password2.setCustomValidity("${passwords_do_not_match}");
            valid = false;
        }

        event.returnValue = valid === true;

    }, true);
</script>
</html>
