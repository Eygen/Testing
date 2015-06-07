<%--@elvariable id="block" type="com.epam.zt.testing.action.blocktestaction"--%>
<%--@elvariable id="assignError" type="com.epam.zt.testing.action.postassigntestaction"--%>
<%--@elvariable id="test" type="com.epam.zt.testing.model.Test"--%>
<%--@elvariable id="groups" type="com.epam.zt.testing.action.getassigntestaction"--%>
<%--@elvariable id="success" type="com.epam.zt.testing.action.GetAssignTestAction"--%>
<%--@elvariable id="found" type="com.epam.zt.testing.action.findstudentforassignaction"--%>
<%--@elvariable id="foundStudents" type="com.epam.zt.testing.action.findstudentforassignaction"--%>
<%--@elvariable id="testAssign" type="com.epam.zt.testing.action.FindStudentForAssignAction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/assignTest.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/tab.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title><fmt:message key="title"/> - <fmt:message key="assign_test"/></title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="assign_test"/></h1>

        <p class="lead">${test.subject.name}</p>
    </div>
    <div class="col-lg-4">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#group" data-toggle="tab" aria-expanded="true"><fmt:message
                    key="by_group"/> </a></li>
            <li class=""><a href="#student" data-toggle="tab" aria-expanded="false"><fmt:message key="by_student"/> </a>
            </li>
        </ul>
        <div id="myTabContent" class="tab-content col-lg-9">
            <div class="tab-pane fade active in" id="group">
                <form role="search"
                      action="<c:url value='${pageContext.request.contextPath}/testing/assignGroupTest' />"
                      method="post">
                    <div class="form-group">
                        <label for="select"><fmt:message key="group_assign"/> </label>
                        <select class="form-control" id="select" name="group">
                            <c:forEach var="group" items="${groups}">
                                <option>${group.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><fmt:message key="assign_test"/></button>
                    </div>
                </form>
            </div>

            <div class="tab-pane fade" id="student">
                <form role="form"
                      action="<c:url value='${pageContext.request.contextPath}/testing/findStudent' />"
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
        </div>
    </div>

    <div class="col-lg-12">
        <c:if test="${success==1}">
            <div class="success"><fmt:message key="test_assigned"/></div>
        </c:if>
        <div class="error">
            <c:if test="${found=='empty'}">
                <fmt:message key="empty_entries"/>
            </c:if>
            <c:if test="${found=='no_user'}">
                <fmt:message key="no_user"/>
            </c:if>
        </div>
        <div class="success">${block}</div>
        <div class="error">${assignError}</div>
    </div>

    <c:if test="${found=='found'}">
        <div class="col-lg-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><fmt:message key="last_name"/></th>
                    <th><fmt:message key="first_name"/></th>
                    <th><fmt:message key="email"/></th>
                    <th><fmt:message key="login"/></th>
                    <th><fmt:message key="group"/></th>
                </tr>
                </thead>
                <c:choose>
                    <c:when test="${menu==1}">
                        <c:forEach var="foundStudent" items="${foundStudents}">
                            <tr>
                                <td>${foundStudent.lastName}</td>
                                <td>${foundStudent.firstName}</td>
                                <td>${foundStudent.email}</td>
                                <td>${foundStudent.login}</td>
                                <td>${foundStudent.group.name}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="foundStudent" items="${foundStudents}">
                            <tr>
                                <td><a href="<c:url value = '${pageContext.request.contextPath}/testing/studentDetails?student_id=${foundStudent.id}' />">${foundStudent.lastName}</a></td>
                                <td><a href="<c:url value = '${pageContext.request.contextPath}/testing/studentDetails?student_id=${foundStudent.id}' />">${foundStudent.firstName}</a></td>
                                <td>${foundStudent.email}</td>
                                <td>${foundStudent.login}</td>
                                <td>${foundStudent.group.name}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
                <%--@elvariable id="menu" type="com.epam.zt.testing.action.findstudentforassignaction"--%>
            <c:if test="${menu==1}">
                <c:if test="${testAssign=='not_assign'}">
                    <form role="form"
                          action="<c:url value='${pageContext.request.contextPath}/testing/assignStudentTest' />"
                          method="post">
                        <div class="form-group" style="margin-left: 10px">
                            <button type="submit" class="btn btn-primary"><fmt:message key="assign_test"/></button>
                        </div>
                    </form>
                </c:if>
                <c:if test="${testAssign=='assign'}">
                    <form role="form"
                          action="<c:url value='${pageContext.request.contextPath}/testing/blockTest' />"
                          method="post">
                        <div class="form-group" style="margin-left: 10px">
                            <button type="submit" class="btn btn-primary"><fmt:message key="block_test"/></button>
                        </div>
                    </form>
                </c:if>
            </c:if>
        </div>
    </c:if>
</div>
</body>
</html>
