<%--@elvariable id="testList" type="com.epam.zt.testing.action.gethometutoraction"--%>
<%--@elvariable id="tests" type="com.epam.zt.testing.action.GetHomeTutorAction"--%>
<%--@elvariable id="delete" type="com.epam.zt.testing.action.gethometutoraction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/tutorHome.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title><fmt:message key="title"/> - <fmt:message key="home"/> </title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="tests"/> </h1>

        <p class="lead"><fmt:message key="created_tests"/> </p>
    </div>
    <c:if test="${delete==1}"><h3 align="center" style="color: #228b22;"><fmt:message key="test_deleted"/> </h3></c:if>
    <c:if test="${testList=='empty'}">
        <h2><fmt:message key="no_tests"/> </h2>
    </c:if>
    <c:if test="${testList=='exist'}">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th><fmt:message key="subject"/> </th>
                <th><fmt:message key="number_questions"/> </th>
                <th style="width: 36px;"></th>
            </tr>
            </thead>
            <c:forEach var="test" items="${tests}">
                <tr>
                    <td>
                        <a href="<c:url value = '${pageContext.request.contextPath}/testing/editTest?test_id=${test.id}' />">${test.subject.name}</a>
                    </td>
                    <td>${test.questionAmount}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
