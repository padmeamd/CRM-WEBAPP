<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Kelly+Slab&display=swap" rel="stylesheet">
    <title>login</title>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy' });
        } );
    </script>
</head>
<body>


<main class="main">
    <h1 class="main-heading-title"> система управления студентами и их успеваемостью
    </h1>
    <div class="beginning">
        <h2>
            введите логин, роль и пароль
        </h2>
        <br>
        <form action="/login" method="post">
            <p>Логин:  </p>

            <input name="login" type="text">
            <br>
            <br>
            <p>Пароль:  </p>

            <input name="password" type="text">
            <br>
            <br>
            <p>Роль: </p>
            <select name="role" >
                <option value="1" style="font-family: Sylfaen; font-size: 10pt;"> Администратор </option>

                <option value="3" style="font-family: Sylfaen; font-size: 10pt;"> Учитель </option>

                <option value="2" style="font-family: Sylfaen; font-size: 10pt;"> Студент </option>

            </select>
            <br>
            <br>
            <br>
            <input type="submit" value=" войти" class="other">

            <c:if test="${message eq 1}">
                <h4>Пожалуйста, заполните все поля!</h4>
            </c:if>
            <c:if test="${message eq 2}">
                <h4>Такого пользователя не существует.</h4>
            </c:if>
        </form>

    </div>


</main>


</body>
</html>