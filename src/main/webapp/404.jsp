<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="/lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="/lib/css/main.css" rel="stylesheet"/>
    <script src="/lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="/lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/></title>
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
            <div class="navbar-brand"> <fmt:message key="title"/> </div>
        </div>
    </div>
</nav>

<div class="container">
    <div class="page-header">
        <h1 class="cover-heading"><fmt:message key="error404"/></h1>

        <h2 class="lead"><fmt:message key="no_page"/></h2>
    </div>
</div>
</body>
</html>
