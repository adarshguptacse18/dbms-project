<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp" />

		<div class="container">
			 <h3>Complaints</h3>
		
		 	<table  class="table">
				<thead>
					<tr>
						<th>Complaint Id</th>
						<th>User Id</th>
						<th>Email</th>
						<th>Message</th>
						<th>Is Resolved</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${complaints}" var="complaint">
						<tr id="">
							<td>${complaint.complaint_id}</td>						
							<td>${complaint.user_id}</td>
							<td>${complaint.email_id}</td>	
							<td>${complaint.message}</td>
							<c:if test="${complaint.is_resolved==true }">
								<td class="btn btn-success">Resolved</td> 
							</c:if>
							<c:if test="${complaint.is_resolved==false and role=='ROLE_ADMIN' }">
								<td><a href="/admin/resolve/${complaint.complaint_id}" class="btn btn-warning">Resolve This</a></td> 
							</c:if>
							<c:if test="${complaint.is_resolved==false and role!='ROLE_ADMIN' }">
								<td><span class="btn btn-warning">Not Resolved</a></td> 
							</c:if>
							<td>${complaint.date}</td>						
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
