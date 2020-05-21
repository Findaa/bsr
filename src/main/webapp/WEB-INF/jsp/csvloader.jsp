<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>
        Test
    </title>
    <link href="${pageContext.request.contextPath}/resources/static/css/style.css" rel="stylesheet">
    <script src="../static/js/sorttable.js"></script>
    <spring:url value="/css/style.css" var="css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${css}" rel="stylesheet"/>


</head>
<body>
<div class="relation">
    <p>
        <form:form method="post" action="/retention_manager_war_exploded/processLoadCsv" modelAttribute="path">
        <input type="text" name="path" placeholder="C:/Users/ /Documents/ .xlsx" required/>
        <input type="submit" value="Upload"/>
        </form:form>
</div>
<div class="relation">
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