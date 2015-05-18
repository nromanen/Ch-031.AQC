<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="js/jquery-1.6.2.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	<%@include file = "itemSelection.jsp" %>
	
	<sec:authorize access="hasRole('ROLE_Customer')">
	<table>
	<tr>
	<td><%@include file="totals.jsp" %></td>
	<td><%@include file="creditcard.jsp" %></td>
	</tr>
	</table>
	</sec:authorize>
		
	<sec:authorize access="hasRole('ROLE_Merchandiser')">
		<%@include file="totalsMerchandiser.jsp" %>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_Customer')">
		<%@include file="buttons.jsp" %>
	</sec:authorize>
	

