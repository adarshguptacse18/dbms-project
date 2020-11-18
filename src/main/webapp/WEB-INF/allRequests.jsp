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
<%-- 	<jsp:include page="alert.jsp"/> --%>
- 		<div class="container"> 
<!-- 			 <h3>Requests</h3> -->
<!-- 			 				  <button class="btn btn-danger" onclick="createAlert('Opps!','Something went wrong','Here is a bunch of text about some stuff that happened.','danger',true,false,'pageMessages');">Add Danger Alert</button> -->
			 
		
		 	<table  class="table">
				<thead>
					<tr>
						<th>Request Id</th>
						<th>Email Id</th>
						<th>Name</th>
						<th>Subject</th>
						<th>Message</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requests}" var="request">
						<tr id="request_${request.request_id}">
							<td>${request.request_id}</td>						
							<td>${request.email_id}</td>
							<td>${request.name}</td>	
							<td>${request.subject}</td>
							<td>${request.message}</td>
							<td><span class="btn btn-danger" onclick="deleteRequest(${request.request_id})">Delete Request</span></td> 		
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
				
		</div>	
		
	
	
	
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		<script>
		function deleteRequest(request_id){
			 $.ajax({
			    url: '/admin/deleteRequest',
			    type: 'POST',
			    dataType: 'json',
			    data:{
			    	'request_id':request_id,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);
			  }); 
			  document.getElementById("request_"+request_id).hidden=true;
				  
			}
			
			
	</script>
	
</body>
</html>