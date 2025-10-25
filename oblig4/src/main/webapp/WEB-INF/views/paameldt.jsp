<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Påmeldt</title>
  <link rel="stylesheet" href="<c:url value='/css/simple.css'/>">
</head>
<body>
  <h2>Påmelding registrert<c:if test="${not empty fornavn}">, ${fornavn}</c:if>!</h2>

  <c:choose>
    <c:when test="${not empty fornavn or not empty etternavn or not empty mobil or not empty kjonn}">
      <ul>
        <c:if test="${not empty etternavn}">
          <li>Etternavn: ${etternavn}</li>
        </c:if>
        <c:if test="${not empty mobil}">
          <li>Mobil: ${mobil}</li>
        </c:if>
        <c:if test="${not empty kjonn}">
          <li>Kjønn: ${kjonn}</li>
        </c:if>
      </ul>
    </c:when>
    <c:otherwise>
      <p>Påmelding registrert.</p>
    </c:otherwise>
  </c:choose>

  <p><a href="<c:url value='/deltagerliste'/>">Gå til deltagerlisten</a></p>
  <p><a href="<c:url value='/paamelding'/>">Ny påmelding</a></p>
</body>
</html>
