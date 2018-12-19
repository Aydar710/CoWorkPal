<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">CoWorkPal</a>
        </div>

        <div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Заявки</a></li>
                <li><a href="/startProject">Начать проект</a></li>
                <li><a href="/usersProjects">Мои проекты</a></li>
            </ul>
        </div>

    </div>
</nav>
<div class="jumbotron">
    <div class="container">
        <h3>Мои проекты</h3>
    </div>
</div>



<c:set var="projects" scope="request" value="${projects}"/>
<div class="container-fluid col-md-3">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Имя проекта</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projects}" var="project">
            <tr>
                <%--TODO передать id проекта--%>
                <td><a href="/admin_projectInfoTasks">${project.name}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>

<%--<div class="container-fluid">
    <h3>3 различных блока</h3>
    <div class="row">
        <div class="col-md-4" style="background-color: #ff9999">Left</div>
        <div class="col-md-4" style="background-color: #99CCFF">Middle</div>
        <div class="col-md-4" style="background-color: #00CC99">Right</div>
    </div>
</div>--%>
</body>
</html>
