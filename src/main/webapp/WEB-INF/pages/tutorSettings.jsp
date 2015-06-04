<%--@elvariable id="successUpdate" type="com.epam.zt.testing.action.getupdatetutoraction"--%>
<%--@elvariable id="passwordError" type="com.epam.zt.testing.action.getupdatetutoraction"--%>
<%--@elvariable id="email" type="com.epam.zt.testing.action.getupdatetutoraction"--%>
<%--@elvariable id="lastname" type="com.epam.zt.testing.action.getupdatetutoraction"--%>
<%--@elvariable id="firstname" type="com.epam.zt.testing.action.getupdatetutoraction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/settings.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title>Testing - Settings</title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Settings</h1>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <div class="well bs-component">
                <form class="form-horizontal" role="form"
                      action="<c:url value='${pageContext.request.contextPath}/testing/tutorSettings' />"
                      method="post">
                    <fieldset>
                        <div class="form-group">
                            <label for="firstname" class="col-lg-2 control-label">Firstname</label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="firstname" name="firstname"
                                       placeholder="Firstname" value="${firstname}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname" class="col-lg-2 control-label">Lastname</label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="lastname" name="lastname"
                                       placeholder="Lastname" value="${lastname}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-lg-2 control-label">Email</label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" id="email" name="email" placeholder="Email"
                                       value="${email}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-lg-2 control-label">Password</label>

                            <div class="col-lg-10">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="New password">

                                <div class="error">${passwordError}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-2">
                                <button type="reset" class="btn btn-default">Cancel</button>
                                <button type="submit" class="btn btn-info">Submit</button>
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
