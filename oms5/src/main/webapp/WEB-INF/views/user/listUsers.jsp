<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/jquery-1.6.2.js"></script>

<script type="text/javascript">
	function deleteItem(id) {
		var url = "${deleteUser}?userID=" + id;
		var OK = confirm('<spring:message code="users.askDel" />');
		if (OK) {
			window.location = url;
		}
	}
</script>

<div id="list">
	<h2>
		<spring:message code="users.h2" />
	</h2>

	<a href="addUser.htm"><spring:message code="users.cr" /> </a>

	<%@include file="userSearch.jsp"%>

	<table id="table">
		<thead>
			<tr>
				<th><a href="${usersSort}?propertyName=firstName"><spring:message
							code="users.fName" /> </a></th>
				<th><a href="${usersSort}?propertyName=lastName"><spring:message
							code="users.lName" /> </a></th>
				<th><a href="${usersSort}?propertyName=login"><spring:message
							code="users.login" /> </a></th>
				<th><a href="${usersSort}?propertyName=role"><spring:message
							code="users.role" /> </a></th>
				<th><a href="${usersSort}?propertyName=region"><spring:message
							code="users.region" /> </a></th>
				<th><spring:message code="users.edit" /></th>
				<th><spring:message code="users.del" /></th>
			</tr>
		</thead>

		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.login}</td>
				<td>${user.role}</td>
				<td>${user.region}</td>
				<td><a href="editUser.htm?userID=${user.id}"><spring:message
							code="users.edit" /> </a>
				<td><a href='javascript:deleteItem("${user.id}");'><spring:message
							code="users.del" /> </a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<%@include file="userNavigation.jsp"%>

	<h5>
		<a href="reportUsers.htm"><spring:message code="users.crReport" />
		</a>
	</h5>
</div>