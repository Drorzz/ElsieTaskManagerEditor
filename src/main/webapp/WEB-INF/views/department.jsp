<%--
  Created by IntelliJ IDEA.
  User: Denis Ivansky
  Date: 04.08.2014
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="department.label.title"/> ${department.name}</title>
</head>
<body>
<a href="/departments"><spring:message code="department.label.toDepartmentList" /></a>
<h2><spring:message code="department.label.title" />${department.name}</h2>
<form:form method="post" modelAttribute="department">
    <table class="data">
        <tr>
            <td><spring:message code="department.label.name"/></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><spring:message code="department.label.level"/></td>
            <td><form:input path="level" /></td>
        </tr>
        <tr>
            <td><spring:message code="department.label.listOrder"/></td>
            <td><form:input path="listOrder" /></td>
        </tr>
        <tr>
            <td><spring:message code="department.label.projectActive"/></td>
            <td><form:input path="projectActive" /></td>
        </tr>
    </table>
    <input type="submit" value="Submit" />
</form:form>
</body>
</html>