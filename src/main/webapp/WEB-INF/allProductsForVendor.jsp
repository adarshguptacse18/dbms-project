<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="header.jsp" />


 <body> 
		<div class="container">
			 <h3>Hey Vendor</h3>
		 	<table  class="table table-striped  table-hover">
				<thead>
					<tr>
						<th>Product Id</th>
						<th>Product Name</th>
						<th>Description</th>
						<th>Quantity</th>
						<th>Price</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${prods}" var="prod">
						<tr>
							<td>${prod.product_id}</td>
							<td>${prod.name}</td>
							<td>${prod.description}</td>
							<td>${prod.quantity}	
							<td>${prod.price}</td>
							<td><span onclick="supply(${prod.product_id})"  class="btn btn-warning"> 
								I have this item
							</span></td> 		
							<td><a href="/vendor/viewProduct/${prod.product_id}" type="button" class="btn btn-warning">View</a></td>							 		
						</tr>
					</c:forEach>
							
						
				</tbody>
			</table>
				
		</div>
			
	
		<script>
		function myFunction(b){
			document.getElementById('hideProduct').hidden=!b;
			document.getElementById('showProduct').hidden=b;
		}
		function supply(product_id){
			
			 $.ajax({
			    url: '/vendor/supply',
			    type: 'POST',
			    dataType: 'json',
			    data:{
			          'product_id':product_id,
			          },
	
			         })
			  .done(function(data) {
				  if(data["status"]==true)
		  			createAlert("Product Addded To My Products","Product Added",null,"success",true,true,"pageMessages");
				  else 	
			  			createAlert("Already Added",data["message"],null,"warning",true,true,"pageMessages");
				  
					  
			  }); 
			}
			
			
	</script>
<jsp:include page="footer.jsp" />
