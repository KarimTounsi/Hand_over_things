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
    <title><spring:message code="editUserProfile.editUserProfile"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page" STYLE="height: 140px">
    <jsp:include page="../fragments/header.jsp"/>
</header>
<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3><spring:message code="editUserProfile.EPD"/></h3>
            <p data-step="1" class="active">
        </div>
    </div>
    <div class="form--steps-container">
        <div class="form--steps-counter"><span></span></div>
        <form:form>
            <div data-step="1">
                <h3><spring:message code="editUserProfile.END"/></h3>
                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <div class="form-group form-group--inline">
                            <label data-id="${id}" data-method="GET" class="id"><spring:message code="editUserProfile.email"/>
                                <input type="text"  id="email"/>
                                <br>
                            </label>

                        </div>
                        <div class="form-group form-group--buttons">
                            <button type="submit" class="btn" id="addButton" data-method="POST"><spring:message code="editUserProfile.ED"/></button>
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
                            <label><spring:message code="editUserProfile.NP"/><input type="password" id="password" placeholder="<spring:message code="editUserProfile.NP"/>" />
                                <br> <form:errors path="password" cssClass="error"/>
                            </label>
                        </div>
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
