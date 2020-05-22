<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>

<head>
    <title>View Terms</title>
    <link href="${pageContext.request.contextPath}/resources/static/css/style.css" rel="stylesheet">
    <script src="../static/js/sorttable.js"></script>
    <spring:url value="/css/style.css" var="css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${css}" rel="stylesheet"/>
</head>

<body>
<br/>
<br/>
<br/>
<br/>
<div>
    <table id="maintable" class="sortable">
        <thead>
        <tr>
            <th><font color="#32cd32"> Terms number</font></th>
            <th><font color="#32cd32"> System</font></th>
            <th><font color="#32cd32"> From</font></th>
            <th><font color="#32cd32"> To</font></th>
            <th><font color="#32cd32"> Income</font></th>
            <th><font color="#32cd32"> In scale of</font></th>
            <th><font color="#32cd32"> Active</font></th>
            <th><font color="#32cd32"> Options</font></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="pp">
            <tr>
                <td><c:out value="${pp.orderNumber}"/></td>
                <td><c:out value="${pp.systemName}"/></td>
                <td><c:out value="${pp.fromDate}"/></td>
                <td><c:out value="${pp.toDate}"/></td>
                <td><c:out value="${pp.amount}"/> <c:out value="${pp.amountType}"/></td>
                <td><c:out value="${pp.amountPeriod}"/></td>
                <td><c:out value="${pp.active ? 'Yes' : 'No'}"/></td>
                <td><a href="/retention_manager_war_exploded/edit/${pp.id}">Edit</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>
    <br/>
    <a href="/retention_manager_war_exploded/add">Add new term</a>
    <br/>
    <br/>
    <form:form method="get" action="loadcsv">
        <input type="submit" value="xlsx" width="50">
    </form:form>
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
</div>
</body>
</html>