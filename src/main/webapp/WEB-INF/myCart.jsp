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
	
	 <h3>MyCart</h3>
		<div class="container">
		 	<table  class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Quantity</th>
						<th>Price</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${prods}" var="prod">
						<tr>
							<td>${prod.name}</td>
							<td>${prod.description}</td>	
							<td><input type="number" min="1" max="5" class="form-control" id="prod_${prod.product_id}" placeholder="190" name="password" value = ${prod.quantity}></td>														
							<td>${prod.price}</td>
							<td><span onclick="addToCart(${prod.product_id})" type="button" class="btn btn-warning">Delete	</span></td> 		
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</div>	
		<a href="/placeOrder">Place Order</a>
	
	
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		<script>
		function addToCart(id){
			var quantity= document.getElementById('prod_'+id).value;
			 $.ajax({
			    url: '/addToCart',
			    type: 'GET',
			    dataType: 'json',
			    data:{
			          'product_id':id,
			          'quantity':quantity,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);
			  }); 
			}
			
	</script>
</body>
</html>