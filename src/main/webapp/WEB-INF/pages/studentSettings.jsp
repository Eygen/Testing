<%--@elvariable id="groupError" type="com.epam.zt.testing.action.poststudentupdateaction"--%>
<%--@elvariable id="successUpdate" type="com.epam.zt.testing.action.poststudentupdateaction"--%>
<%--@elvariable id="passwordError" type="com.epam.zt.testing.action.poststudentupdateaction"--%>
<%--@elvariable id="email" type="com.epam.zt.testing.action.getstudentupdateaction"--%>
<%--@elvariable id="lastname" type="com.epam.zt.testing.action.getstudentupdateaction"--%>
<%--@elvariable id="firstname" type="com.epam.zt.testing.action.getstudentupdateaction"--%>
<%--@elvariable id="groups" type="com.epam.zt.testing.action.GetUpdateStudentAction"--%>
<%--@elvariable id="studentGroup" type="com.epam.zt.testing.action.GetUpdateStudentAction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/settings.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="settings"/> </title>
</head>
<body>
<c:import url="studentMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="settings"/></h1>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <div class="well bs-component">
                <form class="form-horizontal" role="form"
                      action="<c:url value='${pageContext.request.contextPath}/testing/studentSettings' />"
                      method="post">
                    <fieldset>
                        <div class="form-group">
                            <label for="firstname" class="col-lg-2 control-label"><fmt:message key="first_name"/> </label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="firstname" name="firstname"
                                       placeholder="Firstname" value="${firstname}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-lg-2 control-label"><fmt:message key="last_name"/> </label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="lastname" name="lastname"
                                       placeholder="Lastname" value="${lastname}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-lg-2 control-label"><fmt:message key="email"/> </label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="email" name="email" placeholder="Email"
                                       value="${email}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-lg-2 control-label"><fmt:message key="password"/> </label>

                            <div class="col-lg-10">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="New password">
                                <div class="error">${passwordError}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="select" class="col-lg-2 control-label"><fmt:message key="group"/> </label>

                            <div class="col-lg-10">
                                <select class="form-control" id="select" name="group">
                                    <c:forEach var="group" items="${groups}">
                                        <c:choose>
                                            <c:when test="${studentGroup==group.name}">
                                                <option selected>${group.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>${group.name}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                                <div class="error">${groupError}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-2">
                                <button type="reset" class="btn btn-default"><fmt:message key="cancel"/> </button>
                                <button type="submit" class="btn btn-info"><fmt:message key="submit"/> </button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="success">${successUpdate}</div>
        </div>
    </div>
</div>
</body>
</html>
