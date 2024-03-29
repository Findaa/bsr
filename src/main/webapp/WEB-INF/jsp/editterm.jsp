<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>
    <title>Edit Terms Data</title>
    <link href="${pageContext.request.contextPath}/resources/static/css/style.css" rel="stylesheet">
    <script src="../static/js/sorttable.js"></script>
    <spring:url value="/css/style.css" var="css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="${css}" rel="stylesheet"/>
</head>

<body>
<div class="relation">
    <form:form method="post" action="/retention_manager_war_exploded/updateterm" modelAttribute="newterm">
        <table>
            <thead>Edit Terms</thead>
            <tbody>
            <tr>
                <td>System</td>
                <td>Request</td>
                <td>Order-number</td>
                <td>from</td>
                </d>
            </tr>
            <tr>
                <td><input type="text" name="system" placeholder="System" required
                           value="${term.systemName}"/></td>
                <td><input type="text" name="request" placeholder="Request" required
                           value="${term.request}"/></td>
                <td><input type="text" name="orderNumber" placeholder="Order number"
                           value="${term.orderNumber}"/></td>
                <td><input type="text" name="fromDate" placeholder="Date from" required
                           value="${term.fromDate}"/></td>
            </tr>
            <tr>
                <d>
                    <td>To</td>
                    <td>Amount</td>
                    <td>Type</td>
                    <td>Period</td>
                </d>
            </tr>
            <tr>
                <td><input type="text" name="toDate" placeholder="Date to" required
                           value="${term.toDate}"/></td>
                <td><input type="text" name="amount" placeholder="Amount" required
                           value="${term.amount}"/></td>
                <td><input type="text" name="amountType" placeholder="Amount type" required
                           value="${term.amountType}"/></td>
                <td><input type="text" name="amountPeriod" placeholder="Amount period" required
                           value="${term.amountPeriod}"/></td>
            </tr>
            <tr>
                <d>
                    <td>Percent</td>
                    <td>Active</td>
                </d>
            </tr>
            <tr>
                <td><input type="text" name="authorizationPercent" placeholder="Authorization %" required
                           value="${term.authorizationPercent}"/></td>
                <td><input type="checkbox" name="active" id="'active"/></td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="Edit"/>
    </form:form>
</div>
<div class="relation">
    <c:set var="id" scope="request" value="${id}"/>
    <c:if test="${id != null}">
    <form:form method="post" action="delete/${id}">
    <input type="submit" value="Delete term"/>
    </form:form>
    </c:if>

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