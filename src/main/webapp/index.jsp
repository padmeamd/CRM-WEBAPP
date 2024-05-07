<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kelly+Slab&display=swap" rel="stylesheet">

    <title>Title</title>
</head>
<body>
<aside class="navigation">
    <!-- <a href="#" class="navigation-item">на главную</a>
     <a href="#" class="navigation-item">назад</a> -->
</aside>

<main class="main">
    <h1 class="main-heading"> СИСТЕМА УПРАВЛЕНИЯ СТУДЕНТАМИ И ИХ УСПЕВАЕМОСТЬЮ

    </h1>
    <div class="container">
        <a href="/students" class="title-btn">Студенты</a>
        <a href="discipline.html" class="title-btn">Дисциплины</a>
        <a href="#" class="title-btn">Семестры</a>
    </div>

</main>
<aside class="logout">

    <c:choose>
        <c:when test="${isLogin eq 1}">
            <a href="/logout" class="logout-btn">Logout, ${login}</a>
        </c:when>
        <c:otherwise>
            <a href="/login" class="logout-btn">Login</a>
        </c:otherwise>
    </c:choose>
</aside>

</body>
</html>