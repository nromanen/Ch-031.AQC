<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/jquery-1.6.2.js"></script>

<script type="text/javascript">
	function deleteItem(id) {
		var url = "${deleteItem}?productID=" + id;
		var OK = confirm('<spring:message code="im.deleteMsg" />');
		if (OK) {
			window.location = url;
		}
	}
</script>

<div id="list">
	<h2>
		<spring:message code="im.h2" />
	</h2>

	<a href="addItem.htm" style="color: blue;"><spring:message code="im.AddProduct" /> </a>

	<%@include file="itemsSearch.jsp"%>

	<table id="table" style="table-layout: fixed; word-wrap:break-word" >
		<thead>
			<tr>
				<th><a href="${itemSort}?propertyName=productName"><spring:message
							code="im.Name" /> </a></th>
				<th><a href="${itemSort}?propertyName=productDescription"><spring:message
							code="im.Description" /> </a></th>
				<th><a href="${itemSort}?propertyName=productPrice"><spring:message
							code="im.Price" /> </a></th>
				<th><spring:message code="edit" /></th>
				<th><spring:message code="delete" /></th>
			</tr>
		</thead>

		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.productName}</td>
				<td>${product.productDescription}</td>
				<td>${product.productPrice}</td>
				<td><a href="editItem.htm?productID=${product.id}"><spring:message
							code="edit" /> </a>
				<td><a href='javascript:deleteItem("${product.id}");'><spring:message
							code="delete" /> </a></td>
			</tr>
		</c:forEach>
	</table>
	<%@include file="itemsNavigation.jsp"%>
	<a href="reportItems.htm" style="color: blue"><spring:message code="users.crReport" /></a>
</div>