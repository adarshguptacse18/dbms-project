<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <jsp:include page="navbar.jsp" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="finalNavBar.jsp" />

	<div class="toast">
	  <div class="toast-header">
	    Product Removed From Cart!!
	  </div>
	</div>
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
						<tr id="prod_row_${prod.product_id}">
							<td>${prod.name}</td>
							<td>${prod.description}</td>	
							<td><input type="number" class="form-control" id="prod_${prod.product_id}" placeholder="190" name="password" value = ${prod.quantity} disabled></td>														
							<td>${prod.price}</td>
							<td><span onclick="deleteFromCart(${prod.product_id})" type="button" class="btn btn-warning">Delete	</span></td> 		
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
			<p> Total amount : <strong>${price}</strong></p>
			<a  class="btn btn-dark" href="/placeOrder">Place Order</a>		
		</div>	
		
	
	
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		<script>
		function deleteFromCart(id){
			var quantity= document.getElementById('prod_'+id).value;
			
			 $.ajax({
			    url: '/addToCart',
			    type: 'GET',
			    dataType: 'json',
			    data:{
			          'product_id':id,
			          'quantity':-quantity,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);
			  }); 
			 $('.toast').toast('show');
			 document.getElementById('prod_row_'+id).hidden=true;				 
			}
			
	</script>
</body>
</html>