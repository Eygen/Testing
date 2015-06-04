<%--@elvariable id="studentError" type="com.epam.zt.testing.action.findtestresultsaction"--%>
<%--@elvariable id="found" type="com.epam.zt.testing.action.testresultsaction"--%>
<%--@elvariable id="foundByStudent" type="com.epam.zt.testing.action.findtestresultsaction"--%>
<%--@elvariable id="studentTests" type="com.epam.zt.testing.action.findtestresultsaction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/category.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title>Testing - Test Result</title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Test Result</h1>
    </div>
    <div class="col-lg-3">
        <form role="search" action="<c:url value='${pageContext.request.contextPath}/testing/findTestResults' />"
              method="post">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Last Name" name="last_name" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="First Name" name="first_name" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Find Student</button>
            </div>
        </form>
    </div>
    <div class="col-lg-12">
        <div class="error">${studentError}</div>
        <c:if test="${foundByStudent=='not_found'}">
            <div class="error">No passed tests for this student!</div>
        </c:if>
    </div>
    <c:if test="${foundByStudent == 'found'}">
        <div class="col-lg-12">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Subject</th>
                        <th>Mark</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="test" items="${studentTests}">
                        <tr>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/viewTestResults?test_id=${test.id}' />">${test.subject.name}</a>
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
