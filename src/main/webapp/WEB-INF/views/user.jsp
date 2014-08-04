<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--<form:setBundle basename="com.foo.bar.resources.Type" var="typeBundle"/>--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="user.label.title"/> ${user.fullName}</title>
</head>
<body>
    <a href="/users"><spring:message code="user.label.toUserList" /></a>
	<h2><spring:message code="user.label.title" />    ${user.fullName}</h2>
	<table class="data">
		<tr>
			<td><spring:message code="user.label.login"/></td>
			<td><form:input path="user.login" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.label.password"/></td>
			<td><form:password path="user.password" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.label.lastName"/></td>
			<td><form:input path="user.lastName" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.label.firstName"/></td>
			<td><form:input path="user.firstName" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.label.email"/></td>
			<td><form:input path="user.email" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.label.department"/></td>
			<td> 
				<form:select path="user.department">
                     <form:options items="${departmentList}" itemValue="id" itemLabel="name"/>
 				</form:select>
			</td>
		</tr>
		<tr>
			<td><spring:message code="user.label.chief"/></td>
			<td>
				<form:select path="user.chief">
					<form:options items="${userList}" itemValue="id" itemLabel="fullName"/>
				</form:select> 
			</td>
		</tr>
		<tr>
			<td><spring:message code="user.label.accessLevel"/></td>
			<td>
				<form:select path="user.accessLevel">
                    <c:forEach items="${accessLevelList}" var="accessLevel">
                        <form:option value="${accessLevel.value}">
                            <spring:message code="accessLevel.name.${value}"/>
                        </form:option>
                    </c:forEach>
				</form:select>
			</td>
		</tr>
		<tr>
			<td><spring:message code="user.label.position"/></td>
			<td><form:input path="user.position" size="30"/></td>
		</tr>
		<tr>
			<td><spring:message code="user.label.vacation"/></td>
			<td><form:checkbox path="user.vacation" /></td>
		</tr>
	</table>
</body>
</html>