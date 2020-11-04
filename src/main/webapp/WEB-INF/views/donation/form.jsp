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
    <title><spring:message code="form.F"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">

    <jsp:include page="../fragments/header.jsp"/>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                <spring:message code="form.GBWA"/><br/>
                <span class="uppercase"> <spring:message code="form.n"/></span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title"> <spring:message code="form.4SSAE"/></div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span> <spring:message code="form.CT"/></span></div>
                    </li>
                    <li>
                        <div><em>2</em><span> <spring:message code="form.PTIB"/></span></div>
                    </li>
                    <li>
                        <div><em>3</em><span> <spring:message code="form.CAF"/></span></div>
                    </li>
                    <li>
                        <div><em>4</em><span> <spring:message code="form.OAC"/></span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3> <spring:message code="form.I"/></h3>
            <p data-step="1" class="active">
                <spring:message code="form.CTDPTO"/>
            </p>
            <p data-step="2">
                <spring:message code="form.CTDPTO"/>
            </p>
            <p data-step="3">
                <spring:message code="form.COGT"/>
            </p>
            <p data-step="4"><spring:message code="form.PTAROI"/></p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter"><spring:message code="form.S"/><span>1</span>/4</div>
        <form:form action="/user/donation/form" method="post" modelAttribute="donationDto">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3> <spring:message code="form.SWGA"/></h3>
                <c:forEach varStatus="theCount" items="${categories}" var="category" step="1">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="checkbox" name="categories" value="${category.id}"/>
                            <span class="checkbox ch-category"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step"> <spring:message code="form.further"/></button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3> <spring:message code="form.ETNPYT"/></h3>

                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="form.NOLB"/>
                        <input type="number" class="bags" name="quantity" step="1" min="1"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"> <spring:message code="form.back"/></button>
                    <button type="button" class="btn next-step"> <spring:message code="form.further"/></button>
                </div>
            </div>


            <!-- STEP 4 -->
            <div data-step="3">
                <h3> <spring:message code="form.STOYWTH"/></h3>
                <c:forEach varStatus="theCount" items="${institutions}" var="institution" step="1">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="radio" name="institution" value="${institution.id}"/>
                            <span class="checkbox radio"></span>
                            <span class="institution">
                            <div class="title"  >${institution.name}</div>
                             <div class="subtitle">
                          ${institution.description}
                            </div>
                </span>
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"> <spring:message code="form.back"/></button>
                    <button type="button" class="btn next-step"> <spring:message code="form.further"/></button>
                </div>
            </div>
            <!-- STEP 5 -->
            <div data-step="4">
                <h3> <spring:message code="form.ETABTC"/></h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4> <spring:message code="form.PA"/></h4>
                        <div class="form-group form-group--inline">
                            <label>  <spring:message code="form.street"/><input type="text" name="street" id="street"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>  <spring:message code="form.city"/><input type="text" name="city"  id="city"  /> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="form.PC"/><input type="text" name="zipCode" id="zipCode"/>
                            </label>
                        </div>
                                                    <div class="form-group form-group--inline">
                                                        <label>
                                                            <spring:message code="form.PH"/><input type="text" name="phoneNumber" id="phoneNumber" />
                                                        </label>
                                                    </div>
                    </div>

                    <div class="form-section--column">
                        <h4> <spring:message code="form.DOR"/></h4>
                        <div class="form-group form-group--inline">
                            <label>  <spring:message code="form.date"/><input type="date" name="pickUpDate" id="pickUpDate"/></label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>  <spring:message code="form.hour"/><input type="time" name="pickUpTime" id="pickUpTime"/></label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="form.NFTC"/>
                                <textarea name="pickUpComment" rows="5" id="pickUpComment"></textarea>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"> <spring:message code="form.back"/></button>
                    <button type="button" class="btn next-step lastButton"> <spring:message code="form.further"/></button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3> <spring:message code="form.SOYD"/></h3>

                <div class="summary">
                    <div class="form-section">
                        <h4> <spring:message code="form.YGA"/></h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text quantityCategory" ></span
                                >
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text institutionData"
                                ></span
                                >
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4> <spring:message code="form.PA"/>:</h4>
                            <ul>
                                <li class="street"></li>
                                <li class="city"></li>
                                <li class="zipCode"></li>
                                <li class="phoneNumber"></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4> <spring:message code="form.DOR"/>:</h4>
                            <ul>
                                <li class="date"></li>
                                <li class="pickUpTime"></li>
                                <li class="pickUpComment"></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"> <spring:message code="form.back"/></button>
                    <button type="submit" class="btn"> <spring:message code="form.confirm"/></button>
                </div>
            </div>
            <sec:csrfInput/>
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
<script src="<c:url value="/resources/js/app.js"/>"></script>
<script src="<c:url value="/resources/js/app2.js"/>"></script>
</body>
</html>






































