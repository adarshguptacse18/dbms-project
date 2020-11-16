<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
<jsp:include page="finalNavBar.jsp" />

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
	
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</body>
</html>