<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="container container--70">
    <ul class="nav--actions">
<sec:authorize access="!isAuthenticated()">
        <li>
            <form class="form-inline my-2 my-lg-0" method="get" action="/login">
            <button class="btn btn--small btn--without-border">Zaloguj</button>
                <sec:csrfInput/>
            </form>
        </li>
</sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li>
                <form class="form-inline my-2 my-lg-0" method="post" action="/logout">
                <button class="btn btn--small btn--without-border">Wyloguj</button>
                        <sec:csrfInput/>
                </form>
            </li>
        </sec:authorize>
        <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </ul>

    <ul>
        <li><a href="" class="btn btn--without-border active">Start</a></li>
        <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="#" class="btn btn--without-border">O nas</a></li>
        <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        <li><a href="#" class="btn btn--without-border"><sec:authorize access="!isAuthenticated()">nie zalogowany</sec:authorize></a></li>
        <li><a href="#" class="btn btn--without-border"><sec:authorize access="isAuthenticated()"> ZALOGOWANY </sec:authorize></a></li>
    </ul>
</nav>







