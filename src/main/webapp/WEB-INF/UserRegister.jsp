<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	User Registration </title>
</head>
<body>
	
	<div class="container">
        <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
            <div class="card-body">
                <h5 class="card-title text-center"><b>Sign Up</b></h5>
                
                 <form action="/register" class="form-signin" method = "POST">
                 	<c:if test="${not empty error}">
							<div class="alert alert-danger alert-dismissible fade show">
							    <button type="button" class="close" data-dismiss="alert">&times;</button>
							    ${error}
						    </div>
					</c:if>
					<c:if test="${not empty logout}">
							 <div class="alert alert-warning alert-dismissible fade show">
							    <button type="button" class="close" data-dismiss="alert">&times;</button>
							    ${logout}
						    </div>
					</c:if>
                 	
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" class="form-control" id="email" placeholder="Enter Email" name="email" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                	<div class="form-group">
                        <label for="first_name">First Name</label>
                        <input type="text" class="form-control" id="first_name" placeholder="Enter first_name" name="first_name" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label for="last_name">Last Name</label>
                        <input type="text" class="form-control" id="last_name" placeholder="Enter first_name" name="last_name" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Register</button><br><br>
                    </div>
                    <small><p class="text-center">Already Have an Account! <a href="/login"><b>Login Now!</b></a></p></small>
                </form>
            </div>
            </div>
        </div>
        </div>
    </div>
	
	
	
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</body>
</html>