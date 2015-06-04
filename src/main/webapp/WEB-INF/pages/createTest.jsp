<%--@elvariable id="subjects" type="com.epam.zt.testing.action.getcreatetestaction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/createTest.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="create_test"/> </title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="create_test"/></h1>
    </div>
    <div class="row">
        <div class="col-lg-5">
            <div class="well bs-component">
                <form class="form-horizontal" role="form"
                      action="<c:url value='${pageContext.request.contextPath}/testing/createTest' />"
                      method="post">
                    <fieldset>
                        <div class="form-group">
                            <label for="select" class="col-lg-5 control-label"><fmt:message key="subject"/> </label>

                            <div class="col-lg-7">
                                <select class="form-control" id="select" name="subject" required>
                                    <c:forEach var="subject" items="${subjects}">
                                        <option>${subject.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="amountQuestions" class="col-lg-5 control-label"><fmt:message key="number_questions"/> </label>

                            <div class="col-lg-7">
                                <input type="text" class="form-control" id="amountQuestions" name="amountQuestions"
                                       value="20" pattern="(([1-9])([0-9]){1})" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-7 col-lg-offset-5">
                                <button type="reset" class="btn btn-default"><fmt:message key="cancel"/> </button>
                                <button type="submit" class="btn btn-info"><fmt:message key="submit"/> </button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
