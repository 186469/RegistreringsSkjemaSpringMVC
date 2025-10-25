<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Påmelding</title>
  <link rel="stylesheet" href="<c:url value='/css/simple.css'/>">
</head>
<body>
  <h1>Påmelding</h1>

  <form:form action="${pageContext.request.contextPath}/paamelding" method="post" modelAttribute="skjema">

    <div class="row">
      <label>Fornavn:</label>
      <form:input path="fornavn"/>
      <form:errors path="fornavn" cssClass="feil"/>
    </div>

    <div class="row">
      <label>Etternavn:</label>
      <form:input path="etternavn"/>
      <form:errors path="etternavn" cssClass="feil"/>
    </div>

    <div class="row">
      <label>Mobil (8 siffer):</label>
      <form:input path="tlfNummer"/>
      <form:errors path="tlfNummer" cssClass="feil"/>
    </div>

    <div class="row">
      <label>Kjønn:</label>
      <form:select path="kjonn">
        <form:option value="" label="Velg"/>
        <form:option value="mann"   label="mann"/>
        <form:option value="kvinne" label="kvinne"/>
      </form:select>
      <form:errors path="kjonn" cssClass="feil"/>
    </div>

    <div class="row">
      <label>Passord (4–8):</label>
      <form:password path="passord" maxlength="8"/>
      <form:errors path="passord" cssClass="feil"/>
    </div>

    <div class="row">
      <label>Gjenta passord:</label>
      <form:password path="repetertPassord" maxlength="8"/>
      <form:errors path="repetertPassord" cssClass="feil"/>
    </div>

    <c:if test="${not empty _csrf}">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </c:if>

    <button type="submit">Meld meg på</button>
  </form:form>

  <p><a href="<c:url value='/deltagerliste'/>">Gå til deltagerlisten</a></p>
</body>
</html>
