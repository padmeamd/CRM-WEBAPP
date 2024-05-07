<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
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
  <title>student-create</title>
  <script>
    $( function() {
      $( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy' });
    } );
  </script>
</head>
<body>
<aside class="navigation">
  <a href="#" class="navigation-item">на главную</a>
  <a href="#" class="navigation-item">назад</a>

</aside>

<main class="main">
  <h1 class="main-heading"> СИСТЕМА УПРАВЛЕНИЯ СТУДЕНТАМИ И ИХ УСПЕВАЕМОСТЬЮ</h1>
  <h2 class="heading-secondary">для создания студента заполните все поля и нажмите кнопку "создать"</h2>
  <form action="/student-create" method="post">
    <table>
      <tr>

        <td class="cell1" align="right"><h3>фамилия</h3></td>
        <td><input name="surname" type="text" id="name"></td>
      </tr>
    </table>
    <br>
    <table>
      <tr>
        <td class="cell1" align="right"><h3>имя</h3></td>
        <td><input name="name" type="text" id="name"></td>
      </tr>
    </table>
    <br>
    <table>
      <tr>
        <td class="cell1" align="right"><h3>группа</h3></td>
        <td><input name="groupe" type="text" id="name"></td>
      </tr>
    </table>
    <br>
    <table>
      <tr>
        <td class="cell1" align="right"><h3>дата поступления</h3></td>
        <td><input name="date" type="text" id="datepicker"></td>
      </tr>
    </table>
    <br>


    <input type="submit" value=" создать" class="field">
    <c:if test="${message eq 1}">
      <h4>Поля не должны быть пустыми!</h4>
    </c:if>
  </form>


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