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
	
		<div class="container">
			 <h3>All Transactions</h3>
		
		 	<table  class="table">
				<thead>
					<tr>
						<th>Transaction Date</th>
						<th>Status</th>
						<th>Payment Method</th>		
						<th>Order ID</th>										
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${transactions}" var="transaction">
						<tr>
							<td>${transaction.date}</td>
							<td>${transaction.status}</td>
							<td>${transaction.payment_method}</td>	
							<td><a href="/admin/viewOrder/${transaction.order_id}">${transaction.transaction_id}</a></td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
				
		</div>	
		
	
	
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
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