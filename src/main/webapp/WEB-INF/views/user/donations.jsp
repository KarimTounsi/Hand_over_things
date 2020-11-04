<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title><spring:message code="donations.donations"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page" STYLE="height: 140px">

    <jsp:include page="../fragments/header.jsp"/>
</header>

<section class="help">
    <h2><spring:message code="donations.LOG"/></h2>
    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p class="h1"><spring:message code="donations.sorting"/></p>
        <span class="h2 sort" data-path="sortByReceiveStatusDesc">|<spring:message code="donations.received"/>|</span>
        <span class="h2 sort" data-path="sortByReceiveStatusAsc">|<spring:message code="donations.NR"/>|</span> <br>
        <span class="h2 sort" data-path="sortByPickUpAsc">|<spring:message code="donations.RADID"/>|</span> <br>
        <span class="h2 sort" data-path="sortByPickUpDesc">|<spring:message code="donations.RADN"/>| </span> <br>
        <span class="h2 sort" data-path="sortByCreatedAsc">|<spring:message code="donations.ATECDIO"/>|</span> <br>
        <span class="h2 sort" data-path="sortByCreatedDesc">|<spring:message code="donations.ATECDNF"/>|</span>
<br>
        <ul class="help--slides-items list" data-method="GET">
            <c:forEach varStatus="theCount" begin="1" end="${institutions.size()}" var="index" step="2">
                <li>
                    <div class="col">
                        <div class="title">${institutions[theCount.index-1].name}</div>
                        <div class="subtitle">${institutions[theCount.index-1].description}</div>
                    </div>
                    <div class="col">
                        <div class="title">${institutions[theCount.index].name}</div>
                        <div class="subtitle">${institutions[theCount.index].description}</div>
                    </div>
                </li>
            </c:forEach>
        </ul>
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
<script src="<c:url value="/resources/js/app-rest-donations.js"/>"></script>
</body>
</html>
