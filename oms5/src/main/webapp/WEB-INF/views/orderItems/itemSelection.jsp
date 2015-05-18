<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<fieldset>
		<legend><spring:message code="orderItems.itemSelection"/></legend>

		<table width="100%">
 		<tr>
 			<sec:authorize access="hasRole('ROLE_Customer')">
 			<c:if test="${orderData.isEditable }">
	 			<td align="left">
	 				<form:form name="addItem" id="addItem" method="post" action="products.htm" style="display: inline;">
						<input type="hidden" name="orderId" value="${orderId}" style="display: inline;"/> 
						<input type="submit" value="<spring:message code="orderItems.addItem"/>" style="display: inline;"/> 
					</form:form>
	 			</td>
 			</c:if>
 			</sec:authorize>
 			
 			<td align="right">
 			<c:url value="orderItemsResize.htm" var="resizeURL">
			<c:param name="orderId"   value="${orderId}" />
			</c:url>
			<a href='<c:out value="${resizeURL}" />'  style="float:right;">
			<spring:message code="uSearch.show" /> 
			${linesNumber.pageSizeChange}
			<spring:message code="uSearch.items" />
			</a>
 			</td>
 		</tr>
 		</table>

	<div id="list">
		<table border="1" width="100%" style="table-layout: fixed; word-wrap:break-word; position: center" id="orderItems">
			<thead>
				<tr>
					<th><spring:message code="orderItems.itemNumber"/></th>
					<th><spring:message code="orderItems.itemName"/></th>
					<th><spring:message code="orderItems.itemDescription"/></th>
					<th><spring:message code="orderItems.dimension"/></th>
					<th><spring:message code="orderItems.price"/></th>
					<th><spring:message code="orderItems.quantity"/></th>
					<th><spring:message code="orderItems.pricePerLine"/></th>
					<sec:authorize access="hasRole('ROLE_Customer')">
					<c:if test="${orderData.isEditable }">
						<th><spring:message code="edit"/></th>
						<th><spring:message code="delete"/></th>
					</c:if>
					</sec:authorize>
				</tr>
			</thead>

			<c:forEach items="${orderItems}" var="orderItem">
				<tr >
					<td>${orderItem.orderItem.product.id}</td>
					<td>${orderItem.orderItem.product.productName}</td>
					<td>${orderItem.orderItem.product.productDescription}</td>
					<td>${orderItem.orderItem.dimension.dimensionName}</td>
					<td>${orderItem.orderItem.itemPrice}</td>
					<td>${orderItem.orderItem.quantity}</td>
					<td>${orderItem.orderItem.cost}</td>
					<sec:authorize access="hasRole('ROLE_Customer')">
					<c:if test="${orderData.isEditable }">
						<td>
							<form:form id="edit${orderItem.temporaryId }" action="products.htm" method="post">
								<input type="hidden" name="orderId" value="${orderId}"/>
								<input type="hidden" name = "orderItemId" value="${orderItem.temporaryId }"/>
								<a href="javascript:$('#edit${orderItem.temporaryId }').submit()">
									<spring:message code="edit"/>
								</a>
							</form:form>
							
						</td>
						<td >
							<form name="deleteOrderItem" id="deleteOrderItem${orderItem.temporaryId }" method="post" action="orderItemsDelete.htm">
								<input type="hidden" name="orderId" value="${orderId }" />
								<input type="hidden" name="orderItemId" value="${orderItem.temporaryId }" /> 
								<input type="hidden" name="page" value="${page.getCurrentPage()}"/>
								<a href="javascript:$('#deleteOrderItem${orderItem.temporaryId }').submit()">
									<spring:message code="delete"/>
								</a>
							</form>
						</td>
					</c:if>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>
		<div align="Center" style="margin: 0; text-align: center;">
		<c:forEach items="${page.getActionNames()}" var="actionName">
		
			<form method="post" action="orderItems${actionName}.htm" style="display: inline;">
				<input type="hidden" name="orderId" value="${orderId}" style = "display: inline;"/>
				<input type="hidden" name="page" value="${page.getCurrentPage()}" style = "display: inline;"/>
				
				<input type="submit" value="<spring:message code="${actionName }"/>" name = ${actionName }  style = "display: inline;"
					<c:if test="${page.isDisabled(actionName)}"> disabled="disabled" </c:if>/>
			</form>
			
		</c:forEach>
		<h4 style="text-align: left;">
		Page #: <span id="pageNumber">${page.currentPage}</span> from <span id="pageCount">${page.numberOfPages}</span>
		</h4>
		</div>
	</div>
	</fieldset>