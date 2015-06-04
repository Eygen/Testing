<%--@elvariable id="test" type="com.epam.zt.testing.model.test"--%>
<%--@elvariable id="emptyQuestions" type="com.epam.zt.testing.action.getviewtesttutoraction"--%>
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
    <title><fmt:message key="title"/> - <fmt:message key="edit_test"/> </title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>${test.subject.name}</h1>
    </div>
    <div>
        <form role="form"
              action="<c:url value='${pageContext.request.contextPath}/testing/editTest' />"
              method="post">
            <button class="btn btn-danger" type="submit"><fmt:message key="delete"/> </button>
        </form>
        <c:choose>
            <c:when test="${emptyQuestions=='empty'}">
                <div class="empty" align="center">
                    <h2>Test is empty!</h2>
                    <a href="<c:url value = '${pageContext.request.contextPath}/testing/testEditor' />">
                        <button class="btn btn-primary"><fmt:message key="test_editor"/> </button>
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="wrapper">
                    <a href="<c:url value = '${pageContext.request.contextPath}/testing/testEditor' />">
                        <button class="btn btn-info"><fmt:message key="test_editor"/></button>
                    </a>
                    <a href="<c:url value = '${pageContext.request.contextPath}/testing/assignTest' />">
                        <button class="btn btn-info" type="submit"><fmt:message key="assign_test"/> </button>
                    </a>
                </div>
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
</div>
</body>
</html>
