<%--@elvariable id="roleChange" type="com.epam.zt.testing.action.usercategorychangeroleaction"--%>
<%--@elvariable id="delete" type="com.epam.zt.testing.action.usercategorydeleteaction"--%>
<%--@elvariable id="found" type="com.epam.zt.testing.action.usercategoryfindaction"--%>
<%--@elvariable id="roles" type="com.epam.zt.testing.action.GetUserCategoryAction"--%>
<%--@elvariable id="foundUsers" type="com.epam.zt.testing.model.User"--%>
<%--@elvariable id="firstrow" type="com.epam.zt.testing.action.getusercategoryaction"--%>
<%--@elvariable id="exist_users" type="com.epam.zt.testing.action.getusercategoryaction"--%>
<%--@elvariable id="rowcount" type="com.epam.zt.testing.action.GetUserCategoryAction"--%>
<%--@elvariable id="prev" type="com.epam.zt.testing.action.getusercategoryaction"--%>
<%--@elvariable id="next" type="com.epam.zt.testing.action.GetUserCategoryAction"--%>
<%--@elvariable id="users" type="com.epam.zt.testing.action.GetUserCategoryAction"--%>
<%--@elvariable id="menu" type="com.epam.zt.testing.action.usercategoryfindaction"--%>
<%--@elvariable id="roleError" type="com.epam.zt.testing.action.usercategorychangeroleaction"--%>
<%--@elvariable id="deleteError" type="com.epam.zt.testing.action.usercategorydeleteaction"--%>
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
    <title><fmt:message key="title"/> - <fmt:message key="users"/></title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="users"/></h1>
    </div>
    <div class="col-lg-3">
        <form role="search" action="<c:url value='${pageContext.request.contextPath}/testing/userCategoryFind' />"
              method="post">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Last Name" name="last_name">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="First Name" name="first_name">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><fmt:message key="search"/></button>
            </div>
        </form>
    </div>

    <div class="col-lg-12">
        <div class="success">
            <c:if test="${roleChange==1}">
                <fmt:message key="role_changed"/>
            </c:if>
            <c:if test="${delete==1}">
                <fmt:message key="user_deleted"/>
            </c:if>
        </div>
        <div class="error">
            <c:if test="${deleteError==1}">
                <fmt:message key="delete_user_error"/>
            </c:if>
            <c:if test="${roleError==1}">
                <fmt:message key="role_user_error"/>
            </c:if>
            <c:if test="${found=='not_found'}">
                <fmt:message key="no_user"/>
            </c:if>
            <c:if test="${found=='empty'}">
                <fmt:message key="empty_entries"/>
            </c:if>
        </div>
    </div>

    <c:if test="${found == 'found'}">
        <div class="col-lg-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><fmt:message key="last_name"/></th>
                    <th><fmt:message key="first_name"/></th>
                    <th><fmt:message key="email"/></th>
                    <th><fmt:message key="login"/></th>
                    <th><fmt:message key="register_date"/></th>
                    <th><fmt:message key="role"/></th>
                </tr>
                </thead>
                <c:forEach var="someone" items="${foundUsers}">
                    <c:choose>
                        <c:when test="${menu==1}">
                            <tr>
                                <td>${someone.lastName}</td>
                                <td>${someone.firstName}</td>
                                <td>${someone.email}</td>
                                <td>${someone.login}</td>
                                <td>${someone.registerDate}</td>
                                <td>${someone.role.name}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>
                                    <a href="<c:url value = '${pageContext.request.contextPath}/testing/personDetails?person_id=${someone.id}' />">${someone.lastName}</a>
                                </td>
                                <td>
                                    <a href="<c:url value = '${pageContext.request.contextPath}/testing/personDetails?person_id=${someone.id}' />">${someone.firstName}</a>
                                </td>
                                <td>${someone.email}</td>
                                <td>${someone.login}</td>
                                <td>${someone.registerDate}</td>
                                <td>${someone.role.name}</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>
        </div>

        <c:if test="${menu==1}">
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
                        <button type="submit" class="btn btn-info"><fmt:message key="change_role"/></button>
                    </div>
                </form>
                <form role="form"
                      action="<c:url value='${pageContext.request.contextPath}/testing/userCategoryDelete' />"
                      method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-info"><fmt:message key="delete"/></button>
                    </div>
                </form>
            </div>
        </c:if>
    </c:if>


    <div class="all">
        <c:if test="${exist_users=='no'}">
            <div class="error"><fmt:message key="no_users"/></div>
        </c:if>
        <c:if test="${exist_users=='yes'}">
            <div class="col-lg-12">
                <h3><fmt:message key="all_users"/></h3>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="last_name"/></th>
                        <th><fmt:message key="first_name"/></th>
                        <th><fmt:message key="email"/></th>
                        <th><fmt:message key="login"/></th>
                        <th><fmt:message key="register_date"/></th>
                        <th><fmt:message key="role"/></th>
                    </tr>
                    </thead>
                    <c:forEach var="person" items="${users}">
                        <tr>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/personDetails?person_id=${person.id}' />">${person.lastName}</a>
                            </td>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/personDetails?person_id=${person.id}' />">${person.firstName}</a>
                            </td>
                            <td>${person.email}</td>
                            <td>${person.login}</td>
                            <td>${person.registerDate}</td>
                            <td>${person.role.name}</td>
                        </tr>
                    </c:forEach>
                </table>

                <ul class="pager">
                    <c:if test="${prev=='no'}">
                        <li class="previous hidden"><a
                                href="<c:url value='${pageContext.request.contextPath}/testing/pageUserCategory?page=prev' />">&larr;
                            <fmt:message key="previous"/> </a></li>
                    </c:if>
                    <c:if test="${prev=='yes'}">
                        <li class="previous">
                            <a href="<c:url value='${pageContext.request.contextPath}/testing/pageUserCategory?page=prev' />">&larr;
                                <fmt:message key="previous"/> </a></li>
                    </c:if>
                    <c:if test="${next=='no'}">
                        <li class="next hidden"><a
                                href="<c:url value='${pageContext.request.contextPath}/testing/pageUserCategory?page=next' />"><fmt:message
                                key="next"/> &rarr;</a></li>
                    </c:if>
                    <c:if test="${next=='yes'}">
                        <li class="next">
                            <a href="<c:url value='${pageContext.request.contextPath}/testing/pageUserCategory?page=next' />"><fmt:message
                                    key="next"/> &rarr;</a></li>
                    </c:if>
                </ul>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
