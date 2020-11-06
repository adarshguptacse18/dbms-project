
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
	<c:if test="${username !='admin' }">
   		 <jsp:include page="navbar.jsp" />
    </c:if>
    <c:if test="${username =='admin' }">
   		 <jsp:include page="adminNavBar.jsp" />
    </c:if>

	
		<div class="container">
			 <h3> Address</h3>
		
		 	<table  class="table">
				<thead>
					<tr>
						<th>Addresses</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${addresses}" var="address">
						<tr id="number_row_${number}">
							<td>${address}</td>	
							<td><a href="deleteAddress/${address.address_id}" type="button" class="btn btn-warning">Delete</a></td> 		
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
			<div class="btn btn-primary">
				<a href = "addAddress/" style="color:white">Add Address</a>
			</div>
						
		</div>	
	
	
	<script>
			
	</script>
	
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		
</body>
</html>