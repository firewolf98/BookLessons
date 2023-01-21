<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
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
<link rel="stylesheet" href="css/style.css?ts=<?=time()?>&quot">
<link rel="stylesheet" href="css/visIns.css?ts=<?=time()?>&quot">
<link rel="stylesheet" href="css/profIns.css?ts=<?=time()?>&quot">
<title><%=((InsegnanteBean)session.getAttribute("utente")).getNome() %> <%=((InsegnanteBean)session.getAttribute("utente")).getCognome() %></title>
</head>
<body>

	<!--Menu-->
	<%@include file="header.jsp"%>


	
	<%@include file="headerIns.jsp" %>

	<br>
	<div style="text-align:center">
		<a class="btn btn-primary" href="modProfiloIns.jsp">Modifica il tuo profilo</a>
	</div>
	<hr>
	<div class=" margin-top-bottom">
		<div class="block">
			<div class="col-md-6">
				<a class="lessons" href="lezProIns.jsp">Prossime lezioni</a>
			</div>
			<div class="col-md-6">
				<a class="lessons" href="lezPasIns.jsp">Lezioni passate</a>
			</div>
		</div>

	</div>


</body>
</html>