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
		<jsp:include page="finalNavBar.jsp" />
	
	<body class="bg-light">
    <div class="container">
        <div class="row d-flex justify-content-center">
            <div class="col-md-10 mt-5 pt-5">
                <div class="row z-depth-3">
                    <div class="col-sm-4 bg-info rounded-left">
                        <div class="card-block text-center text-white">
                            <i class="fas fa-user-tie fa-7x mt-5"></i>
                            <h2 class="font-weight-bold mt-4">${customer.user.username}</h2>
                            <a href="/editProfile" class="far fa-edit fa-2x mt-3" style="color:white;"></a>
                        </div>
                    </div>
                    <div class="col-sm-8 bg-white rounded-right">
                        <h3 class="mt-3 text-center">My Profile</h3>
                        <hr class="badge-primary mt-0 w-25">
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="font-weight-bold">Email:</p>
                                <c:if test="${not empty customer.user.email}"><h6 class="text-muted">${customer.user.email}</h6></c:if>
                                <c:if test="${empty customer.user.email}"><h6 class="text-muted">Not Available</h6></c:if>
                                
                            </div>
                            <div class="col-sm-6">
                                <p class="font-weight-bold">Phone:</p>
                                <c:if test="${not empty customer.numbers}"><h6 class="text-muted">${customer.numbers[0]}</h6></c:if>
                                <c:if test="${empty customer.numbers}"><h6 class="text-muted">Not Available</h6></c:if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="font-weight-bold">First Name:</p>
                                <c:if test="${not empty customer.user.first_name}"><h6 class="text-muted">${customer.user.first_name}</h6></c:if>
                                <c:if test="${empty customer.user.first_name}"><h6 class="text-muted">Not Available</h6></c:if>
                            </div>
                            <div class="col-sm-6">
                                <p class="font-weight-bold">Last Name:</p>
                                <c:if test="${not empty customer.user.last_name}"><h6 class="text-muted">${customer.user.last_name}</h6></c:if>
                                <c:if test="${empty customer.user.last_name}"><h6 class="text-muted">Not Available</h6></c:if>
                            </div>
                        </div>
                        <div class="row">
                        	 <div class="col-sm-6">
                                <p class="font-weight-bold">Username:</p>
                                <h6 class="text-muted">${customer.user.username}</h6>
                            </div>
                            <div class="col-sm-6">
                                <p class="font-weight-bold">GSTIN Number:</p>
                                <c:if test="${customer.GSTIN_NUMBER!=0}"><h6 class="text-muted">${customer.GSTIN_NUMBER}</h6></c:if>
                                <c:if test="${customer.GSTIN_NUMBER==0}"><h6 class="text-muted">Not Available</h6></c:if>
                            </div>
                            
                        </div>
                        <br class="bg-primary">
                        <ul class="list-unstyled d-flex justify-content-center">
                            <li><a href="/editProfile" class="btn btn-warning">Edit Profile</a></li>
                            <li><a href="/myPhoneNumbers" class="btn btn-success">All Phone Numbers</a></li>
                            <li><a href="/viewAddresses" class="btn btn-secondary">All Addresses</a></li>
                        </ul>
                        
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