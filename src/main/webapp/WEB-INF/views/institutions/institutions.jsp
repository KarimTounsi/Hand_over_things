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
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>


    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Strona Admina</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Lista fundacji</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Lista administratorów</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Lista użytkowników</a>
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

    <form class="needs-validation mt-3" novalidate>
        <div class="form-row">
            <div class="col-md-6 mb-3">
                <label for="name">Nazwa instytucji</label>
                <input type="text" class="form-control" id="name">
                <div class="valid-tooltip">
                    Looks good!
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="description">Opis</label>
                <input type="text" class="form-control" id="description">
                <div class="valid-tooltip">
                    Looks good!
                </div>
            </div>
        </div>
        <button class="btn btn-primary" id="addButton" data-method="POST" type="submit">Dodaj fundacje</button>
    </form>

    <table class="table mt-3" >
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nazwa instytucji</th>
            <th scope="col">Opis</th>
            <th scope="col">Edycja pól</th>
            <th scope="col">Edycja całości</th>
            <th scope="col">Usuwanie</th>
        </tr>
        </thead>
        <tbody class="tableInput" data-method="GET">

        </tbody>
    </table>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="<c:url value="/resources/js/app-rest.js"/>"></script>
</body>
</html>

