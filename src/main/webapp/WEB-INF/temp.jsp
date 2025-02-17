<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="adminNavBar.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#pageMessages {
  position: fixed;
  bottom: 15px;
  right: 15px;
  width: 30%;
}

.alert {
  position: relative;
}

.alert .close {
  position: absolute;
  top: 5px;
  right: 5px;
  font-size: 1em;
}

.alert .fa {
  margin-right:.3em;
}
</style>
</head>
<body>	
	
 				<div id="pageMessages">

					</div>
					<div class="jumbotron">
					  <div class="container">
					    <h1>Let's create some Alerts</h1>
					  </div>
					</div>
					<div class="container">
					  <button class="btn btn-danger" onclick="createAlert('Opps!','Something went wrong','Here is a bunch of text about some stuff that happened.','danger',true,false,'pageMessages');">Add Danger Alert</button>
					  <button class="btn btn-success" onclick="createAlert('','Nice Work!','Here is a bunch of text about some stuff that happened.','success',true,true,'pageMessages');">Add Success Alert</button>
					  <button class="btn btn-info" onclick="createAlert('BTDubs','','Here is a bunch of text about some stuff that happened.','info',false,true,'pageMessages');">Add Info Alert</button>
					  <button class="btn btn-warning" onclick="createAlert('','','Here is a bunch of text about some stuff that happened.','warning',false,true,'pageMessages');">Add Warning Alert</button>
					</div>
				
		</div>	
		<script>
		function createAlert(title, summary, details, severity, dismissible, autoDismiss, appendToId) {
			  var iconMap = {
			    info: "fa fa-info-circle",
			    success: "fa fa-thumbs-up",
			    warning: "fa fa-exclamation-triangle",
			    danger: "fa ffa fa-exclamation-circle"
			  };

			  var iconAdded = false;

			  var alertClasses = ["alert", "animated", "flipInX"];
			  alertClasses.push("alert-" + severity.toLowerCase());

			  if (dismissible) {
			    alertClasses.push("alert-dismissible");
			  }

			  var msgIcon = $("<i />", {
			    "class": iconMap[severity] // you need to quote "class" since it's a reserved keyword
			  });

			  var msg = $("<div />", {
			    "class": alertClasses.join(" ") // you need to quote "class" since it's a reserved keyword
			  });

			  if (title) {
			    var msgTitle = $("<h4 />", {
			      html: title
			    }).appendTo(msg);
			    
			    if(!iconAdded){
			      msgTitle.prepend(msgIcon);
			      iconAdded = true;
			    }
			  }

			  if (summary) {
			    var msgSummary = $("<strong />", {
			      html: summary
			    }).appendTo(msg);
			    
			    if(!iconAdded){
			      msgSummary.prepend(msgIcon);
			      iconAdded = true;
			    }
			  }

			  if (details) {
			    var msgDetails = $("<p />", {
			      html: details
			    }).appendTo(msg);
			    
			    if(!iconAdded){
			      msgDetails.prepend(msgIcon);
			      iconAdded = true;
			    }
			  }
			  

			  if (dismissible) {
			    var msgClose = $("<span />", {
			      "class": "close", // you need to quote "class" since it's a reserved keyword
			      "data-dismiss": "alert",
			      html: "<i class='fa fa-times-circle'></i>"
			    }).appendTo(msg);
			  }
			  
			  $('#' + appendToId).prepend(msg);
			  
			  if(autoDismiss){
			    setTimeout(function(){
			      msg.addClass("flipOutX");
			      setTimeout(function(){
			        msg.remove();
			      },1000);
			    }, 5000);
			  }
			}
					</script>
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</body>
</html>