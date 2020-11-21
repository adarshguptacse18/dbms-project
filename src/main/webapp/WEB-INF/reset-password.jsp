<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

	
	<div class="container">
        <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
            <div class="card-body">
                <h5 class="card-title text-center"><b>Sign In</b></h5>
                
                 <form action="${submit_url}" class="form-signin" method = "POST">
                 	<c:if test="${not empty error}">
							<div class="alert alert-danger alert-dismissible fade show">
							    <button type="button" class="close" data-dismiss="alert">&times;</button>
							    ${error}
						    </div>
					</c:if>
					
                 	
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                  
                     <div class="form-group">
                        <label for="confirm_password">Confirm Password</label>
                        <input type="password" class="form-control" id="confirm_password" placeholder="Enter confirm_password" name="confirm_password" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                  
                  
                
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Reset Password</button><br><br>
                    </div>
                </form>
            </div>
            </div>
        </div>
        </div>
    </div>
	
	
	
    <jsp:include page="footer.jsp" />
