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
<fmt:message bundle="${loc}" key="local.user.login" var="login"/>
<fmt:message bundle="${loc}" key="local.user.password" var="password"/>
<fmt:message bundle="${loc}" key="local.user.password2" var="password2"/>
<fmt:message bundle="${loc}" key="local.user.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.user.name" var="name"/>
<fmt:message bundle="${loc}" key="local.user.patronymic" var="patronymic"/>
<fmt:message bundle="${loc}" key="local.navigate.sign_up_button" var="sign_up_button"/>

<fmt:message bundle="${loc}" key="local.error.login_is_present" var="login_is_present"/>
<fmt:message bundle="${loc}" key="local.error.wrong_data" var="wrong_data"/>
<fmt:message bundle="${loc}" key="local.error.login_is_wrong" var="login_is_wrong"/>
<fmt:message bundle="${loc}" key="local.error.password_is_wrong" var="password_is_wrong"/>
<fmt:message bundle="${loc}" key="local.error.passwords_do_not_match" var="passwords_do_not_match"/>
<fmt:message bundle="${loc}" key="local.error.surname_is_wrong" var="surname_is_wrong"/>
<fmt:message bundle="${loc}" key="local.error.name_is_wrong" var="name_is_wrong"/>
<fmt:message bundle="${loc}" key="local.error.patronymic_is_wrong" var="patronymic_is_wrong"/>
<div id="main" class="main">
    <section id="cover">
        <div id="cover-caption">
            <div class="container">
                <div class="col-xl-5 mx-auto text-center p-4">
                    <form id="sign_up_form" class="form-login login-form" action="controller" method="post">
                        <input type="hidden" name="command" value="sign_up"/>
                        <div class="form-group">
                            <c:if test="${requestScope.LOGIN_IS_PRESENT eq true}">
                                <div class="form-row" style="color: red;">
                                    <c:out value="${login_is_present}"/>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.WRONG_DATA eq true}">
                                <div class="form-row" style="color: red;">
                                    <c:out value="${wrong_data}"/>
                                </div>
                            </c:if>
                            <div class="form-row">
                                <label for="login">${login}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="login" type="text" placeholder="${login}" name="login"
                                       value="${requestScope.login}"/>
                            </div>
                            <br>
                            <div class="form-row">
                                <label for="password">${password}:&nbsp;</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="${password}" value="${requestScope.password}">
                            </div>
                            <div class="form-row">
                                <label for="password">${password2}:&nbsp;</label>
                                <input type="password" class="form-control" id="password2" name="password2"
                                       placeholder="${password}" value="${requestScope.password2}">
                            </div>
                            <div class="form-row">
                                <label for="surname">${surname}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="surname" type="text" placeholder="${surname}"
                                       name="surname"
                                       value="${requestScope.surname}"/>
                            </div>
                            <div class="form-row">
                                <label for="name">${name}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="name" type="text" placeholder="${name}" name="name"
                                       value="${requestScope.name}"/>
                            </div>
                            <div class="form-row">
                                <label for="patronymic">${patronymic}:&nbsp;&nbsp;&nbsp;</label>
                                <input class="form-control" id="patronymic" type="text" placeholder="${patronymic}"
                                       name="patronymic"
                                       value="${requestScope.patronymic}"/>
                            </div>
                        </div>
                        <button class="btn btn-light" type="submit">${sign_up_button}</button>
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

    let login = document.getElementById('login');
    let password = document.getElementById('password');
    let password2 = document.getElementById('password2');
    let surname = document.getElementById('surname');
    let name = document.getElementById('name');
    let patronymic = document.getElementById('patronymic');
    let sign_up_form = document.getElementById('sign_up_form');

    let login_pattern = /^[a-zA-Z1-9_]{3,20}$/;
    let password_pattern = /^[a-zA-Z1-9_]{3,20}$/;
    let SNP_pattern = /^(([A-Z][a-z]{2,15})|([А-Я][а-я]{2,15}))$/;

    login.addEventListener('input',()=>{
        resetErrors();
    },false);
    password.addEventListener('input',()=>{
        resetErrors();
    },false);
    password2.addEventListener('input',()=>{
        resetErrors();
    },false);
    surname.addEventListener('input',()=>{
        resetErrors();
    },false);
    name.addEventListener('input',()=>{
        resetErrors();
    },false);
    patronymic.addEventListener('input',()=>{
        resetErrors();
    },false);

    function resetErrors(){
        login.setCustomValidity("");
        password.setCustomValidity("");
        password2.setCustomValidity("");
        surname.setCustomValidity("");
        name.setCustomValidity("");
        patronymic.setCustomValidity("");
    }

    sign_up_form.addEventListener('submit', (event) => {
        let valid = true;

        if (!(login.value.length === 0 || login_pattern.test(login.value))) {
            login.setCustomValidity("${login_is_wrong}");
            valid = false;
        }
        if (!(password.value.length === 0 || password_pattern.test(password.value))) {
            password.setCustomValidity("${password_is_wrong}");
            valid = false;
        }
        if (!(password.value === password2.value)) {
            password2.setCustomValidity("${passwords_do_not_match}");
            valid = false;
        }
        if (!(surname.value.length === 0 || SNP_pattern.test(surname.value))) {
            surname.setCustomValidity("${surname_is_wrong}");
            valid = false;
        }
        if (!(name.value.length === 0 || SNP_pattern.test(name.value))) {
            name.setCustomValidity("${name_is_wrong}");
            valid = false;
        }
        if (!(patronymic.value.length === 0 || SNP_pattern.test(patronymic.value))) {
            patronymic.setCustomValidity("${patronymic_is_wrong}");
            valid = false;
        }

        event.returnValue = valid === true;

    }, true);
</script>
</html>