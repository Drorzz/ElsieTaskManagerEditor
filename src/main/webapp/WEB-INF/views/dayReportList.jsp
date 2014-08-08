<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="dayReportList.label.title"/></title>
</head>
<body>

<h2><spring:message code="dayReportList.label.title" /></h2>

<table class="data">
    <tr>
        <th><spring:message code="dayReportList.label.user" /></th>
        <th><spring:message code="dayReportList.label.date" /></th>
        <th><spring:message code="dayReportList.label.text" /></th>
        <th></th>
    </tr>
    <c:forEach items="${dayReportList}" var="dayReport">
        <tr>
            <td>${dayReport.user.fullName}</td>
            <td>${dayReport.date}</td>
            <td>${dayReport.text}</td>
            <td><a href="/dayReports/${dayReport.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>