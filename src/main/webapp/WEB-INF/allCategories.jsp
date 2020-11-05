<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="adminNavBar.jsp" />

 <body> 
		<div class="container">
			 <h3>Hey Admin</h3>
		 	<table  class="table table-striped  table-hover">
				<thead>
					<tr>
						<th>Category Id</th>
						<th>Category Name</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cat}" var="category">
						<tr>
							<td>${category.category_id}</td>
							<td>${category.category_name}</td>
							<td><a href="/admin/viewProducts/${category.category_id}" type="button" class="btn btn-warning">View Products</a></td>							
							<td><a href="/admin/editCategory/${category.category_id}" type="button" class="btn btn-warning">Edit</a></td>
						</tr>
					</c:forEach>
							
						
				</tbody>
			</table>
				
		</div>	
		<script>
		
	</script>
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</body>
</html>