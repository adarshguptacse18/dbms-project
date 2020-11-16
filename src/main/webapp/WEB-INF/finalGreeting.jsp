<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:choose>
	<c:when test="${ empty username or role=='ROLE_USER'}">
		Hey User
	</c:when>
	<c:when test="${ role=='ROLE_ADMIN'}">
		Hey Admin
	</c:when>
	<c:otherwise>
		Hey Vendor
	</c:otherwise>
</c:choose>