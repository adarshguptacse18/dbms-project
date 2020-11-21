<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="header.jsp" />

	
	 <h3>Choose The Address</h3>
		<div class="container">
           <div class="quiz" id="quiz" data-toggle="buttons">
		 	
				<c:forEach items="${addresses}" var="addr">
				   <label class="element-animation1 btn btn-lg btn-primary btn-block"><span class="btn-label"><i class="glyphicon glyphicon-chevron-right"></i></span> <input type="radio" name="q_answer"  value="${addr.key}">${addr.value}</label>
				</c:forEach>
	       </div>
					
				
		</div>	
		<a href="/addAddress">Add Address</a>
		
	
	
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
<jsp:include page="footer.jsp" />
