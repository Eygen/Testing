<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--@elvariable id="loginError" type="com.epam.zt.testing.action.loginaction"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/login.css" rel="stylesheet"/>
    <title><fmt:message key="title"/> - <fmt:message key="log_in"/> </title>
</head>
<body>
<%--<c:import url="flags.jsp"/>--%>
<div class="container">
    <form class="form-signin" role="form" action="<c:url value='${pageContext.request.contextPath}/testing/login' />"
          method="post">
        <h2 class="form-signin-heading"><fmt:message key="title"/> - <fmt:message key="log_in"/></h2>
        <input type="text" class="form-control" placeholder="Login" name="login" required autofocus>
        <input type="password" class="form-control" placeholder="Password" name="password" required>
        <button class="btn btn-lg btn-info btn-block" type="submit"><fmt:message key="enter"/> </button>
        <div class="account"><a
                href="<c:url value='${pageContext.request.contextPath}/testing/registry' />"><fmt:message key="registration"/> </a></div>
        <div class="error">${loginError}</div>
    </form>
</div>
</body>
</html>
