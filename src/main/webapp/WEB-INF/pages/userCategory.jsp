<%--@elvariable id="roleChange" type="com.epam.zt.testing.action.usercategorychangeroleaction"--%>
<%--@elvariable id="delete" type="com.epam.zt.testing.action.usercategorydeleteaction"--%>
<%--@elvariable id="found" type="com.epam.zt.testing.action.usercategoryfindaction"--%>
<%--@elvariable id="roles" type="com.epam.zt.testing.action.GetUserCategoryAction"--%>
<%--@elvariable id="foundUser" type="com.epam.zt.testing.model.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/category.css" rel="stylesheet"/>
    <link href="../../lib/css/main.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title>Testing - Users</title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Users</h1>
    </div>
    <div class="col-lg-3">
        <form role="search" action="<c:url value='${pageContext.request.contextPath}/testing/userCategoryFind' />"
              method="post">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Last Name" name="last_name" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="First Name" name="first_name" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Find User</button>
            </div>
        </form>
        <div class="success">
            ${roleChange}
            ${delete}
        </div>
        <c:if test="${found=='not_found'}">
            <div class="error">No such user!</div>
        </c:if>
    </div>
    <c:if test="${found == 'found'}">
        <div class="col-lg-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Last name</th>
                    <th>Fisrt name</th>
                    <th>Email</th>
                    <th>Login</th>
                    <th>Register date</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tr>
                    <td>${foundUser.lastName}</td>
                    <td>${foundUser.firstName}</td>
                    <td>${foundUser.email}</td>
                    <td>${foundUser.login}</td>
                    <td>${foundUser.registerDate}</td>
                    <td>${foundUser.role.name}</td>
                </tr>
            </table>
        </div>
        <div class="col-lg-5">
            <form class="form-inline well" role="form"
                  action="<c:url value='${pageContext.request.contextPath}/testing/userCategoryChangeRole' />"
                  method="post">
                <div class="form-group">
                    <label for="select" class="col-lg-5 control-label">New role</label>

                    <div class="col-lg-6">
                        <select class="form-control" id="select" name="newRole">
                            <c:forEach var="role" items="${roles}">
                                <option>${role.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-info">Change Role</button>
                </div>
            </form>
            <form role="form" action="<c:url value='${pageContext.request.contextPath}/testing/userCategoryDelete' />"
                  method="post">
                <div class="form-group">
                    <button type="submit" class="btn btn-info">Delete User</button>
                </div>
            </form>
        </div>
    </c:if>


    <div>
        <%--@elvariable id="exist_users" type="com.epam.zt.testing.action.getusercategoryaction"--%>
        <c:if test="${exist_users=='no'}">
            <div class="error"><fmt:message key="no_users"/></div>
        </c:if>
        <c:if test="${exist_users=='yes'}">
            <div class="col-lg-12">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Last name</th>
                        <th>Fisrt name</th>
                        <th>Email</th>
                        <th>Login</th>
                        <th>Register date</th>
                    </tr>
                    </thead>
                    <c:forEach var="user" items="users" begin="0" end="9">
                        <tr>
                            <td><a href="<c:url value = '${pageContext.request.contextPath}/testing/startTest?test_id=${user.id}' />">${user.lastName}</a></td>
                            <td><a href="<c:url value = '${pageContext.request.contextPath}/testing/startTest?test_id=${user.id}' />">${user.firstName}</a></td>
                            <td>${user.email}</td>
                            <td>${user.login}</td>
                            <td>${user.registerDate}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                    </tr>
                </table>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
