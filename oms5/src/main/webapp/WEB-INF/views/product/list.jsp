<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.softserveinc.edu.oms.web.product.model.ListProductModel" %>

<table>
	<thead class="centerText">
		<tr>
			<th>
				<form:form id="sortName" commandName="model" method="post" action="products.htm">
					<%@include file="hidden.jsp"%>
					<input type="hidden" value="<%=ListProductModel.ITEM_NAME%>" name="sort">
					<a href="javascript:$('#sortName').submit()"><spring:message code="liProd.iName" /></a>
				</form:form>
			</th>
			<th>
				<form:form id="sortDescription" commandName="model" method="post" action="products.htm">
					<%@include file="hidden.jsp"%>
					<input type="hidden" value="<%=ListProductModel.ITEM_DESCRIPTION%>" name="sort">
					<a href="javascript:$('#sortDescription').submit()"><spring:message code="liProd.iDesc" /></a>
				</form:form>
			</th>
			<th><spring:message code="liProd.add" /></th>
		</tr>
	</thead>
	<c:forEach items="${products}"  var="product">
		<tr>
			<td>${product.productName}</td>
			<td>${product.productDescription}</td>
			<td>
				<form:form id="selectFrom${product.id }" commandName="model" method="post" action="products.htm">
					<%@include file="hidden.jsp"%>
					<input type="hidden" value="${product.id }" name="select">
					<a href="javascript:$('#selectFrom${product.id } ').submit()"><spring:message code="liProd.select" /></a>
				</form:form>
			</td>
		</tr>
	</c:forEach>
</table>