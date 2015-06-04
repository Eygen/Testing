<%--@elvariable id="test" type="com.epam.zt.testing.model.test"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../lib/bootstrap/css/cerulean-bootstrap.min.css" rel="stylesheet"/>
    <link href="../../lib/css/createTest.css" rel="stylesheet"/>
    <script src="../../lib/bootstrap/jquery-1.11.3.min.js"></script>
    <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    <title>Testing - Test Editor</title>
</head>
<body>
<c:import url="tutorMainMenu.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>Test Editor</h1>

        <p class="lead">${test.subject.name}</p>
    </div>
    <form class="form col-lg-6" role="form"
          action="<c:url value='${pageContext.request.contextPath}/testing/testEditor' />"
          method="post">
        <div class="form-group">
            <label for="textArea" class="control-label">Question</label>

            <div>
                <textarea class="form-control" rows="3" id="textArea" name="question" required></textarea>
            </div>
        </div>
        <div style="color: red;">Первый ответ должен быть верным!</div>
        <div class="form-group has-success">
            <label for="firstAnswer" class="control-label">First answer</label>

            <div>
                <input type="text" class="form-control" id="firstAnswer" name="firstAnswer" required>
            </div>
        </div>
        <div class="form-group has-error">
            <label for="secondAnswer" class="control-label">Second answer</label>

            <div>
                <input type="text" class="form-control" id="secondAnswer" name="secondAnswer" required>
            </div>
        </div>
        <div class="form-group has-error">
            <label for="thirdAnswer" class="control-label">Third answer</label>

            <div>
                <input type="text" class="form-control" id="thirdAnswer" name="thirdAnswer" required>
            </div>
        </div>
        <div class="form-group has-error">
            <label for="fourthAnswer" class="control-label">Fourth answer</label>

            <div>
                <input type="text" class="form-control" id="fourthAnswer" name="fourthAnswer" required>
            </div>
        </div>
        <div class="form-group">
            <button type="reset" class="btn btn-danger">Reset</button>
            <button type="submit" class="btn btn-success">Add Question</button>
        </div>
    </form>
    <%--<a href="<c:url value = '${pageContext.request.contextPath}/testing/tutorHome' />">
        <button class="btn btn-info btn-lg">Finish</button>
    </a>--%>
</div>
</body>
</html>
