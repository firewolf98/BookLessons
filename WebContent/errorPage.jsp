<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css">
<title>Prenota le tue ripetizioni</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="well margin-30-top">
						<%String message=(String)request.getAttribute("message");
						if (!message.equals("")){%>
							<h4 class="alert alert-danger"><%=message %></h4>
						<%} %>
</div>
</body>
</html>