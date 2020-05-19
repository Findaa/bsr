<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>
        Test
    </title>
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