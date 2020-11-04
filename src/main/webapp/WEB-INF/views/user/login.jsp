<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title><spring:message code="login.login"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <jsp:include page="../fragments/header.jsp"/>
</header>

<section class="login-page">
    <h2><spring:message code="login.SI"/></h2>

    <form method="post" action="/login">
        <div class="form-group">
            <input type="email" name="username"  placeholder="<spring:message code="login.email"/>" />
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="<spring:message code="login.password"/>" />
            <c:if test="${param['error'] != null}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <spring:message code="login.ILD"/> Błędne dane logowania!
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <a href="/reset" class="btn btn--small btn--without-border reset-password"><spring:message code="login.RP"/></a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/register" class="btn btn--without-border"><spring:message code="login.CAA"/></a>
            <button class="btn" type="submit"><spring:message code="login.SI"/></button>
        </div>
        <sec:csrfInput/>
    </form>
</section>


<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
