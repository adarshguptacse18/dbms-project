<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />


	 <div class="container">
	 	By Price
	 	<form action="/filter/price"="GET">
            <div class="form-group">
                <label name="min_price">Min Price </label>
                <input name="min_price" type="number" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
             </div>
             <div class="form-group">
                <label name="max_price">Max Price </label>
                <input name="max_price" type="number" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
             </div>
             
             <div>
                <button class="btn btn-lg btn-primary  text-uppercase" type="submit">Set Filter</button><br><br>
             </div>
         </form>
         
         
         
         By Rating
         <form action="/filter/rating"="GET">
            <div class="form-group">
                <label name="min_rating">Min Rating </label>
                <input name="min_rating" type="number" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
             </div>
             <div class="form-group">
                <label name="max_rating">Max Rating </label>
                <input name="max_rating" type="number" class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
             </div>
             
             <div>
                <button class="btn btn-lg btn-primary  text-uppercase" type="submit">Set Filter</button><br><br>
             </div>
         </form>
   
	</div>
	
   <jsp:include page="footer.jsp" />
