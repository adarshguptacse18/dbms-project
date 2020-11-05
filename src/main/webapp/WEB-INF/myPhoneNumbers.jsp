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

	
	 <h3>My Phone Numbers</h3>
		<div class="container">
		 	<table  class="table">
				<thead>
					<tr>
						<th>Phone Number</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${numbers}" var="number">
						<tr id="number_row_${number}">
							<td>${number}</td>	
							<td><a href="/delete_number/${number}" type="button" class="btn btn-warning">Delete</a></td> 		
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
				
		</div>	
		<div class="container">
			<div class="alert alert-success" id="result_message" hidden=true>
			  <strong>Phone Number Added!!</strong> Refresh The Page to see the result
			</div>
		  <span class="btn btn-warning" onclick="showReview()">Add Phone Number</span>
		    <div class="form-group" id="addreview"  hidden=false>
		    
		      <input class="form-control" type="tel" rows="5" id="number" name="number"  pattern="[0-9]{5}[0-9]{2}[0-9]{3}">
     		    <button type="submit" onclick="addPhoneNumber()" class="btn btn-primary">Submit</button>
		      
		    </div>
		</div>
	<script>
		 function addPhoneNumber(){
		      var phone_number= document.getElementById("number").value;
		      
					 $.ajax({
					    url: '',
					    type: 'POST',
					    dataType: 'json',
					    data:{
					          'phone_number':phone_number,
					          },
			
					         })
					  .done(function(data) {
					  		console.log(data);
					  }); 
					  document.getElementById('addreview').hidden=true;
					  document.getElementById('result_message').hidden=false;
						  
			}
		function showReview(){
			document.getElementById('addreview').hidden=false;
		}

			
	</script>
	
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
		
</body>
</html>