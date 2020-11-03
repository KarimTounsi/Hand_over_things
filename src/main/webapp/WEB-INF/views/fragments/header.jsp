<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="container container--70">
        <sec:authorize access="!isAuthenticated()">
            <ul class="nav--actions">
            <li>
                <form class="form-inline my-2 my-lg-0" method="get" action="/login">
                    <button class="btn btn--small btn--without-border"><spring:message code="header.login"/></button>
                    <sec:csrfInput/>
                </form>
            </li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <ul class="nav--actions">
                <li class="logged-user">
                        <div class="nav-div"> <spring:message code="header.hello"/>
                            <strong>${pageContext.request.userPrincipal.principal.username}</strong></div>
                    <ul class="dropdown">
                        <li><a href="/user/edit-profile"><spring:message code="header.profile"/></a></li>
                        <li><a href="/user/donations"><spring:message code="header.MC"/></a></li>
                        <li>
                            <form class="my-lg-0" method="post" action="/logout">
                                <button class="btn-primary mx-auto my-sm-0" ><spring:message code="header.logout"/></button>
                                <sec:csrfInput/>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
        <li><a href="/register" class="btn btn--small btn--highlighted"><spring:message code="header.register"/></a></li>
    </ul>
    </sec:authorize>

    <ul>
        <li>
        </li>
        <li><a href="/" class="btn btn--without-border active">Start</a></li>
        <li><a href="#" class="btn btn--without-border"><spring:message code="header.wii"/></a></li>
        <li><a href="#" class="btn btn--without-border"><spring:message code="header.about"/></a></li>
        <li><a href="#" class="btn btn--without-border"><spring:message code="header.FAO"/></a></li>
        <li><a href="#" class="btn btn--without-border"><spring:message code="header.contact"/></a></li>

<%--        <li><a href="#" class="btn btn--without-border">--%>
<%--            --%>
<%--            <sec:authorize--%>
<%--                access="!isAuthenticated()">nie zalogowany</sec:authorize></a></li>--%>
<%--        <li><a href="#" class="btn btn--without-border"><sec:authorize--%>
<%--                access="isAuthenticated()"> ZALOGOWANY </sec:authorize></a></li>--%>
    </ul>
</nav>







