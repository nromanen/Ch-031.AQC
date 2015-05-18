<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="edit">
	<fieldset>
		<legend>
			<spring:message code="merch.totals" />
		</legend>
		<jsp:include page="generalOrderDataPart.jsp" />
		<c:choose>
			<c:when test="${orderStatus eq 'Delivered'}">
				<jsp:include page="merchandiserOrderDataNonEditable.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="merchandiserOrderDataEditWithoutAjax.jsp" />
			</c:otherwise>
		</c:choose>
	</fieldset>
	<c:choose>
		<c:when test="${orderStatus eq 'Delivered'}">
		<jsp:include page="merchandiserOrderDataNonEditableButtons.jsp" />
		</c:when>
		<c:otherwise>
		<jsp:include page="merchandiserOrderDataEditButtons.jsp" />
		</c:otherwise>
	</c:choose>
</div>
