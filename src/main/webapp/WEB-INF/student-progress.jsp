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
  <a href="/" class="navigation-item">на главную</a>
  <a href="/students" class="navigation-item">назад</a>

</aside>

<main class="main">
  <h1 class="main-heading"> система управления студентами и их успеваемостью</h1>
  <h2 class="heading-secondary">отображена успеваемость для следующего студента:</h2>

  <div class="container">
    <table class="student-table" border="1">
      <tr>
        <th>фамилия </th>
        <th> имя</th>
        <th> группа</th>
        <th> дата поступления</th>
      </tr>
      <tr>
        <td>${student.surname}</td>
        <td>${student.name} </td>
        <td>${student.groupe}</td>
        <td><fmt:formatDate value="${student.date}" pattern="dd/MM/yyyy"/></td>
      </tr>
    </table>
  </div>

  <section class="semestr-note">
    <div>
      <table class="disciplina-note" border="1">
        <tr>
          <th>дисциплина </th>
          <th>оценка</th>
        </tr>
        <c:forEach items="${marks}" var="m">
          <tr>
            <td>${m.discipline.discipline}</td>
            <td>${m.mark}</td>
          </tr>
        </c:forEach>

      </table>

    </div>
    <div class="choice-semestr">
      <h2> выбрать семестр: </h2>
      <form action="/student-progress" method="get" >
        <input type="hidden" name="hiddenIdToProgress" value="${student.id}">
        <select border="1" name="idSelectedTerm">
        <c:forEach items="${terms}" var="t">
          <c:choose>
            <c:when test="${t.id eq selectedTerm.id}">
              <option selected value="${t.id}" class="choice">${t.term} </option>
            </c:when>
            <c:otherwise>
              <option value="${t.id}" class="choice">${t.term} </option>
            </c:otherwise>

          </c:choose>
        </c:forEach>
      </select>
        <input type="submit" class="choice-btn" value="выбрать">
      </form>
    </div>
  </section>
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