<%--@elvariable id="mark" type="com.epam.zt.testing.model.mark"--%>
<%--@elvariable id="passTest" type="com.epam.zt.testing.model.Test"--%>
<%--@elvariable id="correct" type="com.epam.zt.testing.action.postpasstestaction"--%>
<%--@elvariable id="timeExpire" type="com.epam.zt.testing.action.finishtestaction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/main.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="finish_test"/> </title>
</head>
<body>
<c:import url="studentMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="finish_test"/></h1>

        <p class="lead">${passTest.subject.name}</p>
    </div>
    <div align="center">
        <c:if test="${timeExpire=='timeExpire'}">
            <div class="error"><fmt:message key="time_expire"/> </div>
            <br/>
        </c:if>
        <h3><fmt:message key="test_mark"/> ${mark.value}</h3>
        <h3><fmt:message key="correct_answer"/> ${correct}</h3>
        <br/>
        <a href="<c:url value = '${pageContext.request.contextPath}/testing/studentHome' />">
            <button class="btn btn-info btn-lg" style="padding-left: 40px; padding-right: 40px;"><fmt:message key="home"/> </button>
        </a>
    </div>
</div>
</body>
</html>
