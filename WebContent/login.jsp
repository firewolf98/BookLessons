<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Prenota le tue ripetizioni</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css?ts=<?=time()?>&quot"">
</head>

<body>
	<%@ include file="header.jsp"%>
	<div class="container bg-white margin-30-top">
		<ol class="breadcrumb" itemscope=""
			itemtype="http://schema.org/BreadcrumbList">
			<li><a href="index.jsp">Home</a></li>
			<li class="active">Login</li>
		</ol>

		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<div class="well margin-30-top">
						<%String message=(String)request.getAttribute("message");
						if (message!=null && !message.equals("")){%>
							<h4 class="alert alert-danger"><%=message %></h4>
						<%} %>
							<form action="LoginServlet" method="post" id="form1">

								<div class="row">
									<div class="form-group col-md-6 col-xs-12 br-1px">
										<h3>Effettua il login</h3>

										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-user"></i></span>

											<input type="text" name="username" required="required"
												placeholder="Username" class="form-control" id="username">
										</div>
										<div class="input-group">
											<span class="input-group-addon"><i
												class="fa fa-asterisk"></i></span> <input type="password"
												name="password" required="required" placeholder="Password"
												class="form-control" id="password">
										</div>
										<hr>
										<button type="submit" class="btn btn-info btn-primary">
											<i class="fa fa-lock"></i> Login
										</button>
									</div>

								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</body>
</html>