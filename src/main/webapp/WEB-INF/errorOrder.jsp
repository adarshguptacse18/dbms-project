<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp" />

	
		<div class="container">
			 <dl class="text text-danger">Error
			 <dt>Please decrease the quantity of these products</dt>
			 </dl>
		 	<table  class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Available Quantity</th>
						<th>Price</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${errorProds}" var="prod">
						<tr>
							<td>${prod.name}</td>
							<td>${prod.description}</td>	
							<td><input type="number" class="form-control" id="prod_${prod.product_id}" placeholder="190" name="password" value = ${prod.quantity} disabled></td>														
							<td>${prod.price}</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
			
			<a  class="btn btn-dark" href="/myCart">Go To My Cart</a>		
		</div>	
		
	
	

		<script>
		function deleteFromCart(id){
			 $.ajax({
			    url: '/addToCart',
			    type: 'GET',
			    dataType: 'json',
			    data:{
			          'product_id':id,
			          'quantity':0,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);
			  }); 
			}
		
	</script>
<jsp:include page="footer.jsp" />
