<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Приглашения</title>

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

</head>
<body>
<div class="jumbotron">
    <div class="container">
        <h3>Приглашения</h3>
    </div>
</div>

<c:set var="invites" scope="request" value="${invites}"/>
<div class="container-fluid col-md-3" id="table">
    <table class="table">
        <thead>
        <tr>
            <th>Проект</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${invites}" var="invite">
            <tr>
                <td>${invite.project.name}</td>
                <td>
                    <button type="button" class="btn btn-primary" id="${invite.id}"
                            onclick="accept(${invite.id}, this.id)">Accept
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
function accept(inviteId, elementId) {
    $.ajax({
        url: 'accept',
        type: 'post',
        data: {
            inviteId: inviteId
        }
    }).done(function () {
        document.getElementById(elementId).textContent = "Accepted";
        document.getElementById(elementId).style.backgroundColor = "#00ff00";
    }).fail(function () {
    })
}
</script>
</body>
</html>
