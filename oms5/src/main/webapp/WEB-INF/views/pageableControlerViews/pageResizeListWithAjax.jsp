<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div>
<p style="margin: 0; text-align: right;">
	<a href="#" onClick="AjaxPageLoad(1,${pageModel.pageSize})" ><spring:message code="uSearch.show" /> ${pageModel.pageSizeChange}
		<spring:message code="uSearch.items" /></a>
</p>
</div>