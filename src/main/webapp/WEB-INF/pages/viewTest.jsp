<%--@elvariable id="emptyQuestions" type="com.epam.zt.testing.action.getviewtestadminaction"--%>
<%--@elvariable id="test" type="com.epam.zt.testing.model.Test"--%>
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
        <p class="lead">${test.subject.name}</p>
    </div>
    <form role="form"
          action="<c:url value='${pageContext.request.contextPath}/testing/deleteTest' />"
          method="post">
        <button class="btn btn-danger" type="submit"><fmt:message key="delete"/> </button>
    </form><br/>
    <c:choose>
        <c:when test="${emptyQuestions=='empty'}">
            <div class="empty" align="center">
                <h2><fmt:message key="empty_test"/> </h2>
            </div>
        </c:when>
        <c:otherwise>
            <div class="wrapper">
                <c:set var="number" value="1"/>
                <c:forEach var="question" items="${test.questions}">
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
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
