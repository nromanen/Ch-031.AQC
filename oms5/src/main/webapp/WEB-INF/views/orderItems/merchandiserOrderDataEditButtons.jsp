<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>

function setError(msg) {
	document.getElementById("error").innerHTML = msg;
}

function calcRows(){
	return document.getElementById("orderItems").getElementsByTagName("tr").length;
}

function dateNotEmpty() {
	if (document.getElementById("dateDays").value.length > 0) {
		return true;
	}
	return false;
}


function validateOrder() {
	var orderItemsCount = calcRows();
	if (orderItemsCount - 1 > 0) {
		return true
	}
	return false;
}

function save() {
	if (validateOrder()) {
		if (dateNotEmpty()) {
			document.merchandiserOrderEditForm.submit();
		} else {
			setError("Please select Delivery Date");
		}
	}else {
		setError("Order has no items");
	}
}
</script>

<div style="float: right;">
	<form style="display: inline;">
		<input type="button" value="<spring:message code="merch.bSave" />"
			onclick="save()" />
	</form>
	<form method="get" action="order.htm" style="display: inline;">
		<input type="submit" value="<spring:message code="merch.bCancel" />">
	</form>
</div>