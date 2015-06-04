<%--@elvariable id="subjectError" type="com.epam.zt.testing.action.testcategoryfindbysubjectaction"--%>
<%--@elvariable id="studentError" type="com.epam.zt.testing.action.testcategoryfindbystudentaction"--%>
<%--@elvariable id="delete" type="com.epam.zt.testing.action.testcategorydeleteaction"--%>
<%--@elvariable id="deleteTest" type="com.epam.zt.testing.action.gettestcategoryaction"--%>
<%--@elvariable id="foundBySubject" type="com.epam.zt.testing.action.testcategoryfindbysubjectaction"--%>
<%--@elvariable id="foundByStudent" type="com.epam.zt.testing.action.testcategoryfindbystudentaction"--%>
<%--@elvariable id="subjectTests" type="com.epam.zt.testing.action.TestCategoryFindBySubjectAction"--%>
<%--@elvariable id="studentTests" type="com.epam.zt.testing.action.TestCategoryFindByStudentAction"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/category.css" rel="stylesheet"/>
    <link href="../../lib/css/testCategory.css" rel="stylesheet"/>
    <link href="../../lib/font-awesome/css/font-awesome.min.css"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../lib/bootstrap/js/tab.js"></script>
    <title>Testing - Tests</title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Tests</h1>
    </div>
    <div class="col-lg-4">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#findBySubject" data-toggle="tab" aria-expanded="true">Search by Subject</a>
            </li>
            <li class=""><a href="#findByStudent" data-toggle="tab" aria-expanded="false">Search by Student</a></li>
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
                        <button type="submit" class="btn btn-primary">Find Test</button>
                    </div>
                </form>
            </div>
            <div class="tab-pane fade" id="findByStudent">
                <form role="search"
                      action="<c:url value='${pageContext.request.contextPath}/testing/testCategoryFindByStudent' />"
                      method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Student Lastname" name="studentLastname"
                               required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Student Firstname" name="studentFirstname"
                               required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Find Test</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="success">${delete}</div>
        <c:if test="${deleteTest==1}">
            <div class="success">Test is deleted!</div>
        </c:if>
        <div class="error">${subjectError} ${studentError}</div>
        <c:if test="${foundBySubject=='not_found'}">
            <div class="error">No tests for this subject!</div>
        </c:if>
        <c:if test="${foundByStudent=='not_found'}">
            <div class="error">No passed tests for this student!</div>
        </c:if>
    </div>
    <c:if test="${foundBySubject == 'found'}">
        <div class="col-lg-12">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Subject</th>
                        <th>Author</th>
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
                        <th>Subject</th>
                        <th>Author</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="test" items="${studentTests}">
                        <tr>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/viewStudentTest?test_id=${test.id}' />">${test.subject.name}</a>
                            </td>
                            <td>${test.author.lastName} ${test.author.firstName}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
