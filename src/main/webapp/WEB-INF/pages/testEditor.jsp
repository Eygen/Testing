<%--@elvariable id="firstAnswer" type="com.epam.zt.testing.action.gettesteditoraction"--%>
<%--@elvariable id="secondAnswer" type="com.epam.zt.testing.action.gettesteditoraction"--%>
<%--@elvariable id="thirdAnswer" type="com.epam.zt.testing.action.gettesteditoraction"--%>
<%--@elvariable id="fourthAnswer" type="com.epam.zt.testing.action.gettesteditoraction"--%>
<%--@elvariable id="editQuestion" type="com.epam.zt.testing.model.question"--%>
<%--@elvariable id="test" type="com.epam.zt.testing.model.test"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setBundle basename="messages"/>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/createTest.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title><fmt:message key="title"/> - <fmt:message key="test_editor"/></title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1><fmt:message key="test_editor"/></h1>

        <p class="lead">${test.subject.name}</p>
    </div>
    <form class="form col-lg-6" role="form"
          action="<c:url value='${pageContext.request.contextPath}/testing/testEditor' />"
          method="post">
        <div class="form-group">
            <label for="textArea" class="control-label">Question</label>

            <div>
                <c:choose>
                    <c:when test="${editQuestion!=null}">
                <textarea class="form-control" rows="3" id="textArea" name="question"
                          required>${editQuestion.description}</textarea>
                    </c:when>
                    <c:otherwise>
                        <textarea class="form-control" rows="3" id="textArea" name="question"
                                  required></textarea>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div style="color: red;"><fmt:message key="answer_warning"/></div>
        <div class="form-group has-success">
            <label for="firstAnswer" class="control-label"><fmt:message key="answer"/> </label>

            <div>
                <input type="text" class="form-control" id="firstAnswer" name="firstAnswer" value="${firstAnswer}"
                       required>
            </div>
        </div>
        <div class="form-group has-error">
            <label for="secondAnswer" class="control-label"><fmt:message key="answer"/> </label>

            <div>
                <input type="text" class="form-control" id="secondAnswer" name="secondAnswer" value="${secondAnswer}"
                       required>
            </div>
        </div>
        <div class="form-group has-error">
            <label for="thirdAnswer" class="control-label"><fmt:message key="answer"/></label>

            <div>
                <input type="text" class="form-control" id="thirdAnswer" name="thirdAnswer" value="${thirdAnswer}"
                       required>
            </div>
        </div>
        <div class="form-group has-error">
            <label for="fourthAnswer" class="control-label"><fmt:message key="answer"/></label>

            <div>
                <input type="text" class="form-control" id="fourthAnswer" name="fourthAnswer" value="${fourthAnswer}"
                       required>
            </div>
        </div>
        <c:choose>
            <c:when test="${editQuestion==null}">
                <div class="form-group">
                    <button type="reset" class="btn btn-danger"><fmt:message key="cancel"/></button>
                    <button type="submit" class="btn btn-success"><fmt:message key="add_question"/></button>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group">
                    <button type="reset" class="btn btn-danger"><fmt:message key="cancel"/></button>
                    <button type="submit" class="btn btn-success"><fmt:message key="edit"/></button>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>
</body>
</html>
