<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Deltagerliste</title>
  <link rel="stylesheet" href="<c:url value='/css/simple.css'/>">
  <link rel="stylesheet" href="<c:url value='/css/app.css'/>">
</head>
<body>
  <h1>Deltagerliste</h1>

  <c:choose>
    <c:when test="${empty deltagere}">
      <p>Ingen påmeldte ennå.</p>
    </c:when>
    <c:otherwise>
        <table class="compact">
          <thead>
            <tr>
              <th>Fornavn</th>
              <th>Etternavn</th>
              <th>Mobil</th>
              <th>Kjønn</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="d" items="${deltagere}">
              <tr>
                <td>${d.fornavn}</td>
                <td>${d.etternavn}</td>
                <td>${d.tlfNummer}</td>
                <td>${d.kjonn}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

    </c:otherwise>
  </c:choose>

  <p><a href="<c:url value='/paamelding'/>">Til påmelding</a></p>
</body>
</html>
