<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript" src="js/json.min.js"></script>
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<!-- script type="text/javascript" src="js/user/addEditUser.js"></script-->

<div id="edit">
	<h3><spring:message code="uform.h3" /></h3>

	<br />
	<form:form method="POST" commandName="userModel"
		style="margin: 0; text-align: left;">
		<form:hidden path="id" />
		<form:hidden path="customerTypeID" />
		<form:hidden path="balance" />

		<table>
			<tr>
				<td><spring:message code="uform.login" /></td>
				<td><form:input path="login" id="login" />
				<form:errors path="login" class="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="uform.fName" /></td>
				<td><form:input id="firstName" path="firstName" />
				<form:errors path="firstName" class="error" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="uform.lName" /></td>
				<td><form:input id="lastName" path="lastName" />
				<form:errors path="lastName" class="error" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="uform.pass" /></td>
				<td><form:password id="password" path="password" />
				<form:errors path="password" class="error" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="uform.confirm" /></td>
				<td><form:password id="confirmPassword" path="confirmPassword" />
				<form:errors path="confirmPassword" class="error" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="uform.email" /></td>
				<td><form:input id="email" path="email" />
				<form:errors id="emailError" path="email" class="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="uform.region" /></td>
				<td><form:select path="regionID" items="${regions}"
						itemValue="id" />
			</tr>
			<tr>
				<td><spring:message code="uform.role" /></td>
				<td><form:select path="roleID" items="${roles}"
						itemValue="id" />
			</tr>
		</table>

		<br />

		<input type="submit" value="<spring:message code="uform.bCr" />" /> <!-- onclick="return processSubmit()" -->
		<input type="button" value="<spring:message code="uform.bCancel" />" 
			onClick="location.href='users.htm'" />
	</form:form>
</div>