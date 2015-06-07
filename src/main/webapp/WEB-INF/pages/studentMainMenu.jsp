<%--@elvariable id="user" type="com.epam.zt.testing.model.Student"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <title></title>
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
               href="<c:url value = '${pageContext.request.contextPath}/testing/studentHome' />"><fmt:message
                    key="title"/> </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="activate"><a
                        href="<c:url value = '${pageContext.request.contextPath}/testing/studentHome' />"><fmt:message
                        key="home"/> </a>
                </li>
                <li class="inactivate"><a
                        href="<c:url value = '${pageContext.request.contextPath}/testing/viewMark' />"><fmt:message
                        key="view_mark"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="fa fa-user"></i> ${user.login} <b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<c:url value = '${pageContext.request.contextPath}/testing/studentSettings' />"><i
                                    class="fa fa-fw fa-gear"></i> <fmt:message key="settings"/> </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value = '${pageContext.request.contextPath}/testing/logout' />"><i
                                    class="fa fa-fw fa-power-off"></i> <fmt:message key="log_out"/> </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
