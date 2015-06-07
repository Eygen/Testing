<%--@elvariable id="foundStudent" type="com.epam.zt.testing.model.student"--%>
<%--@elvariable id="questions" type="com.epam.zt.testing.action.getstudenttestadminaction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/editTest.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title><fmt:message key="title"/> - <fmt:message key="view_test"/> </title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="view_test"/></h1>

        <p class="lead">${foundStudent.lastName} ${foundStudent.firstName}</p>
    </div>
    <div class="wrapper">
        <c:set var="number" value="1"/>
        <c:forEach var="question" items="${questions}">
            <div class="question">
                <p><strong>${number})</strong> ${question.description}</p>
                <ul>
                    <c:forEach var="answer" items="${question.answers}">
                        <c:if test="${answer.correct==false}">
                            <li style="color: #ff0000">${answer.description}<br/></li>
                        </c:if>
                        <c:if test="${answer.correct==true}">
                            <li style="color: #228b22">${answer.description}<br/></li>
                        </c:if>
                    </c:forEach>
                </ul>
                <c:set var="number" value="${number+1}"/>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
