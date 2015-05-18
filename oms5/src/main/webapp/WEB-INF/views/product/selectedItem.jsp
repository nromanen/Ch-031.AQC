<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import = "com.softserveinc.edu.oms.web.product.model.ListProductModel"  %>
<fieldset>

	<form:form commandName="model" method="post" action="products.htm" id="doneForm">
		<form:hidden path="orderId"/>
		<form:hidden path="orderItemId"/>
		<form:hidden path="searchProperty"/>
		<form:hidden path="searchValue"/>
		<form:hidden path="ascending"/>
		<form:hidden path="sortPropertyName"/>
		<form:hidden path="productId"/>
		<table>
			<tr>
				<td>
					<spring:message code="liProd.item" />
				</td>
				<td>
					<c:if test="${(!empty model.productId) &&(model.productId!='')&&(model.productId!=null)}">
						${selectedProduct.productName }
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<form:errors path="productId" class="error"/>
				</td>
			</tr>
			<tr>
				<td><spring:message code="liProd.price" /></td>
				<td><c:if test="${(model.productId!=null)}">${selectedProduct.productPrice }</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="liProd.quantity" />
				</td>
				<td>
					<form:input path="quantity"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<form:errors path="quantity" class="error"/>
				</td>
			</tr>
			<tr>
				<td><spring:message code="liProd.dimen" /></td>
				<td><form:select path="dimension" items="<%=ListProductModel.DIMENSIONS %>" /></td>
			</tr>
		</table>
		<input type="hidden" name="done"/>
	</form:form>
	<table>
	<tr>
		<td>
			<form:form commandName="model" id = "resetForm" method="post" action="products.htm" >
				<%@include file="hidden.jsp" %>
				<input type="hidden" name="reset"/>
				<input type="submit" value="<spring:message code="liProd.bReset" />">
			</form:form>
		</td>
		
		<td>
			<input type="submit" value="<spring:message code="liProd.bDone" />" onclick="javascript:$('#doneForm').submit()"/>
		</td>
		
		<td>
			<form:form id = "cancelForm" method="get" action="orderItems.htm">
				<input type="hidden" name="orderId" value="${model.orderId }"/>
				<input type="submit" value="<spring:message code="liProd.bCancel" />">
			</form:form>
		</td>
	</tr>
	</table>
</fieldset>