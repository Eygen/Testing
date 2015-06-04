<%--@elvariable id="foundStudent" type="com.epam.zt.testing.model.student"--%>
<%--@elvariable id="questions" type="com.epam.zt.testing.action.getstudenttestadminaction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/editTest.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title>Testing - View Test</title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>View Test</h1>
        <p class="lead">${foundStudent.lastName} ${foundStudent.firstName}</p>
    </div>
    <div class="wrapper">
        <c:set var="number" value="1"/>
        <c:forEach var="question" items="${questions}">
            <div class="question">
                <p><strong>${number})</strong> ${question.description}</p>
                <ul>
                    <c:forEach var="answer" items="${question.answers}">
                        <li>${answer.description}<br/></li>
                    </c:forEach>
                </ul>
                <c:set var="number" value="${number+1}"/>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
