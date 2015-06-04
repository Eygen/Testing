<%--@elvariable id="user" type="com.epam.zt.testing.model.User"--%>
<%--@elvariable id="tests" type="com.epam.zt.testing.action.GetHomeStudentAction"--%>
<%--@elvariable id="emptyGroup" type="com.epam.zt.testing.action.GetHomeStudentAction"--%>
<%--@elvariable id="testList" type="com.epam.zt.testing.action.GetHomeStudentAction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/studentHome.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title>Testing - Home</title>
</head>
<body>
<c:import url="studentMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Tests</h1>

        <p class="lead">Select the test to pass</p>
    </div>
    <c:if test="${emptyGroup=='empty'}">
        <div class="settings">
            <h4>No link of group. Please, specify in the settings!</h4><br/>
            <a href="<c:url value = '${pageContext.request.contextPath}/testing/studentSettings' />">
                <button class="btn btn-info btn-lg">Settings</button>
            </a>
        </div>
    </c:if>
    <c:if test="${testList=='empty'}">
        <h2>No tests yet</h2>
    </c:if>
    <c:if test="${testList=='exist'}">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Subject</th>
                <th>Available</th>
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
                            Available
                        </c:if>
                        <c:if test="${test.active==false}">
                            Not available
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
