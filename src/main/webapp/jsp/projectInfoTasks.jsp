<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Информация о проекте</title>

    <head>
        <title>Title</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
              integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
              crossorigin="anonymous">

        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
    </head>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">CoWorkPal</a>
        </div>

        <div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/tasks">Задачи</a></li>
                <li><a href="/members">Участники</a></li>
                <li><a href="/admins">Администраторы</a></li>
                <li><a href="/usersProjects">Мои проекты</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <h3>${projectName}</h3>
    </div>
</div>

<c:set var="role" scope="request" value="${role}"/>
<c:if test="${role eq 'Admin'}">
    <div class="container">
        <form role="form" method="post" class="col-md-3">
            <div class="form-group">
                <label for="task">Задача</label>
                <input type="text" class="form-control" name="task" placeholder="Введите задачу" id="task">
            </div>
            <input type="submit" class="btn btn-primary" value="Добавить задачу">
        </form>
    </div>
</c:if>


<c:set var="tasks" scope="request" value="${tasks}"/>
<div class="container-fluid col-md-3" id="table">
    <table class="table">
        <thead>
        <tr>
            <th>Задачи</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tasks}" var="task">
            <tr>
                <td>${task.task}</td>
                <td>
                    <button type="button" class="btn btn-success" id="${task.id}"
                            onclick="accept(${task.id}, this.id)">Complete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function accept(taskId, elementId) {
        $.ajax({
            url: 'done',
            type: 'post',
            data: {
                taskId: taskId
            }
        }).done(function () {
            //document.getElementById(elementId).style.backgroundColor ='red';
            document.getElementById(elementId).textContent = 'Done'

        }).fail(function () {
            alert("fail");
        })
    }

</script>

</body>
</html>
