<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

	 <div class="container">
	 	<form action="/admin/findCustomerById"="GET">
            <div class="form-group">
                <label name="user_id">Find By Customer Id </label>
                <input name="user_id" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
             </div>
             
             <div>
                <button class="btn btn-lg btn-primary  text-uppercase" type="submit">Find User</button><br><br>
             </div>
         </form>
   
	</div>
	<div class="container">
	 	<form action="/admin/findCustomerByEmail"="GET">
            <div class="form-group">
                <label name="email">Find By Customer Email </label>
                <input name="email" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
             </div>
             
             <div>
                <button class="btn btn-lg btn-primary  text-uppercase" type="submit">Find User</button><br><br>
             </div>
         </form>
   
	</div>
	<div class="container">
	 	<form action="/admin/findCustomerByUsername"="GET">
            <div class="form-group">
                <label name="username">Find By Username </label>
                <input name="username" type="text" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
             </div>
             
             <div>
                <button class="btn btn-lg btn-primary  text-uppercase" type="submit">Find User</button><br><br>
             </div>
         </form>
   
	</div>
	<jsp:include page="footer.jsp" />
