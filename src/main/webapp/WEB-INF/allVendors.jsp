<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />


    <div class="container">
        <h3>All vendors</h3>
    
        <table class="table">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>email</th>
                    <th>Username</th>
                    <th>Pancard Number</th>
                    <th>Company Name</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${vendors}" var="vendor">
                    <tr id="">
                        <td>${vendor.user.first_name}</td>
                        <td>${vendor.user.last_name}</td>
                        <td>${vendor.user.email}</td>
                        <td>${vendor.user.username}</td>
                        <td>${vendor.pancard_no}</td>
                        <td>${vendor.company_name}</td>
                        <td><span onclick="disableEnableUser(${vendor.supplier_id})"  class="btn btn-danger"> 
						<c:if test="${vendor.user.is_enabled==true }">
							<span id="hide_${vendor.supplier_id}" >Disable</span>
							<span id="show_${vendor.supplier_id}" hidden=true>Enable</span>
						</c:if>
						<c:if test="${vendor.user.is_enabled==false }">
							<span id="hide_${vendor.supplier_id}" hidden=true>Disable</span>
							<span id="show_${vendor.supplier_id}"  >Enable</span>
						</c:if>
						</span></td>
						<td><a href="/admin/viewEditPhoneNumber/${vendor.supplier_id}" type="button" class="btn btn-secondary">Phone Numbers</a></td>						
						<td><a href="/admin/productsByVendor/${vendor.supplier_id}" type="button" class="btn btn-primary">Products</a></td>
						<td><a href="/admin/complaints/${vendor.supplier_id}/" type="button" class="btn btn-warning">View All Complaints</a></td> 									 															 									
						<td><a href="/admin/editVendorProfile/${vendor.supplier_id}/" type="button" class="btn btn-warning">Edit Profile</a></td> 		 
                    </tr>
                </c:forEach>

            </tbody>
        </table>

    </div>



    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function disableEnableUser(vendor_id){
            
             $.ajax({
                url: '/admin/editProfile/disableEnableUser',
                type: 'POST',
                dataType: 'json',
                data:{
                      'user_id':vendor_id,
                      },
    
                     })
              .done(function(data) {
                    console.log(data);
                    
                     document.getElementById('hide_'+vendor_id).hidden=!document.getElementById('hide_'+vendor_id).hidden;
                     document.getElementById('show_'+vendor_id).hidden=!document.getElementById('show_'+vendor_id).hidden;
                     
              }); 
            }
            
            
    </script>
<jsp:include page="footer.jsp" />
