<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />


			<div class="container">
 				<jsp:include page="finalGreeting.jsp" />
		 	<table  class="table table-striped  table-hover">
				<thead>
					<tr>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td>Show All Products</td>
							<td><a href="/vendor/viewProducts" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Show All Categories</td>
							<td><a href="/vendor/AllCategories" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>	
						<tr>
							<td>Your Products</td>
							<td><a href="/vendor/myProducts" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>						
				</tbody>
			</table>
				
		</div>	
	
		<jsp:include page="footer.jsp" />
