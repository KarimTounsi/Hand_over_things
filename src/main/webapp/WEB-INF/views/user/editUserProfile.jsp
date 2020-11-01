<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page" STYLE="height: 140px">
    <jsp:include page="../fragments/header.jsp"/>
<%--    <main role="main">--%>
<%--        <div class="jumbotron mt-5">--%>
<%--            <div class="container text-center">--%>
<%--                <form class="needs-validation mt-3" novalidate>--%>
<%--                    <div class="form-row">--%>
<%--                        <div class="col-md-12 mb-3">--%>

<%--                            <label for="email" data-id="${id}" data-method="GET" class="id" >Email</label>--%>
<%--                            <input type="text" class="form-control"  id="email">--%>
<%--                            <br> <form:errors path="email" cssClass="error"/>--%>
<%--                            <div class="valid-tooltip">--%>
<%--                                Looks good!--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-12 mb-3">--%>
<%--                            <label for="password">Nowe hasło</label>--%>
<%--                            <input type="password" class="form-control" id="password" placeholder="Nowe hasło">--%>
<%--                            <br> <form:errors path="password" cssClass="error"/>--%>
<%--                            <div class="valid-tooltip">--%>
<%--                                Looks good!--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-12 mb-3">--%>
<%--                            <label for="passwordCheck">Powtórz nowe hasło</label>--%>
<%--                            <input type="password" class="form-control" id="passwordCheck" placeholder="Powtórz nowe hasło">--%>
<%--                            <br> <form:errors path="passwordCheck" cssClass="error"/>--%>
<%--                            <div class="valid-tooltip">--%>
<%--                                Looks good!--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <button class="btn btn-primary" id="addButton" data-method="POST" type="submit">Zmień email</button>--%>
<%--                    <button class="btn btn-primary" id="addButton2" data-method="POST" type="submit">Zmień hasło</button>--%>
<%--                </form>--%>

<%--            </div>--%>
<%--        </div>--%>

<%--    </main>--%>
</header>
<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Edycja danych profilu</h3>
            <p data-step="1" class="active">
        </div>
    </div>
    <div class="form--steps-container">
        <div class="form--steps-counter"><span></span></div>
        <form:form>
            <div data-step="1">
                <h3>Wpisz nowe dane</h3>
                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <div class="form-group form-group--inline">
                            <label data-id="${id}" data-method="GET" class="id">Email
                                <input type="text"  id="email"/>
                                <br>
                            </label>

                        </div>
                        <div class="form-group form-group--buttons">
                            <button type="submit" class="btn" id="addButton" data-method="POST">Wprowadź dane</button>
                        </div>

                        </form:form>

                        <form:form>
                        <div class="form-group form-group--inline">

                        </div>

                        <div class="form-group form-group--inline">

                        </div>
                    </div>
                    <div class="form-section--column">
                        <div class="form-group form-group--inline">
                            <label> Nowe hasło <input type="password" id="password" placeholder="Nowe hasło" />
                                <br> <form:errors path="password" cssClass="error"/>
                            </label>
                        </div>

<%--                        <div class="form-group form-group--inline">--%>
<%--                            <label> Powtórz<br>nowe hasło <input type="password" id="passwordCheck" placeholder="Powtórz nowe hasło"/>--%>
<%--                                <br> <form:errors path="passwordCheck" cssClass="error"/>--%>
<%--                            </label>--%>
<%--                        </div>--%>
<%--                        <div class="form-group form-group--inline">--%>
<%--                            <div class="form-group form-group--buttons">--%>
<%--                                <button type="submit" class="btn" id="addButton2" data-method="POST">Zmień hasło</button>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </div>
                </div>
            </div>
        </form:form>

    </div>

</section>
<jsp:include page="../fragments/footer.jsp"/>
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
<script src="<c:url value="/resources/js/app.js"/>"></script>

<script src="<c:url value="/resources/js/app-rest-user.js"/>"></script>
<script>
    getUser();
    putUpdate();
</script>


</body>
</html>
