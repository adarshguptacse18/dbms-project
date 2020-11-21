<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp" />


		<div class="container">
			 <h5>Order Id : ${order.order_id }</h5>
			 <dl> Total Amount: Rs.${order.amount }</dl>
			 <dl>Status : ${order.status }</dl>
		 	<table  class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Quantity</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${order.prods}" var="prod">
						<tr id="prod_row_${prod.product_id}">
							<td>${prod.name}</td>
							<td>${prod.description}</td>	
							<td><input type="number" class="form-control" id="prod_${prod.product_id}" placeholder="190" name="password" value = ${prod.quantity} disabled></td>														
							<td>${prod.price}</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
			<c:if test="${order.status!='CANCELLED' and order.status!='REFUNDED' }">
				<a href="cancelOrder/${order.order_id}" type="button" class="btn btn-danger">Cancel Order</a>
			</c:if>
			<c:if test="${order.status!='REFUNDED' }">
				<a href="refundOrder/${order.order_id}" type="button" class="btn btn-danger">Refund Order</a>
			</c:if>
				
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
