<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="finalNavBar.jsp" />

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
                <form:errors path="name" cssClass="text-warning"/>
                
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <form:label path="description">Description</form:label>
                <form:input path="description" type="text" class="form-control" required="required"/>
                <form:errors path="description" cssClass="text-warning"/>
                      
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <form:label path="price">Price</form:label>
                <form:input path="price" type="number" class="form-control" required="required"/>
                <form:errors path="price" cssClass="text-warning"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
               <div class="form-group">
             	  	<form:label path="category_id">Category</form:label>	
               	 	<form:label path="category_id">Category</form:label>
	                <form:errors path="name" cssClass="text-warning"/>           	 	
                        <form:select path="category_id" id="category" class="form-control">
                        	<c:forEach items="${categories}" var="category">
                          		 <option value="${category.category_id}">${category.category_name}</option>
							</c:forEach>                        
                       </form:select>
                
               </div>
              <%-- <div class="form-group">
                <form:label name="file">Add Image</form:label>
                <form:input type="file" name="file" class="form-control-file border" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div> --%>
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
	
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		
</body>
</html>