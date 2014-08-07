<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="user.label.title"/> ${user.fullName}</title>
</head>
<body>
    <a href="/users"><spring:message code="user.label.toUserList" /></a>
	<h2><spring:message code="user.label.title" />${user.fullName}</h2>
    <form:form method="post" modelAttribute="user">
        <form:hidden path="id" />
        <table class="data">
            <tr>
                <td><spring:message code="user.label.login"/></td>
                <td><form:input path="login" /></td>
            </tr>
            <tr>
                <td><spring:message code="user.label.password"/></td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td><spring:message code="user.label.lastName"/></td>
                <td><form:input path="lastName" /></td>
            </tr>
            <tr>
                <td><spring:message code="user.label.firstName"/></td>
                <td><form:input path="firstName" /></td>
            </tr>
            <tr>
                <td><spring:message code="user.label.email"/></td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td><spring:message code="user.label.department"/></td>
                <td>
                    <form:select path="department">
                         <form:options items="${departmentList}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><spring:message code="user.label.chief"/></td>
                <td>
                    <form:select path="chief">
                        <form:options items="${userList}" itemValue="id" itemLabel="fullName"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><spring:message code="user.label.accessLevel"/></td>
                <td>
                    <form:select path="accessLevel">
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
                <td><form:input path="position" size="30"/></td>
            </tr>
            <tr>
                <td><spring:message code="user.label.vacation"/></td>
                <td><form:checkbox path="vacation" /></td>
            </tr>
            <tr>
                <td><spring:message code="user.label.active"/></td>
                <td><form:checkbox path="active" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>