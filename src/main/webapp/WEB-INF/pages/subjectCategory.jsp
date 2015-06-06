<%--@elvariable id="deleteError" type="com.epam.zt.testing.action.subjectcategorydeleteaction"--%>
<%--@elvariable id="tutorError" type="com.epam.zt.testing.action.subjectcategoryaddaction"--%>
<%--@elvariable id="delete" type="com.epam.zt.testing.action.subjectcategorydeleteaction"--%>
<%--@elvariable id="addSuccess" type="com.epam.zt.testing.action.getsubjectcategoryaction"--%>
<%--@elvariable id="found" type="com.epam.zt.testing.action.subjectcategoryfindaction"--%>
<%--@elvariable id="foundSubjects" type="com.epam.zt.testing.model.Subject"--%>
<%--@elvariable id="menu" type="com.epam.zt.testing.action.subjectcategoryfindaction"--%>
<%--@elvariable id="exist_subjects" type="com.epam.zt.testing.action.getsubjectcategoryaction"--%>
<%--@elvariable id="subjects" type="com.epam.zt.testing.action.GetSubjectCategoryAction"--%>
<%--@elvariable id="prev" type="com.epam.zt.testing.action.GetSubjectCategoryAction"--%>
<%--@elvariable id="next" type="com.epam.zt.testing.action.GetSubjectCategoryAction"--%>
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
    <title><fmt:message key="title"/> - <fmt:message key="subjects"/></title>
</head>
<body>
<c:import url="adminMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="subjects"/></h1>
    </div>
    <div class="col-lg-3">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#find" data-toggle="tab" aria-expanded="true"><fmt:message key="search"/> </a>
            </li>
            <li class=""><a href="#add" data-toggle="tab" aria-expanded="false"><fmt:message key="add_subject"/> </a>
            </li>
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
                        <button type="submit" class="btn btn-primary"><fmt:message key="search"/></button>
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
                        <input type="text" class="form-control" placeholder="Tutor Lastname" name="tutorLastname"
                               required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Tutor Firstname" name="tutorFirstname"
                               required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><fmt:message key="add_subject"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="col-lg-12">
        <div class="success">
            <c:if test="${addSuccess==1}">
                <fmt:message key="subject_created"/>
            </c:if>
            <c:if test="${delete==1}">
                <fmt:message key="subject_deleted"/>
            </c:if>
        </div>
        <div class="error">
            <c:if test="${deleteError==1}">
                <fmt:message key="delete_subject_error"/>
            </c:if>
            <c:if test="${tutorError==1}">
                <fmt:message key="no_tutor"/>
            </c:if>
            <c:if test="${found=='not_found'}">
                <fmt:message key="no_subject"/>
            </c:if>
        </div>
    </div>

    <c:if test="${found == 'found'}">
        <div class="col-lg-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><fmt:message key="subject"/></th>
                    <th><fmt:message key="tutor"/></th>
                </tr>
                </thead>
                <c:forEach var="foundSubject" items="${foundSubjects}">
                    <c:choose>
                        <c:when test="${menu==1}">
                            <tr>
                                <td>${foundSubject.name}</td>
                                <td>${foundSubject.tutor.lastName} ${foundSubject.tutor.firstName}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>
                                    <a href="<c:url value = '${pageContext.request.contextPath}/testing/subjectDetails?subject_id=${foundSubject.id}' />">${foundSubject.name}</a>
                                </td>
                                <td>${foundSubject.tutor.lastName} ${foundSubject.tutor.firstName}</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>

            <c:if test="${menu==1}">
                <form role="form"
                      action="<c:url value='${pageContext.request.contextPath}/testing/subjectCategoryDelete' />"
                      method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-info"><fmt:message key="delete"/></button>
                    </div>
                </form>
            </c:if>
        </div>
    </c:if>


    <div class="all">
        <c:if test="${exist_subjects=='no'}">
            <div class="error"><fmt:message key="no_subjects"/></div>
        </c:if>
        <c:if test="${exist_subjects=='yes'}">
            <div class="col-lg-12">
                <h3><fmt:message key="all_subjects"/></h3>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="subject"/></th>
                        <th><fmt:message key="tutor"/></th>
                    </tr>
                    </thead>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td>
                                <a href="<c:url value = '${pageContext.request.contextPath}/testing/subjectDetails?subject_id=${subject.id}' />">${subject.name}</a>
                            </td>
                            <td>${subject.tutor.lastName} ${subject.tutor.firstName}</td>
                        </tr>
                    </c:forEach>
                </table>

                <ul class="pager">
                    <c:if test="${prev=='no'}">
                        <li class="previous hidden"><a
                                href="<c:url value='${pageContext.request.contextPath}/testing/pageSubjectCategory?page=prev' />">&larr;
                            <fmt:message key="previous"/> </a></li>
                    </c:if>
                    <c:if test="${prev=='yes'}">
                        <li class="previous">
                            <a href="<c:url value='${pageContext.request.contextPath}/testing/pageSubjectCategory?page=prev' />">&larr;
                                <fmt:message key="previous"/> </a></li>
                    </c:if>
                    <c:if test="${next=='no'}">
                        <li class="next hidden"><a
                                href="<c:url value='${pageContext.request.contextPath}/testing/pageSubjectCategory?page=next' />"><fmt:message
                                key="next"/> &rarr;</a></li>
                    </c:if>
                    <c:if test="${next=='yes'}">
                        <li class="next">
                            <a href="<c:url value='${pageContext.request.contextPath}/testing/pageSubjectCategory?page=next' />"><fmt:message
                                    key="next"/> &rarr;</a></li>
                    </c:if>
                </ul>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
