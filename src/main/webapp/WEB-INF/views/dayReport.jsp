<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="dayReport.label.title"/>${dayReport.user.fullName}: ${dayReport.date}</title>
</head>
<body>
    <a href="/dayReports"><spring:message code="dayReport.label.toDayReportsList" /></a>
    <form:form method="post" modelAttribute="dayReport">
        <form:hidden path="id" />
        <table class="data">
            <tr>
                <td><spring:message code="dayReport.label.user"/></td>
                <td>
                    <form:select path="user">
                        <form:options items="${userList}" itemValue="id" itemLabel="fullName"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><spring:message code="dayReport.label.date"/></td>
                <td><form:input path="date" /></td>
            </tr>
            <tr>
                <td><spring:message code="dayReport.label.text"/></td>
                <td><form:input path="text" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>