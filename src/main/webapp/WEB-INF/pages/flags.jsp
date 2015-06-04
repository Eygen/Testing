<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/flags.css" rel="stylesheet"/>
    <title></title>
</head>
<body>
<div class="container">
        <ul class="nav nav-pills pull-right">
            <li><a href="#">
                <div class="flag flag-kz"></div>
            </a></li>
            <li><a href="<c:url value='${pageContext.request.contextPath}/testing/language?flag=2' />">
                <div class="flag flag-ru"></div>
            </a></li>
            <li><a href="#">
                <div class="flag flag-gb"></div>
            </a></li>
        </ul>
</div>
</body>
</html>
