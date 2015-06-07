<%--@elvariable id="delete" type="com.epam.zt.testing.action.groupcategorydeleteaction"--%>
<%--@elvariable id="addSuccess" type="com.epam.zt.testing.action.groupcategoryaddaction"--%>
<%--@elvariable id="found" type="com.epam.zt.testing.action.groupcategoryfindaction"--%>
<%--@elvariable id="foundGroups" type="com.epam.zt.testing.action.groupcategoryfindaction"--%>
<%--@elvariable id="menu" type="com.epam.zt.testing.action.groupcategoryfindaction"--%>
<%--@elvariable id="noDelete" type="com.epam.zt.testing.action.groupcategoryfindaction"--%>
<%--@elvariable id="exist_groups" type="com.epam.zt.testing.action.getgroupcategoryaction"--%>
<%--@elvariable id="groups" type="com.epam.zt.testing.action.GetGroupCategoryAction"--%>
<%--@elvariable id="prev" type="com.epam.zt.testing.action.GetGroupCategoryAction"--%>
<%--@elvariable id="next" type="com.epam.zt.testing.action.GetGroupCategoryAction."--%>
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
    <title><fmt:message key="title"/> - <fmt:message key="groups"/></title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="groups"/></h1>
    </div>
    <div class="col-lg-3">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#find" data-toggle="tab" aria-expanded="true"><fmt:message key="search"/> </a>
            </li>
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
                        <button type="submit" class="btn btn-primary"><fmt:message key="search"/></button>
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
                        <button type="submit" class="btn btn-primary"><fmt:message key="add_group"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-lg-12">
        <div class="success">
            <c:if test="${addSuccess==1}">
                <fmt:message key="group_created"/>
            </c:if>
            <c:if test="${delete==1}">
                <fmt:message key="group_deleted"/>
            </c:if>
        </div>
        <div class="error">
            <c:if test="${found=='not_found'}">
                <fmt:message key="no_group"/>
            </c:if>
        </div>
    </div>

    <c:if test="${found == 'found'}">
        <div class="col-lg-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><fmt:message key="group"/></th>
                    <th><fmt:message key="student_number"/></th>
                    <th><fmt:message key="students"/></th>
                </tr>
                </thead>
                <c:forEach var="foundGroup" items="${foundGroups}">
                    <c:choose>
                        <c:when test="${menu==1}">
                            <tr>
                                <td>${foundGroup.name}</td>
                                <td>${foundGroup.students.size()}</td>
                                <td>
                                    <c:forEach var="student" items="${foundGroup.students}">
                                        ${student.lastName} ${student.firstName} <br/>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>
                                    <a href="<c:url value = '${pageContext.request.contextPath}/testing/groupDetails?group_id=${foundGroup.id}' />">${foundGroup.name}</a>
                                </td>
                                <td>${foundGroup.students.size()}</td>
                                <td>
                                    <c:forEach var="student" items="${foundGroup.students}">
                                        ${student.lastName} ${student.firstName} <br/>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>
            <br/>
        </div>

        <c:if test="${menu==1}">
            <c:choose>
                <c:when test="${noDelete==1}">
                    <div class="error col-lg-12"><fmt:message key="no_delete_group"/></div>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-3">
                        <form role="form"
                              action="<c:url value='${pageContext.request.contextPath}/testing/groupCategoryDelete' />"
                              method="post">
                            <div class="form-group">
                                <button type="submit" class="btn btn-info"><fmt:message key="delete"/></button>
                            </div>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:if>


    <div class="all">
        <c:if test="${exist_groups=='no'}">
            <div class="error"><fmt:message key="no_groups"/></div>
        </c:if>
        <c:if test="${exist_groups=='yes'}">
            <div class="col-lg-12">
                <h3><fmt:message key="all_groups"/></h3>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="group"/></th>
                        <th><fmt:message key="student_number"/></th>
                        <th><fmt:message key="students"/></th>
                    </tr>
                    </thead>
                    <c:forEach var="group" items="${groups}">
                        <tr>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/groupDetails?group_id=${group.id}' />">${group.name}</a>
                            </td>
                            <td>${group.students.size()}</td>
                            <td>
                                <c:forEach var="student" items="${group.students}">
                                    ${student.lastName} ${student.firstName} <br/>
                                </c:forEach>
                            </td>
                    </c:forEach>
                </table>

                <ul class="pager">
                    <c:if test="${prev=='no'}">
                        <li class="previous hidden"><a
                                href="<c:url value='${pageContext.request.contextPath}/testing/pageGroupCategory?page=prev' />">&larr;
                            <fmt:message key="previous"/> </a></li>
                    </c:if>
                    <c:if test="${prev=='yes'}">
                        <li class="previous">
                            <a href="<c:url value='${pageContext.request.contextPath}/testing/pageGroupCategory?page=prev' />">&larr;
                                <fmt:message key="previous"/> </a></li>
                    </c:if>
                    <c:if test="${next=='no'}">
                        <li class="next hidden"><a
                                href="<c:url value='${pageContext.request.contextPath}/testing/pageGroupCategory?page=next' />"><fmt:message
                                key="next"/> &rarr;</a></li>
                    </c:if>
                    <c:if test="${next=='yes'}">
                        <li class="next">
                            <a href="<c:url value='${pageContext.request.contextPath}/testing/pageGroupCategory?page=next' />"><fmt:message
                                    key="next"/> &rarr;</a></li>
                    </c:if>
                </ul>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
