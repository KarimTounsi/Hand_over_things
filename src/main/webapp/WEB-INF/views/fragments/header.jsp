<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                        <li><a href="/user/edit-profile">Profil</a></li>
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
            <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="btn btn--without-border dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown" aria-haspopup="true"
               aria-expanded="false">Strony admina
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdown02">
                <a class="dropdown-item" href="/admin/orders/all">Zamówienia użytkowników</a>
                <a class="dropdown-item" href="/admin/category/all">Lista kategorii</a>
                <a class="dropdown-item" href="/admin/category/add">Dodaj kategorie</a>
                <a class="dropdown-item" href="/admin/category/all/deleted">Lista usuniętych kategorii</a>
                <a class="dropdown-item" href="/admin/image/image-without-relations">Lista zdjęć <br> nie dodanych do produktu</a>
                <a class="dropdown-item" href="/admin/image/add">Dodaj zdjęcie</a>
                <a class="dropdown-item" href="/admin/product/add">Dodaj produkt</a>
                <a class="dropdown-item" href="/admin/product/all/deleted">Lista usuniętych produktów</a>
            </div>
        </li>
        </sec:authorize>
        </li>
        <li><a href="/" class="btn btn--without-border active">Start</a></li>
        <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="#" class="btn btn--without-border">O nas</a></li>
        <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="#" class="btn btn--without-border">Kontakt</a></li>

<%--        <li><a href="#" class="btn btn--without-border">--%>
<%--            --%>
<%--            <sec:authorize--%>
<%--                access="!isAuthenticated()">nie zalogowany</sec:authorize></a></li>--%>
<%--        <li><a href="#" class="btn btn--without-border"><sec:authorize--%>
<%--                access="isAuthenticated()"> ZALOGOWANY </sec:authorize></a></li>--%>
    </ul>
</nav>







