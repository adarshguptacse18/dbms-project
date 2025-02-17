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
 				<jsp:include page="finalGreeting.jsp" />
		 	<table  class="table table-striped  table-hover">
				<thead>
					<tr>
						<th>Product Id</th>
						<th>Product Name</th>
						<th>Description</th>
						<th>Quantity</th>
						<th>Price</th>
						<th></th>
						<c:if test="${role=='ROLE_VENDOR' }"><th></th></c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${prods}" var="prod">
						<tr id = "prod_${prod.product_id}">
							<td>${prod.product_id}</td>
							<td>${prod.name}</td>
							<td>${prod.description}</td>
							<td>${prod.quantity}	
							<td>${prod.price}</td>
							<c:if test="${role=='ROLE_VENDOR' }">
							<td><span onclick="deleteFromSupplies(${prod.product_id})"  class="btn btn-warning"> 
								I don't deliver it.
							</span></td> 	
							</c:if>	
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
		function deleteFromSupplies(product_id){
			
			 $.ajax({
			    url: '/vendor/delete',
			    type: 'POST',
			    dataType: 'json',
			    data:{
			          'product_id':product_id,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);		
			  		document.getElementById('prod_'+product_id).hidden=true;			 
			  }); 
			}
			
			
	</script>
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</body>
</html>