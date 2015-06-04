<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/registrySuccess.css" rel="stylesheet"/>
    <title><fmt:message key="title"/></title>
</head>
<body>
<div class="site-wrapper">
    <div class="site-wrapper-inner">
        <div class="cover-container">
            <div class="inner cover">
                <h1 class="cover-heading"><fmt:message key="created_account"/> </h1>

                <p class="lead"><fmt:message key="activation"/> </p>

                <p class="lead">
                    <a href="<c:url value = '${pageContext.request.contextPath}/testing/logout' />"
                       class="btn btn-lg btn-primary"><fmt:message key="home"/> </a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
