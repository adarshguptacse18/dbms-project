<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp" />

		<div class="container">
		<jsp:include page="finalGreeting.jsp"/>
		 	<table  class="table table-striped  table-hover">
				<thead>
					<tr>
						<th>Category Id</th>
						<th>Category Name</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cat}" var="category">
						<tr>
							<td>${category.category_id}</td>
							<td>${category.category_name}</td>
							<td><a href="viewProducts/${category.category_id}" type="button" class="btn btn-warning">View Products</a></td>							
							<c:if test="${role=='ROLE_ADMIN'}"><td><a href="/admin/editCategory/${category.category_id}" type="button" class="btn btn-warning">Edit</a></td></c:if>
						</tr>
					</c:forEach>
							
						
				</tbody>
			</table>
				
		</div>	
		<script>
		
	</script>
<jsp:include page="footer.jsp" />
