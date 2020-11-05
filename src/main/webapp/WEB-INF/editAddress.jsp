<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${username !='admin' }">
   		 <jsp:include page="navbar.jsp" />
    </c:if>
    <c:if test="${username =='admin' }">
   		 <jsp:include page="adminNavBar.jsp" />
    </c:if>

	<div class="container">
	    <div class="row">
	      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
	        <div class="card card-signin my-5">
	          <div class="card-body">
	            <h5 class="card-title text-center"><b>Add Product</b></h5>
	            <form:form action="" class="form-signin" method="POST" modelAttribute="address" enctype = "multipart/form-data">
	              <div class="form-group">
	                <form:label path="house_no">House No</form:label>
	                <form:input path="house_no" type="text" class="form-control" required="required"/>
	                <div class="valid-feedback">Valid.</div>
	                <div class="invalid-feedback">Please fill out this field.</div>
	              </div>
	              <div class="form-group">
	                <form:label path="street_no">Street No</form:label>
	                <form:textarea path="street_no" type="text" class="form-control" required="required"/>      
	                <div class="valid-feedback">Valid.</div>
	                <div class="invalid-feedback">Please fill out this field.</div>
	              </div>
	               <div class="form-group">
	                <form:label path="locality_and_city">Locality And City</form:label>
	                <form:input path="locality_and_city" type="text" class="form-control" required="required"/>	
	                <div class="valid-feedback">Valid.</div>
	                <div class="invalid-feedback">Please fill out this field.</div>
	              </div>
	               <div class="form-group">
	                <form:label path="pincode">Pincode</form:label>
	                <form:input path="pincode" type="number" class="form-control" required="required"/>	
	                <div class="valid-feedback">Valid.</div>
	                <div class="invalid-feedback">Please fill out this field.</div>
	              </div>
	               <div class="form-group">
	                <form:label path="state">State</form:label>
	                <form:input path="state" type="number" class="form-control" required="required"/>
	                <div class="valid-feedback">Valid.</div>
	                <div class="invalid-feedback">Please fill out this field.</div>
	              </div>
	             
	              <div class="form-group">
	                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Update Address</button><br><br>
	              </div>
	            </form:form>
	          </div>
	        </div>
	      </div>
	    </div>

	</div>

	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		
</body>
</html>