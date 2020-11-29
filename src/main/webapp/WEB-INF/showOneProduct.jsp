<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />


	<div class="container">
		  <div class="row">
		    <div class="col-md-6">
		      <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
		        <div class="carousel-inner">
		        <c:forEach items="${images}" var="image">
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
		        <h5>${prod.rating==0? 'Not Rated' : prod.rating}</h5>
		      </div>
		      <div class="row">
		        <h5>${prod.quantity<=0? 'Out Of Stock' : 'In Stock'}</h5>
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
		     <div class="row">
				<input type="number" min="1" max="5" class="col-sm-3" id="prod_${prod.product_id}" placeholder="Quantity" name="quantity" value = 1>
				<span class="col-sm-5 btn buy text-primary" onclick="addToCart(${prod.product_id})" type="button" class="btn btn-warning">
					<c:if test="${empty username}">
						<a href="/login" data-toggle="popover" title="Login to continue">
							Add To Cart
						</a>	
					</c:if>
					<c:if test="${not empty username}">
							Add To Cart
					</c:if>
				</span>
			</div>
		  </div>
		
		</div>
		
		<div class="container">
			<h3>Reviews:</h3>
			 <table id = "reviews" class="table table-striped ">
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
		<div class="container">
			<div class="alert alert-success" id="result_message" hidden=true>
			  <strong>Review Added!!</strong> Refresh The Page to see the result
			</div>
		  <span class="btn btn-warning" onclick="showReview()">Add Review</span>
		    <div class="form-group" id="addreview"  hidden=false>
		    
		      <textarea class="form-control" rows="5" id="message" name="message"></textarea>
     		    <button type="submit" onclick="addReview(${prod.product_id})" class="btn btn-primary">Submit</button>
		      
		    </div>
		</div>
	</div>
	
    <script>
		function addToCart(id){
			var quantity= document.getElementById('prod_'+id).value;
			try{
				var q = parseInt(quantity);
				if(q<=0){
		  			createAlert("Error","Quantity Must be greater than 0",null,"warning",true,true,"pageMessages");	
		  			return;
				}
				
			} catch(err){
	  			createAlert("Error","Something Unexpected occurred",null,"danger",true,true,"pageMessages");
	  			return;	
			}
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
			  		if(data['status']==true){
			  			createAlert("Product Addded","Product Added to the cart",null,"success",true,true,"pageMessages");	
				  	}
			  		else{
			  			createAlert("Product Can't be added","Something Unexpected occurred",null,"danger",true,true,"pageMessages");	
				  		
				  	}
			  		
			  })
			  .fail(function(data) {
				  console.log(data);
			  		if(data['status']==true){
			  			createAlert("Product Addded","Product Added to the cart",null,"success",true,true,"pageMessages");	
				  	}
			  		else{
			  			createAlert("Product Can't be added","Please login to continue",null,"danger",true,true,"pageMessages");	
				  		
				  	}
			  		
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
					  var markup = "<tr><th class='text-secondary'>"+message+"</th></tr>";
					  console.log(markup);
					  $("table tbody").append(markup); 
						  
			}
			function showReview(){
				document.getElementById('addreview').hidden=false;
			}
			
		
			
	</script>
	<jsp:include page="footer.jsp" />
