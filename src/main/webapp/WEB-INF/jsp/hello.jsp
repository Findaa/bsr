<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>
        Test
    </title>
    <link href="${pageContext.request.contextPath}/resources/static/css/style.css" rel="stylesheet" >

    <spring:url value="/css/style.css" var="css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${css}" rel="stylesheet" />

</head>
<body>
<h2>
    ${message}
</h2>
<div class="menu"></div>
<form:form method="get" action="redir">
    <input type="submit" value="Link"> <br/>
</form:form><br/>
<div class="logout">
    <form:form method="get" action="list">
        <input type="submit" value="List button"></form:form>
    <form:form method="get" action="list">
        <input type="submit" value="List button 2">
    </form:form>
</div>
</body>
</html>