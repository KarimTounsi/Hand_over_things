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


<jsp:include page="../fragments/AdminPageheader.jsp"/>

    <form class="needs-validation mt-3" novalidate>
        <div class="form-row">
            <div class="col-md-6 mb-3">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email">
                <br> <form:errors path="email" cssClass="error"/>
                <div class="valid-tooltip">
                    Looks good!
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="password">hasło</label>
                <input type="password" class="form-control" id="password">
                <br> <form:errors path="password" cssClass="error"/>
                <div class="valid-tooltip">
                    Looks good!
                </div>
            </div>
        </div>
        <button class="btn btn-primary" id="addButton" data-method="POST" type="submit">Dodaj admina</button>
    </form>

    <table class="table mt-3" >
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Email</th>
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

<script src="<c:url value="/resources/js/app-rest-user.js"/>"></script>
</body>
</html>

