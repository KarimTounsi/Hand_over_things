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
    <title><spring:message code="resetPassword.resetPasssword"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <jsp:include page="../fragments/header.jsp"/>
</header>

<section class="login-page">
    <h2><spring:message code="resetPassword.WNP"/></h2>
    <form:form action="/resetPassword" method="post" modelAttribute="passwordDTO">
        <div class="form-group">
            <input type="password" name="password" placeholder="<spring:message code="resetPassword.password"/>"/>
            <br> <form:errors path="password" cssClass="error"/>
        </div>
        <input name="id" type="text"  id="id" hidden  value="${id}"/>
        <input name="token" type="text" id="token" hidden value="${token}"/>
        <div class="form-group">
            <input type="password" name="passwordCheck" placeholder="<spring:message code="resetPassword.RP"/>"/>
            <br> <form:errors path="passwordCheck" cssClass="error"/>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit" id="addPasswordButton"><spring:message code="resetPassword.E"/></button>
        </div>
    </form:form>
</section>

<jsp:include page="../fragments/footer.jsp"/>



</body>
</html>
