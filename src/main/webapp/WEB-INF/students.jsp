<%--
  Created by IntelliJ IDEA.
  User: Dasha Day
  Date: 26.02.2024
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kelly+Slab&display=swap" rel="stylesheet">
    <script type="text/javascript" src="../resources/js/functions.js?v=1"></script>
    <title>student</title>
</head>
<body>
<aside class="navigation">
    <a href="#" class="navigation-item">на главную</a>

</aside>

<main class="main">
    <h1 class="main-heading"> СИСТЕМА УПРАВЛЕНИЯ СТУДЕНТАМИ И ИХ УСПЕВАЕМОСТЬЮ

    </h1>

    <div class="student-btns">
        <div class="student-btns1">
            <input onclick="studentProgress()" type="submit" class="student-btn" value="посмотреть успеваемость выбранного студента"/>
           <c:if test="${role eq 1}">
               <input onclick="studentModify()" type="submit" class="student-btn" value="модифицировать выбранного студента"/>
           </c:if>

        </div>
        <c:if test="${role eq 1}">
        <div class="student-btns1">
            <form action="/student-create" method="get">
                <input type = "submit" class="student-btn" value="создать студента"/>
                </form>
            <input onclick="studentDelete()" type="submit" class="student-btn" value="удалить выбранных студентов"/>
            </c:if>
        </div>
    </div>
    <h2 class="heading-secondary">
        список студентов
    </h2>
    <table class="student-table" border="1">
        <tr>
            <th>&nbsp</th>
            <th>фамилия</th>
            <th>имя</th>
            <th>группа</th>
            <th>дата поступления</th>
        </tr>
<c:forEach items = "${students}" var ="st">
        <tr>
            <td> <input class="checkbox" type="checkbox" id="" value="${st.id}">
            </td>
            <td>${st.surname}</td>
            <td>${st.name}</td>
            <td>${st.groupe}</td>
            <td><fmt:formatDate value="${st.date}" pattern="dd/MM/yyyy"/></td>
        </tr>
</c:forEach>
    </table>

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

<form action="/student-modify" method="get" id="formToModify" >
    <input type="hidden" name="hiddenModifyId" id="hiddenToModify">
</form>
<form action="/student-delete" method="get"  id="formToDelete">
    <input type="hidden" name="hiddenIdsToDelete" id="hiddenIdsToDelete">
</form>
<form action="/student-progress" method="get"  id="formToProgress">
    <input type="hidden" name="hiddenIdToProgress" id="hiddenIdToProgress">
</form>

</body>
</html>