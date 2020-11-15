<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <h5 class="card-title text-center"><b>Add Address</b></h5>
            <form action="" class="form-signin" method="POST">
              <div class="form-group">
                <label name="house_no">House No</label>
                <input name="house_no" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
               <div class="form-group">
                <label name="street_no">Street No</label>
                <input name="street_no" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <label name="locality_and_city">Locality And City</label>
                <input name="locality_and_city" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <label name="pincode">Pincode</label>
                <input name="pincode" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
             <div class="form-group">
                <label name="state">State</label>
                <input name="state" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
              <div class="form-group">
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Add Address</button><br><br>
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