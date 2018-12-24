<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Администраторы</title>
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
                <li><a href="/tasks">Задачи</a></li>
                <li><a href="/members">Участники</a></li>
                <li><a href="/admins">Администраторы</a></li>
                <li><a href="/usersProjects">Мои проекты</a></li>
            </ul>
        </div>
    </div>
</nav>

<br><br><br><br><br>
<c:set var="role" scope="request" value="${role}"/>
<c:if test="${role eq 'Admin'}">
    <div class="container">
        <form role="form" method="post" class="col-md-3">
            <div class="form-group">
                <label for="admin">Добавить менеджера</label>
                <input type="text" class="form-control" name="adminsEmail" placeholder="Введите Email" id="admin">
            </div>
            <input type="submit" class="btn btn-primary" value="Добавить менеджера в проект">
        </form>
    </div>
</c:if>


<c:set var="admins" scope="request" value="${admins}"/>
<div class="container-fluid col-md-3" id="table">
    <table class="table">
        <thead>
        <tr>
            <th>Менеджеры</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${admins}" var="admin">
            <tr>
                <td>${admin.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
