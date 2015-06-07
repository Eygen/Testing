<%--@elvariable id="subjectError" type="com.epam.zt.testing.action.testcategoryfindbysubjectaction"--%>
<%--@elvariable id="studentError" type="com.epam.zt.testing.action.testcategoryfindbystudentaction"--%>
<%--@elvariable id="delete" type="com.epam.zt.testing.action.testcategorydeleteaction"--%>
<%--@elvariable id="deleteTest" type="com.epam.zt.testing.action.gettestcategoryaction"--%>
<%--@elvariable id="foundBySubject" type="com.epam.zt.testing.action.testcategoryfindbysubjectaction"--%>
<%--@elvariable id="foundByStudent" type="com.epam.zt.testing.action.testcategoryfindbystudentaction"--%>
<%--@elvariable id="subjectTests" type="com.epam.zt.testing.action.TestCategoryFindBySubjectAction"--%>
<%--@elvariable id="studentTests" type="com.epam.zt.testing.action.TestCategoryFindByStudentAction"--%>
<%--@elvariable id="exist_tests" type="com.epam.zt.testing.action.gettestcategoryaction"--%>
<%--@elvariable id="tests" type="com.epam.zt.testing.action.GetTestCategoryAction"--%>
<%--@elvariable id="prev" type="com.epam.zt.testing.action.GetTestCategoryAction"--%>
<%--@elvariable id="next" type="com.epam.zt.testing.action.GetTestCategoryAction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/category.css" rel="stylesheet"/>
    <link href="../../lib/css/main.css" rel="stylesheet"/>
    <link href="../../lib/font-awesome/css/font-awesome.min.css"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../lib/bootstrap/js/tab.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="tests"/></title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="tests"/></h1>
    </div>
    <div class="col-lg-4">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#findBySubject" data-toggle="tab" aria-expanded="true"><fmt:message
                    key="by_subject"/> </a>
            </li>
            <li class=""><a href="#findByStudent" data-toggle="tab" aria-expanded="false"><fmt:message
                    key="by_student"/> </a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade active in" id="findBySubject">
                <form role="search"
                      action="<c:url value='${pageContext.request.contextPath}/testing/testCategoryFindBySubject' />"
                      method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Subject Name" name="subjectName" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><fmt:message key="search"/></button>
                    </div>
                </form>
            </div>
            <div class="tab-pane fade" id="findByStudent">
                <form role="search"
                      action="<c:url value='${pageContext.request.contextPath}/testing/testCategoryFindByStudent' />"
                      method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Student Lastname" name="studentLastname">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Student Firstname" name="studentFirstname">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><fmt:message key="search"/></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="success">
            <c:if test="${delete==1}">
                <fmt:message key="test_deleted"/>
            </c:if>
        </div>
        <div class="error">
            <c:if test="${subjectError==1}">
                <fmt:message key="no_subject"/>
            </c:if>
            <c:if test="${studentError==1}">
                <fmt:message key="no_student"/>
            </c:if>
            <c:if test="${foundByStudent=='empty'}">
                <fmt:message key="empty_entries"/>
            </c:if>
            <c:if test="${foundBySubject=='not_found'}">
                <fmt:message key="no_subject_tests"/>
            </c:if>
            <c:if test="${foundByStudent=='not_found'}">
                <fmt:message key="no_student_tests"/>
            </c:if>
        </div>
    </div>

    <c:if test="${foundBySubject == 'found'}">
        <div class="col-lg-12">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th><fmt:message key="subject"/></th>
                        <th><fmt:message key="author"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="test" items="${subjectTests}">
                        <tr>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/viewTest?test_id=${test.id}' />">${test.subject.name}</a>
                            </td>
                            <td>${test.author.lastName} ${test.author.firstName}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>

    <c:if test="${foundByStudent == 'found'}">
        <div class="col-lg-12">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th><fmt:message key="student"/></th>
                        <th><fmt:message key="subject"/></th>
                        <th><fmt:message key="mark"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="test" items="${studentTests}">
                        <tr>
                            <td>${test.student.lastName} ${test.student.firstName}</td>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/viewStudentTest?test_id=${test.id}&student_id=${test.student.id}' />">${test.subject.name}</a>
                            </td>
                            <td>${test.mark.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>


    <div class="all">
        <c:if test="${exist_tests=='no'}">
            <div class="error"><fmt:message key="no_tests"/></div>
        </c:if>
        <c:if test="${exist_tests=='yes'}">
            <div class="col-lg-12">
                <h3><fmt:message key="all_tests"/></h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th><fmt:message key="subject"/></th>
                        <th><fmt:message key="author"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="test" items="${tests}">
                        <tr>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/viewTest?test_id=${test.id}' />">${test.subject.name}</a>
                            </td>
                            <td>${test.author.lastName} ${test.author.firstName}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <ul class="pager">
                    <c:if test="${prev=='no'}">
                        <li class="previous hidden"><a
                                href="<c:url value='${pageContext.request.contextPath}/testing/pageTestCategory?page=prev' />">&larr;
                            <fmt:message key="previous"/> </a></li>
                    </c:if>
                    <c:if test="${prev=='yes'}">
                        <li class="previous">
                            <a href="<c:url value='${pageContext.request.contextPath}/testing/pageTestCategory?page=prev' />">&larr;
                                <fmt:message key="previous"/> </a></li>
                    </c:if>
                    <c:if test="${next=='no'}">
                        <li class="next hidden"><a
                                href="<c:url value='${pageContext.request.contextPath}/testing/pageTestCategory?page=next' />"><fmt:message
                                key="next"/> &rarr;</a></li>
                    </c:if>
                    <c:if test="${next=='yes'}">
                        <li class="next">
                            <a href="<c:url value='${pageContext.request.contextPath}/testing/pageTestCategory?page=next' />"><fmt:message
                                    key="next"/> &rarr;</a></li>
                    </c:if>
                </ul>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
