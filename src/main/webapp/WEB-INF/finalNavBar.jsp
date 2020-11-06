<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:choose>
	<c:when test="${ empty username or role=='ROLE_USER'}">
		<jsp:include page="navbar.jsp" />
	</c:when>
	<c:when test="${ role=='ROLE_ADMIN'}">
		<jsp:include page="adminNavBar.jsp" />
	</c:when>
	<c:otherwise>
		<jsp:include page="vendorNavBar.jsp" />
	</c:otherwise>
</c:choose>