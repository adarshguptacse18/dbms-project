<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		User SignUp	
		<form:form method="post" modelAttribute="prod">
			<fieldset class="form-group">
					<form:label path="name">Name</form:label>
					<form:input path="name" type="text" class="form-control" required="required"/>
					<form:label path="description">Description</form:label>
					<form:input path="description" type="text" class="form-control" required="required"/>
					<form:label path="price">Price</form:label>
					<form:input path="price" type="text" class="form-control" required="required"/>	
					<form:label path="category_id">Category</form:label>
					<form:input path="category_id" type="text" class="form-control" required="required"/>							
			</fieldset>
			<button type="submit" class="btn btn-success">Add</button>
		</form:form>

	</div>
	
	
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		
</body>
</html>