<%--@elvariable id="studentError" type="com.epam.zt.testing.action.findtestresultsaction"--%>
<%--@elvariable id="found" type="com.epam.zt.testing.action.findtestresultsaction"--%>
<%--@elvariable id="studentTests" type="com.epam.zt.testing.action.findtestresultsaction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/main.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title><fmt:message key="title"/> - <fmt:message key="view_result"/></title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="view_result"/></h1>
    </div>
    <div class="col-lg-3">
        <form role="search" action="<c:url value='${pageContext.request.contextPath}/testing/findTestResults' />"
              method="post">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Last Name" name="last_name">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="First Name" name="first_name">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><fmt:message key="search"/></button>
            </div>
        </form>
    </div>
    <div class="error col-lg-12">
        <c:if test="${studentError==1}">
            <fmt:message key="no_student"/>
        </c:if>
        <c:if test="${found=='empty'}">
            <fmt:message key="empty_entries"/>
        </c:if>
        <c:if test="${found=='not_found'}">
            <fmt:message key="no_student_tests"/>
        </c:if>
    </div>
    <c:if test="${found == 'found'}">
        <div class="col-lg-12">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th><fmt:message key="student"/></th>
                        <th><fmt:message key="subject"/></th>
                        <th><fmt:message key="mark"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="test" items="${studentTests}">
                        <tr>
                            <td>${test.student.lastName} ${test.student.firstName}</td>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/viewTestResults?test_id=${test.id}&student_id=${test.student.id}' />">${test.subject.name}</a>
                            </td>
                            <td>${test.mark.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
