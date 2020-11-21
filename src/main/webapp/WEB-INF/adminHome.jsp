<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />


			<div class="container">
			 <h3>Hey Admin</h3>
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
							<td><a href="/admin/AllProducts" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Show All Categories</td>
							<td><a href="/admin/AllCategories" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Show All Orders</td>
							<td><a href="/admin/Orders" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Show All Transactions</td>
							<td><a href="/admin/Transactions" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr >
							<td>Show All Vendors</td>
							<td><a href="/admin/allVendors" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Add Products</td>
							<td><a href="/admin/addProduct" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Add Categories</td>
							<td><a href="/admin/addCategory" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						
						<tr>
							<td>Show All Customers</td>
							<td><a href="/admin/allCustomers" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Find Customers</td>
							<td><a href="/admin/findCustomer" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>View All Complaints</td>
							<td><a href="/admin/allComplaints" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>View All Requests</td>
							<td><a href="/admin/allRequests" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Find Requests</td>
							<td><a href="/admin/findRequests" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Find Vendors</td>
							<td><a href="/admin/findVendors" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
				</tbody>
			</table>
				
		</div>	
	
<jsp:include page="footer.jsp" />
