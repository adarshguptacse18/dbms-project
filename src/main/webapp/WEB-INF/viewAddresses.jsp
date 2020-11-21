
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp" />



	
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
				<a href = "/addAddress" style="color:white">Add Address</a>
			</div>
						
		</div>	
	
	
	<script>
			
	</script>
	
	<jsp:include page="footer.jsp" />
