<%--@elvariable id="delete" type="com.epam.zt.testing.action.groupcategorydeleteaction"--%>
<%--@elvariable id="addSuccess" type="com.epam.zt.testing.action.groupcategoryaddaction"--%>
<%--@elvariable id="found" type="com.epam.zt.testing.action.groupcategoryfindaction"--%>
<%--@elvariable id="foundGroup" type="com.epam.zt.testing.model.Group"--%>
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
    <script src="../../lib/bootstrap/js/tab.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="groups"/> </title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="groups"/></h1>
    </div>
    <div class="col-lg-3">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#find" data-toggle="tab" aria-expanded="true"><fmt:message key="search"/> </a></li>
            <li class=""><a href="#add" data-toggle="tab" aria-expanded="false"><fmt:message key="add_group"/> </a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade active in" id="find">
                <form role="search"
                      action="<c:url value='${pageContext.request.contextPath}/testing/groupCategoryFind' />"
                      method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Group Name" name="groupName" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><fmt:message key="search"/> </button>
                    </div>
                </form>
            </div>
            <div class="tab-pane fade" id="add">
                <form role="search"
                      action="<c:url value='${pageContext.request.contextPath}/testing/groupCategoryAdd' />"
                      method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Group Name" name="newGroup" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><fmt:message key="add_group"/> </button>
                    </div>
                </form>
            </div>
        </div>
        <div class="success">
            ${addSuccess}
            ${delete}
        </div>
        <c:if test="${found=='not_found'}">
            <div class="error"><fmt:message key="no_group"/> </div>
        </c:if>
    </div>
    <c:if test="${found == 'found'}">
        <div class="col-lg-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><fmt:message key="group"/> </th>
                    <th><fmt:message key="student_number"/> </th>
                    <th><fmt:message key="students"/> </th>
                </tr>
                </thead>
                <tr>
                    <td>${foundGroup.name}</td>
                    <td>${foundGroup.students.size()}</td>
                    <td>
                        <c:forEach var="student" items="${foundGroup.students}">
                            ${student.lastName} ${student.firstName} <br/>
                        </c:forEach>
                    </td>
                </tr>
            </table>
            <br/>
            <c:if test="${foundGroup.students.size()>0}">
                <div class="error"><fmt:message key="no_delete_group"/> </div>
            </c:if>
        </div>
        <c:if test="${foundGroup.students.size()==0}">
            <div class="col-lg-3">
                <form role="form"
                      action="<c:url value='${pageContext.request.contextPath}/testing/groupCategoryDelete' />"
                      method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-info"><fmt:message key="delete"/> </button>
                    </div>
                </form>
            </div>
        </c:if>
    </c:if>
</div>
</body>
</html>
