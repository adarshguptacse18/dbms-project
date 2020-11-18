<div id="pageMessages" class="container">

</div>
		<style>
		.top-text-block{
			  display: block;
			  padding: 3px 20px;
			  clear: both;
			  font-weight: 400;
			  line-height: 1.42857143;
			  color: #333;
			  white-space: inherit !important;
			  border-bottom:1px solid #f4f4f4;
			  position:relative;
			  &:hover {
			        &:before {
			        content: '';
			        width: 4px;
			        background: #f05a1a;
			        left: 0;
			        top: 0;
			        bottom: 0;
			        position: absolute;
			    }
			  }
			  &.unread {
			    background:#ffc;
			    
			    // &:hover {
			    //   background:#ffd;
			    // }
			  }
			  
			  .top-text-light {
			    // color:#ccc;
			    color: #999;
			    font-size: 0.8em;
			  }
			}   
			
			.top-head-dropdown {
			  .dropdown-menu {
			   width: 350px;
			    height:300px;
			    overflow:auto;
			  }
			  
			  li:last-child{
			    .top-text-block {
			      border-bottom:0;
			    }
			  }
			}
			.topbar-align-center {
			  text-align: center;
			}
			.loader-topbar {
			  margin: 5px auto;
			  border: 3px solid #ddd;
			  border-radius: 50%;
			  border-top: 3px solid #666;
			  width: 22px;
			  height: 22px;
			  -webkit-animation: spin-topbar 1s linear infinite;
			  animation: spin-topbar 1s linear infinite;
			}
			
			@-webkit-keyframes spin-topbar {
			  0% { -webkit-transform: rotate(0deg); }
			  100% { -webkit-transform: rotate(360deg); }
			}
			
			@keyframes spin-topbar {
			  0% { transform: rotate(0deg); }
			  100% { transform: rotate(360deg); }
			}
		</style>
				
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
	