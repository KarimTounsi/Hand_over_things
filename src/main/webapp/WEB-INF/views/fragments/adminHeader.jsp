<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav class="container container--70">
        <sec:authorize access="!isAuthenticated()">
            <ul class="nav--actions">
            <li>
                <form class="form-inline my-2 my-lg-0" method="get" action="/login">
                    <button class="btn btn--small btn--without-border">Zaloguj</button>
                    <sec:csrfInput/>
                </form>
            </li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <ul class="nav--actions">
                <li class="logged-user">
                        <div class="nav-div"> Witaj
                            <strong>${pageContext.request.userPrincipal.principal.username}</strong></div>
                    <ul class="dropdown">
                        <li><a href="#">Profil</a></li>
                        <li><a href="#">Moje zbiórki</a></li>
                        <li>
                            <form class="my-lg-0" method="post" action="/logout">
                                <button class="btn-primary mx-auto my-sm-0" >Wyloguj</button>
                                <sec:csrfInput/>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
        <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </ul>
    </sec:authorize>

    <ul>
        <li>
        </li>
        <li><a href="/admin" class="btn btn--without-border">Start</a></li>
        <li><a href="/institutions/view" class="btn btn--without-border">Lista fundacji</a></li>
        <li><a href="#" class="btn btn--without-border">Lista Administratorów</a></li>
        <li><a href="#" class="btn btn--without-border">Lista użytkowników</a></li>
<%--        <li><a href="#" class="btn btn--without-border">--%>
<%--            --%>
<%--            <sec:authorize--%>
<%--                access="!isAuthenticated()">nie zalogowany</sec:authorize></a></li>--%>
<%--        <li><a href="#" class="btn btn--without-border"><sec:authorize--%>
<%--                access="isAuthenticated()"> ZALOGOWANY </sec:authorize></a></li>--%>
    </ul>
</nav>







