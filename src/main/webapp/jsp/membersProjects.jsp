<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Проекты</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

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
                <li><a href="/invite">Заявки</a></li>
                <li><a href="/startProject">Начать проект</a></li>
                <li><a href="/usersProjects">Мои проекты</a></li>
                <li><a href="/membersProjects">Проекты</a></li>
            </ul>
        </div>

    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <h3>Проекты</h3>
    </div>
</div>

<c:set var="projects" scope="request" value="${membersProjects}"/>
<div class="container-fluid col-md-3">
    <table class="table table-hover">
        <thead>
        <tr>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projects}" var="project">
            <tr>
                <td><a onclick="onClick(${project.id})" href="/projectInfoTasks">${project.name}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function onClick(projectID) {
        $.ajax({
            url: 'addProjectId',
            type: 'post',
            data: {
                projectId: projectID
            }
        })
    }
</script>

</body>
</html>
