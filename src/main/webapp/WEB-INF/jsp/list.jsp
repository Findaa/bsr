<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <head>
        <title>
            Test
        </title>
        <link href="${pageContext.request.contextPath}/resources/static/css/style.css" rel="stylesheet" >

        <spring:url value="/css/style.css" var="css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <link href="${css}" rel="stylesheet" />

    </head>
</head>
<body>
<h2>
    ${message}
</h2>
<form:form method="get" action="main">
    <input type="submit" value="Link"> <br/>
</form:form><br/>
</body>
</html>