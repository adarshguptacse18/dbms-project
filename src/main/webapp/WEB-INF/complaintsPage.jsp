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
			 <h3>Complaints</h3>
		
		 	<table  class="table">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Username</th>
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
		
	
	
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
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
</body>
</html>