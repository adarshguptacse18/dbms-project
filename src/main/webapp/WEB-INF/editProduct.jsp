<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="navbar.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="toast">
  <div class="toast-header">
  	Image Deleted
  </div>
</div>
	 <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center"><b>Add Product</b></h5>
            <form:form action="" class="form-signin" method="POST" modelAttribute="prod" enctype = "multipart/form-data">
              <div class="form-group">
                <form:label path="name">Product Name</form:label>
                <form:input path="name" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <form:label path="description">Description</form:label>
                <form:textarea path="description" type="text" class="form-control" required="required"/>      
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
               <div class="form-group">
                <form:label path="price">Price</form:label>
                <form:input path="price" type="number" class="form-control" required="required"/>	
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
               <div class="form-group">
                <form:label path="quantity">Quantity</form:label>
                <form:input path="quantity" type="number" class="form-control" required="required"/>	
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
               <div class="form-group">
                <form:label path="category_id">Category</form:label>
                <form:input path="category_id" type="number" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              
              <div class="container">
			    <div class="row">
			      <div class="col-12">
			          <table class="table">
			            <thead>
			              <tr>
			                <th scope="col">Image</th>
			                <th scope="col"></th>
			              </tr>
			            </thead>
			            <tbody>
          	              	<c:forEach items="${prod.image_path}" var="image">             
				              <tr id = "${image}">
				                <td class="w-25">
				                    <img src="${image}" class="img-fluid">
				                </td>
				                <td>
									<span onclick="deleteImage(${prod.product_id},'${image}')"  class="btn btn-warning">Delete	</span> 		
				    			</td>
				              </tr>
				             </c:forEach>
			            
			            </tbody>
			          </table>   
			      </div>
			    </div>
			  </div>
          
              <div class="form-group">
              	<input type="file" name="file">
              </div>
              <div class="form-group">
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Add Product</button><br><br>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
	<script>
		function deleteImage(product_id,image_path){
			
			 $.ajax({
			    url: '/admin/deleteImage',
			    type: 'POST',
			    dataType: 'json',
			    data:{
			          'product_id':product_id,
			          'image_path':image_path,
			          },
	
			         })
			  .done(function(data) {
			  		console.log(data);
			  		
					 $('.toast').toast('show');
					 document.getElementById(image_path).hidden=true;
			  }); 
			}
			
	</script>
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		
</body>
</html>