<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<jsp:include page="header.jsp" />

	 <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center"><b>Rate The Product</b></h5>
            <form action="" class="form-signin" method="POST">
              <div class="form-group">
                <label name="rating">Enter Rating b/w 1 and 5</label>
                <input name="rating" type="number" min=1 max=5 class="form-control" required="required"/>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>
             
              <div class="form-group">
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Rate</button><br><br>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
	</div>
  <jsp:include page="footer.jsp" />
