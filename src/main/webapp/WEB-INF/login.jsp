<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	${error}</title>
</head>
<body>
	<jsp:include page="finalNavBar.jsp" />
	
	<div class="container">
        <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
            <div class="card-body">
                <h5 class="card-title text-center"><b>Sign In</b></h5>
                
                 <form action="/login" class="form-signin" method = "POST">
                 	<c:if test="${not empty error}">
							<div class="alert alert-danger alert-dismissible fade show">
							    <button type="button" class="close" data-dismiss="alert">&times;</button>
							    ${error}
						    </div>
					</c:if>
					<c:if test="${not empty successmessage}">
							  <div class="alert alert-success alert-dismissible">
                                   <button type="button" class="close" data-dismiss="alert">&times;</button>
                                   ${successmessage}
                              </div>
					</c:if>
                 	
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
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
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Login</button><br><br>
                        <a class="btn btn-lg btn-warning btn-block text-uppercase" href="/forget_password">Forgot Password?</a>
                    </div>
                    <small><p class="text-center">Not registered yet! <a href="/register"><b>Register Now!</b></a></p></small>
                </form>
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