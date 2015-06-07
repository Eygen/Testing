<%--@elvariable id="exist_marks" type="com.epam.zt.testing.action.viewmarkaction"--%>
<%--@elvariable id="marks" type="com.epam.zt.testing.action.viewmarkaction"--%>
<%--@elvariable id="prev" type="com.epam.zt.testing.action.viewmarkaction"--%>
<%--@elvariable id="next" type="com.epam.zt.testing.action.ViewMarkAction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/viewMark.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="view_mark"/></title>
</head>
<body>
<c:import url="studentMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="view_mark"/></h1>
    </div>
    <div class="col-lg-12">
        <c:if test="${exist_marks=='no'}">
            <div align="center"><h3><fmt:message key="no_student_tests"/></h3></div>
        </c:if>
        <c:if test="${exist_marks=='yes'}">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="subject"/></th>
                    <th><fmt:message key="mark"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="mark" items="${marks}">
                    <tr>
                        <td>${mark.test.subject.name}</td>
                        <td>${mark.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <ul class="pager">
                <c:if test="${prev=='no'}">
                    <li class="previous hidden"><a
                            href="<c:url value='${pageContext.request.contextPath}/testing/pageMark?page=prev' />">&larr;
                        <fmt:message key="previous"/> </a></li>
                </c:if>
                <c:if test="${prev=='yes'}">
                    <li class="previous">
                        <a href="<c:url value='${pageContext.request.contextPath}/testing/pageMark?page=prev' />">&larr;
                            <fmt:message key="previous"/> </a></li>
                </c:if>

                <c:if test="${next=='no'}">
                    <li class="next hidden"><a
                            href="<c:url value='${pageContext.request.contextPath}/testing/pageMark?page=next' />"><fmt:message
                            key="next"/> &rarr;</a></li>
                </c:if>
                <c:if test="${next=='yes'}">
                    <li class="next">
                        <a href="<c:url value='${pageContext.request.contextPath}/testing/pageMark?page=next' />"><fmt:message
                                key="next"/> &rarr;</a></li>
                </c:if>
            </ul>
        </c:if>
    </div>
</div>
</body>
</html>
