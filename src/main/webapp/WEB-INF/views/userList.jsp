<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="userList.label.title"/></title>
</head>
<body>

<h2><spring:message code="userList.label.title" /></h2>

<table class="data">
    <tr>
        <th><spring:message code="userList.label.name" /></th>
        <th><spring:message code="userList.label.login" /></th>
        <th><spring:message code="userList.label.department" /></th>
        <th><spring:message code="userList.label.isActive" /></th>
        <th></th>
    </tr>
    <c:forEach items="${entityList}" var="department">
        <tr>
            <td>${department.fullName}</td>
            <td>${department.login}</td>
            <td>${department.department.name}</td>
            <td>${department.getActive()}</td>
            <td><a href="/users/${department.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>