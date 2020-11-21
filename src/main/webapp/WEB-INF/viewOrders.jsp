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
						<th>Rating</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${order.prods}" var="prod">
						<tr id="prod_row_${prod.product_id}">
							<td>${prod.name}</td>
							<td>${prod.description}</td>	
							<td><input type="number" class="form-control" id="prod_${prod.product_id}" placeholder="190" name="password" value = ${prod.quantity} disabled></td>														
							<td>${prod.price}</td>
							<td>${prod.rating}</td>
							<td><a href="/rateOrder/${order.order_id}/${prod.product_id}" type="button" class="btn btn-warning">Rate Product</a></td> 		
							
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
			
			<c:if test="${order.status!='CANCELLED' }">
				<a href="/cancelOrder/${order.order_id}" type="button" class="btn btn-danger">Cancel Order</a>
			</c:if>
				
		</div>	
		
	
	
		<jsp:include page="footer.jsp" />
