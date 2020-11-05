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
    <jsp:include page="adminNavBar.jsp" />
		
	 <h3>All Customers</h3>
		<div class="container">
		 	<table  class="table">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>email</th>
						<th>Username</th>
						<th>GSTIN_NUMBER</th>
						<th>Trust Value</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customers}" var="customer">
						<tr id="">
							<td>${customer.user.first_name}</td>
							<td>${customer.user.last_name}</td>	
							<td>${customer.user.email}</td>
							<td>${customer.user.username}</td>
							<td>${customer.GSTIN_NUMBER }</td>
							<td>${customer.trust_value }</td>		
							<td><a href="/admin/viewEditPhoneNumber/${customer.customer_id}" type="button" class="btn btn-secondary">Phone Numbers</span></td> 																
							<td><a href="/admin/Orders/${customer.customer_id}" type="button" class="btn btn-primary">View Orders</span></td> 									
							<td><a href="/admin/editProfile/${customer.customer_id}/" type="button" class="btn btn-warning">Edit Profile</span></td> 		
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