<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Начать проект</title>
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
            </ul>
        </div>

    </div>
</nav>
<br><br><br><br>
    <form role="form" method="post" class="col-md-3">
        <div class="form-group">
            <label for="projectName">Имя проекта</label>
            <input type="text" class="form-control" name="projectName" placeholder="Имя проекта" id="projectName">
        </div>
        <input type="submit" class="btn btn-success" value="Начать проект">
    </form>
</body>
</html>
