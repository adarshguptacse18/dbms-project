<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp" />

	
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
<jsp:include page="footer.jsp" />
