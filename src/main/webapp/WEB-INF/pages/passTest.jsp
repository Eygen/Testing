<%--@elvariable id="num" type="com.epam.zt.testing.action.getpasstestaction"--%>
<%--@elvariable id="passTest" type="com.epam.zt.testing.model.Test"--%>
<%--@elvariable id="question" type="com.epam.zt.testing.model.question"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/main.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title>Testing - Pass Test</title>
</head>
<body>
<nav class="navbar-inverse navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"
               href="<c:url value = '${pageContext.request.contextPath}/testing/studentHome' />">Testing</a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="page-header">
        <h1>Pass Test</h1>

        <p class="lead">${passTest.subject.name}</p>
    </div>
    <div class="container">
        <p style="font-size: 1.5em">Question ${num+1}</p>
        <p>${question.description}</p>

        <form role="form"
              action="<c:url value='${pageContext.request.contextPath}/testing/passTest' />"
              method="post">
            <div class="form-group">

                <div>
                    <c:forEach var="answer" items="${question.answers}">
                        <div class="radio">
                            <label>
                                <input type="radio" name="answer" value="${answer.id}" required>
                                    ${answer.description}
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success">Next Question</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
