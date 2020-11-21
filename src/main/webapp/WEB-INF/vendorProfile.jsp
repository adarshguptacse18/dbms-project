<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
   
   
<jsp:include page="header.jsp" />

<link rel="stylesheet" type="text/css" href="/static/css/style.css">

    
    <body class="bg-light">
    <div class="container">
        <div class="row d-flex justify-content-center">
            <div class="col-md-10 mt-5 pt-5">
                <div class="row z-depth-3">
                    <div class="col-sm-4 bg-info rounded-left">
                        <div class="card-block text-center text-white">
                            <i class="fas fa-user-tie fa-7x mt-5"></i>
                            <h2 class="font-weight-bold mt-4">${vendor.user.username}</h2>
                            <a href="/editProfile" class="far fa-edit fa-2x mt-3" style="color:white;"></a>
                        </div>
                    </div>
                    <div class="col-sm-8 bg-white rounded-right">
                        <h3 class="mt-3 text-center">My Profile</h3>
                        <hr class="badge-primary mt-0 w-25">
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="font-weight-bold">Email:</p>
                                <c:if test="${not empty vendor.user.email}"><h6 class="text-muted">${vendor.user.email}</h6></c:if>
                                <c:if test="${empty vendor.user.email}"><h6 class="text-muted">Not Available</h6></c:if>
                                
                            </div>
                            <div class="col-sm-6">
                                <p class="font-weight-bold">Phone:</p>
                                <c:if test="${not empty phone_number}"><h6 class="text-muted">${phone_number[0]}</h6></c:if>
                                <c:if test="${empty phone_number}"><h6 class="text-muted">Not Available</h6></c:if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <p class="font-weight-bold">First Name:</p>
                                <c:if test="${not empty vendor.user.first_name}"><h6 class="text-muted">${vendor.user.first_name}</h6></c:if>
                                <c:if test="${empty vendor.user.first_name}"><h6 class="text-muted">Not Available</h6></c:if>
                            </div>
                            <div class="col-sm-6">
                                <p class="font-weight-bold">Last Name:</p>
                                <c:if test="${not empty vendor.user.last_name}"><h6 class="text-muted">${vendor.user.last_name}</h6></c:if>
                                <c:if test="${empty vendor.user.last_name}"><h6 class="text-muted">Not Available</h6></c:if>
                            </div>
                        </div>
                        <div class="row">
                             <div class="col-sm-6">
                                <p class="font-weight-bold">Username:</p>
                                <h6 class="text-muted">${vendor.user.username}</h6>
                            </div>
                            <div class="col-sm-6">
                                <p class="font-weight-bold">Pancard Number:</p>
                                <c:if test="${not empty vendor.pancard_no}"><h6 class="text-muted">${vendor.pancard_no	}</h6></c:if>
                                <c:if test="${empty vendor.pancard_no}"><h6 class="text-muted">Not Available</h6></c:if>
                            </div>
                            
                        </div>
                        <br class="bg-primary">
                        <ul class="list-unstyled d-flex justify-content-center">
                            <li><a href="editProfile" class="btn btn-warning">Edit Profile</a></li>
                            <li><a href="/myPhoneNumbers" class="btn btn-success">All Phone Numbers</a></li>
                        </ul>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
