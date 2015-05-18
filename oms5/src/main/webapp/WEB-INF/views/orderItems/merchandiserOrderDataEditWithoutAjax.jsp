<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet" type="text/css" media="screen"
	href="css/datePicker.css">
<script type="text/javascript" src="js/date.js"></script>
<script type="text/javascript" src="js/jquery.datePicker.js"></script>
<script type="text/javascript">

	$(function() {
		Date.format = 'dd/mm/yyyy';
		$('.date-pick').datePicker().val('${deliveryDate}').trigger('change');
	});
</script>
<div style="float: left; width: 50%;">
	<form name="merchandiserOrderEditForm" id="merchandiserOrderEditForm" method="get"
		action="merchandiserEdit.htm">
		<input type="hidden" name="orderId" value="${selectedOrderID}">
		<table>
			<tr>
				<td><spring:message code="merch.orStatus" /></td>
				<td><select name="orderStatus">
						<option value="Created"
							<c:if test="${orderStatus eq 'Created'}">selected="selected"</c:if>>
							<spring:message code="merch.created" />
						</option>
						<option value="Ordered"
							<c:if test="${orderStatus eq 'Ordered'}">selected="selected"</c:if>>
							<spring:message code="merch.ordered" />
						</option>
						<option value="Pending"
							<c:if test="${orderStatus eq 'Pending'}">selected="selected"</c:if>>
							<spring:message code="merch.pending" />
						</option>
						<option value="Delivered"
							<c:if test="${orderStatus eq 'Delivered'}">selected="selected"</c:if>>
							<spring:message code="merch.delivered" />
						</option>
				</select>
				</td>
			</tr>
			<tr>
					<td><spring:message code="merch.delDate" />
					</td>
					<td><input name="deliveryDate" type="text" class="date-pick"
						id="dateDays" style="width: 110px" />
					</td>
				</tr>
			<tr>
				<td><spring:message code="merch.gift" /></td>
				<td><input type="checkbox" name="isGift"
					<c:if test="${isGift}">checked="checked"</c:if>></td>
			</tr>
			<tr>
				<td colspan = "2" style="color:red;">
					<div id="error"></div> 
				</td>
			</tr>
		</table>
	</form>
</div>