<%--@elvariable id="tutorError" type="com.epam.zt.testing.action.subjectcategoryaddaction"--%>
<%--@elvariable id="delete" type="com.epam.zt.testing.action.subjectcategorydeleteaction"--%>
<%--@elvariable id="addSuccess" type="com.epam.zt.testing.action.getsubjectcategoryaddaction"--%>
<%--@elvariable id="found" type="com.epam.zt.testing.action.subjectcategoryfindaction"--%>
<%--@elvariable id="foundSubject" type="com.epam.zt.testing.model.Subject"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/category.css" rel="stylesheet"/>
    <link href="../../lib/css/main.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/tab.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title>Testing - Subjects</title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Subjects</h1>
    </div>
    <div class="col-lg-3">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#find" data-toggle="tab" aria-expanded="true">Search</a></li>
            <li class=""><a href="#add" data-toggle="tab" aria-expanded="false">Add subject</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade active in" id="find">
                <form role="search"
                      action="<c:url value='${pageContext.request.contextPath}/testing/subjectCategoryFind' />"
                      method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Subject Name" name="subjectName" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Find Subject</button>
                    </div>
                </form>
            </div>
            <div class="tab-pane fade" id="add">
                <form role="search"
                      action="<c:url value='${pageContext.request.contextPath}/testing/subjectCategoryAdd' />"
                      method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Subject Name" name="newSubject" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Tutor Lastname" name="tutorLastname" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Tutor Firstname" name="tutorFirstname" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Add Subject</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="success">
            <c:if test="${addSuccess==1}">
                Subject successfully created!
            </c:if>

            ${delete}
        </div>
        <div class="error">${tutorError}</div>
        <c:if test="${found=='not_found'}">
            <div class="error">No such subject!</div>
        </c:if>
    </div>
    <c:if test="${found == 'found'}">
        <div class="col-lg-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Subject</th>
                    <th>Tutor</th>
                </tr>
                </thead>
                <tr>
                    <td>${foundSubject.name}</td>
                    <td>${foundSubject.tutor.lastName} ${foundSubject.tutor.firstName}</td>
                </tr>
            </table>
            <form role="form"
                  action="<c:url value='${pageContext.request.contextPath}/testing/subjectCategoryDelete' />"
                  method="post">
                <div class="form-group">
                    <button type="submit" class="btn btn-info">Delete Subject</button>
                </div>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>
