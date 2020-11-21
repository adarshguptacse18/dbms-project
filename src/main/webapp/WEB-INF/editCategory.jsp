<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />


<div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center"><b>Edit Category</b></h5>
            <form:form action="" class="form-signin" method="POST" modelAttribute="cat">
              <div class="form-group">
                <form:label path="category_name">Category Name</form:label>
                <form:input path="category_name" type="text" class="form-control" required="required"/>
                <form:errors path="category_name" cssClass="text-warning"/>
                
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
              </div>            
              <div class="form	-group">
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Update Category</button><br><br>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
</div>	
<jsp:include page="footer.jsp" />
