<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	User Registration </title>
</head>
<body>
		
	<form action="/upload" method="POST" enctype = "multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="Upload Image"></input> 
	</form>	
	
</body>
</html>