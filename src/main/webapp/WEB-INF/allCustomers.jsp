<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp" />

		
		<div class="container">
			 <h3>Customers</h3>
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
							<td><span onclick="disableEnableUser(${customer.customer_id})"  class="btn btn-danger"> 
							<c:if test="${customer.user.is_enabled==true }">
								<span id="hide_${customer.customer_id}" >Disable</span>
								<span id="show_${customer.customer_id}" hidden >Enable</span>
							</c:if>
							<c:if test="${customer.user.is_enabled==false }">
								<span id="hide_${customer.customer_id}" hidden>Disable</span>
								<span id="show_${customer.customer_id}"  >Enable</span>
							</c:if>
<%-- 								<c:if test="${prod.hide==false}"><span id="hideProduct">Hide</span></c:if> --%>
<%-- 								<c:if test="${prod.hide==true }"><span id="showProduct">Show</span></c:if> --%>
							</span></td> 																							
							<td><a href="/admin/viewEditPhoneNumber/${customer.customer_id}" type="button" class="btn btn-secondary">Phone Numbers</a></td>
							<td><a href="/admin/viewAllAddresses/${customer.customer_id}/" type="button" class="btn btn-secondary">Addresses</a></td> 																						 																
							<td><a href="/admin/Orders/${customer.customer_id}" type="button" class="btn btn-primary">View Orders</a></td>
							<td><a href="/admin/complaints/${customer.customer_id}/" type="button" class="btn btn-warning">View All Complaints</a></td> 									 									
							<td><a href="/admin/editProfile/${customer.customer_id}/" type="button" class="btn btn-warning">Edit Profile</a></td> 		
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
				
		</div>	
		
	
	
	
		<script>
		function disableEnableUser(customer_id){
			
			 $.ajax({
			    url: '/admin/editProfile/disableEnableUser',
			    type: 'POST',
			    dataType: 'json',
			    data:{
			          'user_id':customer_id,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);
			  		
					 document.getElementById('hide_'+customer_id).hidden=!document.getElementById('hide_'+customer_id).hidden;
					 document.getElementById('show_'+customer_id).hidden=!document.getElementById('show_'+customer_id).hidden;
					 
			  }); 
			}
			
			
	</script>
<jsp:include page="footer.jsp" />
	