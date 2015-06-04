<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="lib/css/error.css" rel="stylesheet"/>
    <title><fmt:message key="title"/></title>
</head>
<body>
<div class="wrapper">
    <div class="page-header">
        <h1 class="cover-heading"><fmt:message key="error500"/></h1>

        <h2 class="lead"><fmt:message key="no_access"/></h2>
    </div>
</div>
</body>
</html>
