<%--@elvariable id="email" type="com.epam.zt.testing.action.registryaction"--%>
<%--@elvariable id="login" type="com.epam.zt.testing.action.registryaction"--%>
<%--@elvariable id="last_name" type="com.epam.zt.testing.action.registryaction"--%>
<%--@elvariable id="first_name" type="com.epam.zt.testing.action.registryaction"--%>
<%--@elvariable id="confirmError" type="com.epam.zt.testing.action.registryaction"--%>
<%--@elvariable id="loginError" type="com.epam.zt.testing.action.registryaction"--%>
<%--@elvariable id="passwordError" type="com.epam.zt.testing.action.registryaction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/registry.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title>Testing - Registration</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form role="form" action="<c:url value='${pageContext.request.contextPath}/testing/registry' />"
                  method="post">
                <h2>Please Sign Up</h2>
                <hr class="colorgraph">

                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="text" name="first_name" id="first_name" class="form-control input-lg"
                                   placeholder="First Name" value="${first_name}" tabindex="1" required>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="text" name="last_name" id="last_name" class="form-control input-lg"
                                   placeholder="Last Name" value="${last_name}" tabindex="2" required>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" name="login" id="display_name" class="form-control input-lg"
                           placeholder="Login" value="${login}" tabindex="3" required>
                </div>
                <div class="error">${loginError}</div>
                <div class="form-group">
                    <input type="email" name="email" id="email" class="form-control input-lg"
                           placeholder="Email Address" value="${email}" tabindex="4" required>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="password" name="password" id="password" class="form-control input-lg"
                                   placeholder="Password" tabindex="5" required>
                        </div>
                        <div class="error">${passwordError}</div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="form-group">
                            <input type="password" name="password_confirmation" id="password_confirmation"
                                   class="form-control input-lg" placeholder="Confirm Password" tabindex="6" required>
                        </div>
                        <div class="error">${confirmError}</div>
                    </div>
                    <p>Пароль должен быть не менее 8 символов. Должен состоять из заглавных и прописных латинских букв,
                        цифр и спецсимволов.</p>
                </div>

                <hr class="colorgraph">
                <div class="row">
                    <div class="button"><input type="submit" value="Register"
                                               class="btn btn-info btn-block btn-lg" tabindex="7"></div>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
