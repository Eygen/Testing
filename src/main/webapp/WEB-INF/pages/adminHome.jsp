<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/adminHome.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="home"/></title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="services"/> </h1>
    </div>
    <div class="row">
        <div class="col-md-3">
            <h2><fmt:message key="users"/> </h2>
            <p><fmt:message key="users_description"/> </p>
            <p><a class="btn btn-primary" href="<c:url value = '${pageContext.request.contextPath}/testing/userCategory' />" role="button"><fmt:message key="details"/> &raquo;</a></p>
        </div>
        <div class="col-md-3">
            <h2><fmt:message key="subjects"/> </h2>
            <p><fmt:message key="subjects_description"/> </p><br/>
            <p><a class="btn btn-primary" href="<c:url value = '${pageContext.request.contextPath}/testing/subjectCategory' />" role="button"><fmt:message key="details"/> &raquo;</a></p>
        </div>
        <div class="col-md-3">
            <h2><fmt:message key="groups"/> </h2>
            <p><fmt:message key="groups_description"/> </p>
            <p><a class="btn btn-primary" href="<c:url value = '${pageContext.request.contextPath}/testing/groupCategory' />" role="button"><fmt:message key="details"/> &raquo;</a></p>
        </div>
        <div class="col-md-3">
            <h2><fmt:message key="tests"/> </h2>
            <p><fmt:message key="tests_description"/> </p>
            <p><a class="btn btn-primary" href="<c:url value = '${pageContext.request.contextPath}/testing/testCategory' />" role="button"><fmt:message key="details"/> &raquo;</a></p>
        </div>
    </div>
</div>
</body>
</html>
