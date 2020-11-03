<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <jsp:include page="navbar.jsp" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/static/css/style.css">

</head>
<body>
	
	<div class="toast">
	  <div class="toast-header">
	    Product Added to the Cart
	  </div>
	</div>
	<div class="container">
		<h3><strong>All Products</strong></h3>
	
		<div class="row">
			<c:forEach items="${prods}" var="prod">
				
					<!-- Product  -->
					<div class="col-md-4 product-grid">
						<div class="image">
							<a href="/showProduct?product_id=${prod.product_id}">
								<img src="${prod.image_path[0]}" class="w-100">
								<div class="overlay">
									<div class="detail">View Details</div>
								</div>
							</a>
						</div>
						<h5 class="text-center">${prod.name}</h5>
						<h5 class="text-center">Price: Rs.${prod.price}</h5>
						<div class="row">
							<input type="number" min="1" max="5" class="col-sm-3" id="prod_${prod.product_id}" placeholder="Quantity" name="quantity" value = 1>
							<span class="col-sm-9 btn buy" onclick="addToCart(${prod.product_id})" type="button" class="btn btn-warning">
								<c:if test="${empty username}">
									<span data-toggle="popover" title="Login to continue">
										Add To Cart
									</span>	
								</c:if>
								<c:if test="${not empty username}">
										Add To Cart
								</c:if>
							</span>
						</div>
					</div>
			</c:forEach>
		</div>
	</div>	
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		<script>
		function addToCart(id){
			var quantity= document.getElementById('prod_'+id).value;
			 $.ajax({
			    url: '/addToCart',
			    type: 'GET',
			    dataType: 'json',
			    data:{
			          'product_id':id,
			          'quantity':quantity,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);
			  }); 
		  	  $('.toast').toast('show');
		  		document.getEelmentById('prod_'+id).value = 1; 
				
			}
			
	</script>
</body>
</html>