<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="adminNavBar.jsp" />

 <body> 
		<div class="container">
			 <h3>Hey Admin</h3>
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
						<th>Supplier ID</th>
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
							<c:if test="${role=='ROLE_ADMIN' }"><td><a href="/admin/editProduct/${prod.product_id}" type="button" class="btn btn-warning">Edit</a></td>
							<td><span onclick="hideProduct(${prod.product_id})"  class="btn btn-warning">
							<c:if test="${prod.hide==false }">
								<span id="hide_${prod.product_id}" >Hide</span>
								<span id="show_${prod.product_id}" hidden >Show</span>
							</c:if>
							<c:if test="${prod.hide==true }">
								<span id="hide_${prod.product_id}" hidden>Hide</span>
								<span id="show_${prod.product_id}"  >Show</span>
							</c:if>
<%-- 								<c:if test="${prod.hide==false}"><span id="hideProduct">Hide</span></c:if> --%>
<%-- 								<c:if test="${prod.hide==true }"><span id="showProduct">Show</span></c:if> --%>
							</span></td>
							<c:if test="${not empty prod.supplier_id and prod.supplier_id!=0}">
								<td><a href="/admin/editVendorProfile/${prod.supplier_id}" type="button" class="btn btn-warning">${prod.supplier_id}</a></td>
							</c:if>
							<c:if test="${ empty prod.supplier_id or prod.supplier_id==0}">
								<td><a href="#" type="button" class="btn btn-warning">Not Set</a></td>
							</c:if>
							
 							<td><a href="/admin/chooseVendor/${prod.product_id}" type="button" class="btn btn-warning">Choose Vendor</a></td>							 	
							 
							</c:if> 
								
							 		
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
		function hideProduct(product_id){
			
			 $.ajax({
			    url: '/admin/hideProduct',
			    type: 'POST',
			    dataType: 'json',
			    data:{
			          'product_id':product_id,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);
			  		
					 document.getElementById('hide_'+product_id).hidden=!document.getElementById('hide_'+product_id).hidden;
					 document.getElementById('show_'+product_id).hidden=!document.getElementById('show_'+product_id).hidden;
					 
			  }); 
			}
			
	</script>
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</body>
</html>