<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="adminNavBar.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
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
							<td><a href="/admin/Vendors" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Add Products</td>
							<td><a href="/admin/Products" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
						<tr>
							<td>Add Categories</td>
							<td><a href="/admin/Categories" type="button" class="btn btn-warning">Go To</a></td>	
						</tr>
				</tbody>
			</table>
				
		</div>	
	
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</body>
</html>