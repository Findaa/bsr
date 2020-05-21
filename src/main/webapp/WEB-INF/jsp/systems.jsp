<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <head>
        <title>
            Test
        </title>
        <link href="${pageContext.request.contextPath}/resources/static/css/style.css" rel="stylesheet">
        <%--        <link href="${css}" rel="stylesheet"/>--%>
        <%--        <spring:url value="/css/style.css" var="css"/>--%>
        <spring:url value="/js/jquery.js" var="jq"/>
        <spring:url value="/js/dataTables.js" var="dt"/>
        <script src="${jq}"></script>
        <link rel="stylesheet" type="text/css" href="../static/css/dataTables.css">
        <script type="text/javascript" src="${dt}"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#maintable").dataTable({
                    "bJQueryUI" : true,
                    "sPaginationType" : "full_numbers",
                    "bRetrieve" : true,
                    "bFilter" : true,
                    "iDisplayLength": 10,
                    "bProcessing" : true,
                    "bServerSide" : false,
                    "aoColumns" : [ { "bSearchable" : false,"bVisible" : false,
                        "asSorting" : [ "asc" ] },
                        {"sWidth" : "50%","bSortable" : true },
                        {"sWidth" : "50%","bSortable" : true },
                    ]
                });
                });
            });
        </script>
    </head>
</head>
<body>
<br/>
<br/>
<br/>
<br/>
<div>
    <table id="maintable" class="display">
        <thead>
        <tr>
            <th>Numer umowy</th>
            <th>System</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="pp">
            <tr>
                <td><c:out value="${pp.name}"/></td>
                <td><c:out value="${pp.info}"/></td>
                <td><a href="/retention_manager_war_exploded/editsystem/${pp.id}">Edit</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>
    <br/>
    <a href="/retention_manager_war_exploded/addsys">Add new system</a>
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