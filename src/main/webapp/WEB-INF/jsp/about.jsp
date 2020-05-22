<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>
    <title>About</title>
    <link href="${pageContext.request.contextPath}/resources/static/css/style.css" rel="stylesheet">
    <spring:url value="/css/style.css" var="css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${css}" rel="stylesheet"/>
</head>

<body>
<br/>
<br/>
<br/>
Czesc. Pozdrawiam czytelnikow. Projekt wykonuje od 18.05.2020.
<div class="logout">
    <form:form method="get" action="list">
        <input type="submit" value="All documents">
    </form:form>
    <form:form method="get" action="alist">
        <input type="submit" value="Active documents">
    </form:form>
    <form:form method="get" action="systems">
        <input type="submit" value="View systems">
    </form:form>
    <form:form method="get" action="about">
        <input type="submit" value="About app">
    </form:form>
</div>
</body>
</html>