<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<body>
	<nav class="navbar navbar-expand-md navbar-light">

    <a class="navbar-brand " href="/">
      <img src="/static/logo.png" height="60">
    </a>
    <!-- Collapse button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav1"
      aria-controls="basicExampleNav1" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>    
    <div class="collapse navbar-collapse" id="basicExampleNav1">
  
      <ol class="navbar-nav mr-auto">
        <li class="breadcrumb-item"><a class="waves-effect" href="/admin">Home</a></li>
        <li class="breadcrumb-item"><a class="waves-effect" href="/admin/addProduct">Add Product</a></li>
      </ol>
      <!-- Right -->
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a href="/myCart" class="nav-link navbar-link-2 waves-effect">
            <i class="fas fa-shopping-cart pl-0"></i>
          </a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle waves-effect" id="navbarDropdownMenuLink3" data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="true">
            <i class="united kingdom flag m-0"></i>
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
            <a class="dropdown-item" href="/admin/allCategories">All Categories</a>
            <a class="dropdown-item" href="/admin/addCategory">Add Category</a>
            <a class="dropdown-item" href="/admin/allVendors">All Vendors</a>
            <a class="dropdown-item" href="/admin/allProduct">All Products</a>
            <a class="dropdown-item" href="/admin/addProduct">Add Product</a>
            <a class="dropdown-item" href="/admin/allOrders">All Orders</a>
            <a class="dropdown-item" href="/admin/allTransactions">All Orders</a>        
          </div>
        </li>
        
        <li class="nav-item">
          <a href="#!" class="nav-link waves-effect">
            Contact Us
          </a>
        </li>
         <c:if test = "${empty username}" >
	        <li class="nav-item">
	          <a href="/login" class="nav-link waves-effect">
	            Sign in
	          </a>
	        </li>
	         <li class="nav-item pl-2 mb-2 mb-md-0">
	          <a href="#!" type="button"
	            class="btn btn-outline-info btn-md btn-rounded btn-navbar waves-effect waves-light">Sign up</a>
	        </li>
        </c:if>
        <c:if test = "${not empty username }">
        	<li class="nav-item pl-2 mb-2 mb-md-0">
	          <a href="/myProfile" type="button"
	            class="btn btn-outline-info btn-md btn-rounded btn-navbar waves-effect waves-light">${username}</a>
	        </li>
        </c:if>
      </ul>
    </div>
    <!-- Links -->
    
    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css">
  </nav>
</body>
