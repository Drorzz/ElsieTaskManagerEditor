<%--
  Created by IntelliJ IDEA.
  User: konstantin_k
  Date: 04.08.2014
  Time: 17:35
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
    <title><spring:message code="departmentList.label.title"/></title>
</head>
<body>

<h2><spring:message code="departmentList.label.title" /></h2>

<table class="data">
    <tr>
        <th><spring:message code="departmentList.label.name" /></th>
        <th></th>
    </tr>
    <c:forEach items="${entityList}" var="department">
        <tr>
            <td>${department.name}</td>
            <td><a href="/departments/${department.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>