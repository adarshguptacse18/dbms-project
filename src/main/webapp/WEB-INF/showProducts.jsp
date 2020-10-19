<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<h1>All Products</h1>
		<div class="container">
		 	<table  class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${prods}" var="prod">
						<tr>
							<td>${prod.name}</td>
							<td>${prod.description}</td>								
							<td>${prod.price}</td>
							<td><span onclick="addToCart(${prod.product_id})" type="button" class="btn btn-warning">Delete	</span></td> 		
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</div>	
	
	
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		<script>
		function addToCart(){
			  $.ajax({
			    url: '/addToCart',
			    type: 'GET',
			    dataType: 'json',
			    data:{
			          'product_id':1,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);
			  });
	
			}
			
	</script>
</body>
</html>