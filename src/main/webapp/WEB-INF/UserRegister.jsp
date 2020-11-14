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
	 <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center"><b>Edit Profile</b></h5>
            <form:form action="" class="form-signin" method="POST" modelAttribute="customer">
              <div class="form-group">
                <form:label path="user.first_name">First Name</form:label>
                <form:input path="user.first_name" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <form:label path="user.last_name">Last Name</form:label>
                <form:input path="user.last_name" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <form:label path="user.username">Username</form:label>
                <form:input path="user.username" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <form:label path="user.email">Email</form:label>
                <form:input path="user.email" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <form:label path="user.password">Password</form:label>
                <form:input path="user.password" type="password" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
               <div class="form-group">
                <form:label path="GSTIN_NUMBER">GSTIN Number</form:label>
                <form:input path="GSTIN_NUMBER" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Update Profile</button><br><br>
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