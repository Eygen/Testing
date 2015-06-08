<%--@elvariable id="user" type="com.epam.zt.testing.model.User"--%>
<%--@elvariable id="tests" type="com.epam.zt.testing.action.GetHomeStudentAction"--%>
<%--@elvariable id="emptyGroup" type="com.epam.zt.testing.action.GetHomeStudentAction"--%>
<%--@elvariable id="testList" type="com.epam.zt.testing.action.GetHomeStudentAction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/studentHome.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="home"/> </title>
</head>
<body>
<c:import url="studentMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="tests"/> </h1>

        <p class="lead"><fmt:message key="select_test"/> </p>
    </div>
    <c:if test="${emptyGroup=='empty'}">
        <div class="settings">
            <h4><fmt:message key="no_link_group"/> </h4><br/>
            <a href="<c:url value = '${pageContext.request.contextPath}/testing/studentSettings' />">
                <button class="btn btn-info btn-lg"><fmt:message key="settings"/> </button>
            </a>
        </div>
    </c:if>
    <c:if test="${testList=='empty'}">
        <h2><fmt:message key="no_tests"/> </h2>
    </c:if>
    <c:if test="${testList=='exist'}">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th><fmt:message key="subject"/> </th>
                <th><fmt:message key="is _available"/> </th>
            </tr>
            </thead>
            <c:forEach var="test" items="${tests}">
                <tr>
                    <td>
                        <c:if test="${test.active==true}">
                            <a href="<c:url value = '${pageContext.request.contextPath}/testing/startTest?test_id=${test.id}' />">${test.subject.name}</a>
                        </c:if>
                        <c:if test="${test.active==false}">
                            ${test.subject.name}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${test.active==true}">
                            <fmt:message key="available"/>
                        </c:if>
                        <c:if test="${test.active==false}">
                            <fmt:message key="not_available"/>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
