<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="header.jsp" />


    <div class="container">
        <h3>Choose The vendor</h3>
    
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
						<td><a href="/admin/chooseVendor/${product_id}/${vendor.supplier_id}" type="button" class="btn btn-secondary">Choose</a></td>								 
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
