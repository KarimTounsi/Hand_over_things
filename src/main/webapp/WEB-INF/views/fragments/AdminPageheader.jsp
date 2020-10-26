<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/admin">Strona Admina</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/admin/institutions/view">Lista fundacji</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/admins/view">Lista administratorów</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/users/view">Lista użytkowników</a>
            </li>
        </ul>
        <%--                <li class="nav-item dropdown">--%>
        <%--                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
        <%--                        Dropdown--%>
        <%--                    </a>--%>
        <%--                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
        <%--                        <a class="dropdown-item" href="#">Action</a>--%>
        <%--                        <a class="dropdown-item" href="#">Another action</a>--%>
        <%--                        <div class="dropdown-divider"></div>--%>
        <%--                        <a class="dropdown-item" href="#">Something else here</a>--%>
        <%--                    </div>--%>
        <%--                </li>--%>
        <sec:authorize access="!isAuthenticated()">
            <form class="form-inline my-2 my-lg-0" method="get" action="/login">
                <button class="btn btn-primary my-2 my-sm-0 mr-sm-2" type="submit">Zaloguj</button>
                <sec:csrfInput/>
            </form>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">

            <form class="form-inline my-2 my-lg-0" method="post" action="/logout">
                <button class="btn btn-primary my-2 my-sm-0 mr-sm-2" type="submit">Wyloguj</button>
                <sec:csrfInput/>
            </form>

        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <div style="margin-right: 20px" class="nav-div"> Witaj,
                <strong>${pageContext.request.userPrincipal.principal.username}</strong></div>
        </sec:authorize>
    </div>
</nav>







