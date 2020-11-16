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

	<jsp:include page="finalNavBar.jsp" />
	
	 <h3>My Orders</h3>
		<div class="container">
		 	<table  class="table">
				<thead>
					<tr>
						<th>Order Date</th>
						<th>Total Amount</th>
						<th>Status</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orders}" var="order">
						<tr id="prod_row_${order.order_id}">
							<td>${order.order_date}</td>
							<td>${order.amount}</td>	
							<td>${order.status}</td>
							<td><a href="/viewOrder/${order.order_id}" type="button" class="btn btn-warning">View Details</span></td> 		
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
				
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