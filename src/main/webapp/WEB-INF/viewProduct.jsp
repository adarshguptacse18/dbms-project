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
	<div class="container">
		  <div class="row">
		    <div class="col-md-6">
		      <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
		        <div class="carousel-inner">
		        <c:forEach items="${prod.image_path}" var="image">
   			          <div class="carousel-item active"> <img class="d-block" src="${image}"> </div>
		        </c:forEach>
		        </div>
		        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev"> <span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a> <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next"> <span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="sr-only">Next</span> </a> </div>
		    </div>
		    <div class="col-md-6">
		      <div class="row">
		        <h2>${prod.name}</h2>
		      </div>
		      <div class="row">
		        <h1><i class="fa fa-inr" aria-hidden="true"></i>Rs.${prod.price}</h1>
		      </div>
		      <div class="row">
		        <h5>Rating : ${prod.rating==0? 'Not Rated' : prod.rating}</h5>
		      </div>
		      <div class="row">
		      	<div class="text-secondary">
		      		${prod.description}
		      	</div>
		      </div>
		      <div class="row mt-4">
		        <h3 class="text-info"><i class="fa fa-map-marker" aria-hidden="true"></i></h3>
		        <p style="font-size: 20px"> &nbsp; Get Delivery  &nbsp; <span class="text-success">FREE</span> </p>
		     </div>
		     
		  </div>
		
		</div>
		
		<div class="container">
			<h3>Reviews:</h3>
			 <table class="table table-striped ">
			 	<thead>
			      <tr>
			        <th></th>
			      </tr>
			    </thead>
			    <tbody>
			    	<c:forEach items="${reviews}" var="review">
						<tr>
							<th class="text-secondary">
					      		${review.message}
					      	</th>
						</tr>
						
					</c:forEach>
			    </tbody>
			 </table>
			
		</div>
		
	</div>
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
			 document.getElementById('prod_'+id).value = '0'; 
				  
			}
		 function addReview(id){
		      var message= document.getElementById("message").value;
		      
					 $.ajax({
					    url: '/addReview/' + id,
					    type: 'POST',
					    dataType: 'json',
					    data:{
					          'message':message,
					          },
			
					         })
					  .done(function(data) {
					  		console.log(data);
					  }); 
					  document.getElementById('addreview').hidden=true;
					  document.getElementById('result_message').hidden=false;// 					 document.getElementById("message").value = ''; 
						  
			}
			function showReview(){
				document.getElementById('addreview').hidden=false;
			}
		
			
	</script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		
</body>
</html>