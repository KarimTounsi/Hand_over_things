<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title><spring:message code="register.register"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <jsp:include page="../fragments/header.jsp"/>
</header>

<section class="login-page">
    <h2><spring:message code="register.CAA"/></h2>
    <form:form action="/register" method="post" modelAttribute="userDTO">
        <div class="form-group">
            <input type="email" name="email" placeholder="<spring:message code="register.email"/>"/>
           <br> <form:errors path="email" cssClass="error"/>
        </div>

        <div class="form-group">
            <input type="password" name="password" placeholder="<spring:message code="register.password"/>"/>
            <br> <form:errors path="password" cssClass="error"/>
        </div>

        <div class="form-group">
            <input type="password" name="passwordCheck" placeholder="<spring:message code="register.RP"/>"/>
            <br> <form:errors path="passwordCheck" cssClass="error"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border"><spring:message code="register.login"/></a>
            <button class="btn" type="submit"><spring:message code="register.CA"/></button>
        </div>
        <sec:csrfInput/>
    </form:form>
</section>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
