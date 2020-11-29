<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<jsp:include page="finalNavBar.jsp" />
	
		<div class="container">
			 <h3><strong>Choose The Address</strong></h3>
		
           <div class="quiz" id="quiz" data-toggle="buttons">
		 	
				<c:forEach items="${addresses}" var="addr">
				   <label class="element-animation1 btn btn-lg btn-primary btn-block"><span class="btn-label"><i class="glyphicon glyphicon-chevron-right"></i></span> <input type="radio" name="q_answer"  value="${addr.key}">${addr.value}</label>
				</c:forEach>
	       </div>
					
		<a class="btn btn-secondary" href="/addAddress">Add Address</a>
			
		</div>	
		
	
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		<script>
		$(function(){
		    var loading = $('#loadbar').hide();
		    $(document)
		    .ajaxStart(function () {
		        loading.show();
		    }).ajaxStop(function () {
		    	loading.hide();
		    });
		    
		    $("label.btn").on('click',function () {
		    	var choice = $(this).find('input:radio').val();
		    	$('#loadbar').show();
		    	$('#quiz').fadeOut();
		    	setTimeout(function(){
		           $( "#answer" ).html(  $(this).checking(choice) );      
		            $('#quiz').show();
		            $('#loadbar').fadeOut();
		           /* something else */
		    	}, 1500);
		    });

		    $ans = 3;

		    $.fn.checking = function(ck) {
			    console.log(ck);
			 // Simulate a mouse click:
			    window.location.href = "/payment?address_id=" + ck;
		    }; 
		});	

			
	</script>
</body>
</html>