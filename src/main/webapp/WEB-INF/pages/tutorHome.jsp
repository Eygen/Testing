<%--@elvariable id="testList" type="com.epam.zt.testing.action.gethometutoraction"--%>
<%--@elvariable id="tests" type="com.epam.zt.testing.action.GetHomeTutorAction"--%>
<%--@elvariable id="delete" type="com.epam.zt.testing.action.gethometutoraction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/tutorHome.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title>Testing - Home</title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Tests</h1>

        <p class="lead">Created tests</p>
    </div>
    <c:if test="${delete==1}"><h3 align="center" style="color: #228b22;">Test is deleted!</h3></c:if>
    <c:if test="${testList=='empty'}">
        <h2>No tests yet</h2>
    </c:if>
    <c:if test="${testList=='exist'}">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Subject</th>
                <th>The number of questions (for student)</th>
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
