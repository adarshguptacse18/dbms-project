<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<jsp:include page="finalNavBar.jsp" />
	
	<div class="container">
        <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
            <div class="card-body">
                <h5 class="card-title text-center"><b>Sign In</b></h5>
                
                 <form action="/forget_password" class="form-signin" method = "POST">
                 	<c:if test="${not empty error}">
							<div class="alert alert-danger alert-dismissible fade show">
							    <button type="button" class="close" data-dismiss="alert">&times;</button>
							    ${error}
						    </div>
					</c:if>
					
                 	
                    <div class="form-group">
                        <label for="email">Please Enter EmailId</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                  
                
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Send Reset Link</button><br><br>
                    </div>
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