<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<style>
@media(min-width: 768px) {
  .field-label-responsive {
    padding-top: .5rem;
    text-align: right;
  }
}
</style>

<head>
    <!-- Standard Meta -->
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <!-- Site Properties -->
    <title>Add a product</title>

    <!-- Stylesheets -->
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<br><br><br>
<div class="container">
		<form method="post" action="/processpayment" class="form-horizontal" role="form">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h2>Payment</h2>
                <hr>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3 field-label-responsive">
                <label for="method">Method</label>
            </div>
            <select id="payment_method" name="payment_method">
				<option value="online" >Online Payment</option>
				<option value="offline" >Offline Payment</option>
			</select>
<!--             <div class="col-md-6"> -->
<!--                 <div class="form-group"> -->
<!--                     <div class="input-group mb-2 mr-sm-2 mb-sm-0"> -->
<!--                         <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-shopping-basket"></i> -->
<!--                         <select name="payment_method" path="payment_method"> -->
							
<!--                         </select> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
            
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group has-danger">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-inr"></i></div>
                        <input type="hidden" name="order_id" class="form-control" id="order_id"
                               placeholder="order_id" required="true" readonly value="${order_id}">
                    </div>
                </div>
            </div>
            
        </div>
        <div class="row">
            <div class="col-md-3 field-label-responsive">
                <label for="price">Price</label>
            </div>
            <div class="col-md-6">
                <div class="form-group has-danger">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-inr"></i></div>
                        <input type="text" name="price" class="form-control" id="price"
                               placeholder="Price" required="true" readonly value="${price}">
                    </div>
                </div>
            </div>
            
        </div>
        
        <c:forEach var="product" items="${items}">
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <input type="hidden" name="product_id" class="form-control" id="username"
                               placeholder="Name" required ="true" readonly value="${product.product_id}">
                    </div>
                </div>
            </div>
            
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <input type="hidden" name="quantity${product.product_id}" class="form-control" id="quantity"
                               placeholder="Quantity" required ="true" readonly value="${product.quantity}">
                    </div>
                </div>
            </div>
            
        </div>
        </c:forEach>
        <br><br>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-6">
                <button type="submit" class="btn btn-success"><i class="fa fa-user-plus"></i>Pay</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
