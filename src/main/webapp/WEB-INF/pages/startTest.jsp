<%--@elvariable id="passTest" type="com.epam.zt.testing.model.test"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="pass_test"/> </title>
</head>
<body>
<c:import url="studentMainMenu.jsp"/>
<div class="container">
    <div align="center" style="margin: 150px 200px;">
        <h3><fmt:message key="test_content"/> ${passTest.questionAmount} <fmt:message key="test_description"/> </h3>
        <br/>

        <form role="form"
              action="<c:url value='${pageContext.request.contextPath}/testing/startTest' />"
              method="post">
            <div class="form-group">
                <button type="submit" class="btn btn-info btn-lg" style="padding-left: 40px; padding-right: 40px;">Start</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
