<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Prenota le tue ripetizioni </title>
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
<link rel="stylesheet" href="css/style.css">
<style>
input.er{
	border-style: solid;
	border-color:red;
}
</style>
</head>


<body>
	<!--Menu-->
	<%@include file="header.jsp"%>

	<div class="jumbotron" >
		<div id="contentj">
			<div align="center">
				<h2>Trova i migliori insegnanti per le tue ripetizioni</h2>
				<form class="form-inline" action="RicercaInsegnanti">
					<div class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span> <input type="text"
							class="form-control" id="materia" placeholder="Inserisci materia"
							name="materia" required list="ricerca-datalist" onkeyup="ricercaMateria(this.value)">
							<datalist id="ricerca-datalist"></datalist>
					</div>
					<div class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-education"></i></span> <select
							class="form-control" id="sel1" name="livello">
							<option value="universita">Università</option>
							<option value="elementari">Elementari</option>
							<option value="superiori">Superiori</option>
							<option value="medie">Medie</option>
						</select>
					</div>
					<div class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-map-marker"></i></span> <input type="text"
							class="form-control" id="citta" placeholder="Inserisci città"
							name="citta">
					</div>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-search"></span> Search
					</button>
				</form>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-4 text-center">
				<div>
					<i class="fa fa-search circle-icon"></i>
					<h3>Trova l'insegnante perfetto</h3>
					<hr>
					<p>Cerca il tuo insegnante tra i nostri insegnanti qualificati</p>
				</div>
			</div>
			<div class="col-md-4 text-center">
				<div>
					<i class="fa fa-calendar circle-icon"></i>
					<h3>Prenota la tua lezione</h3>
					<hr>
					<p>Prenota la lezione a casa dell'insegnante, casa tua oppure
						via Skype</p>
				</div>
			</div>
			<div class="col-md-4 text-center">
				<div>
					<i class="fa fa-check-square circle-icon "></i>
					<h3>Migliora i tuoi voti</h3>
					<hr>
					<p>Migliora i tuoi voti con sole poche ore di lezione</p>
				</div>
			</div>
		</div>

		<div class="col-md-5 riga"></div>
		<%ArrayList<VotoBean> slide=(ArrayList) session.getAttribute("slide");
		if (slide==null) response.sendRedirect("Commenti"); 
		else{%>
		<div class="row" >
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<div class="col-md-3 text-center">
						<%VotoBean slide1=slide.get(0); %>
							<%
							String foto1 = "data:image/jpg;base64," + slide1.getInsegnante().getFoto();
							%>
						<img src=<%=foto1%> class="img-circle">
						</div>
						<div class="col-md-9">
							<h3><%=slide1.getStudente().getNome()%> ha utilizzato il servizio di BookLessons.
							Ecco cosa pensa del suo insegnante <%=slide1.getInsegnante().getNome()%></h3>
							<blockquote><%=slide1.getCommento()%></blockquote>
							<%for (int i=0;i<slide1.getVoto();i++){ %>
							<i class="fa fa-star"></i>
							<%} %>
						</div>
					</div>

					<div class="item">
						<div class="col-md-3 text-center">
						<%VotoBean slide2=slide.get(1); %>
							<%
							String foto2 = "data:image/jpg;base64," + slide2.getInsegnante().getFoto();
							%>
						<img src=<%=foto2%> class="img-circle">
						</div>
						<div class="col-md-9">
							<h3><%=slide2.getStudente().getNome()%> ha utilizzato il servizio di BookLessons.
							Ecco cosa pensa del suo insegnante <%=slide2.getInsegnante().getNome()%></h3>
							<blockquote><%=slide2.getCommento()%></blockquote>
							<%for (int i=0;i<slide2.getVoto();i++){ %>
							<i class="fa fa-star"></i>
							<%} %>
						</div>
					</div>

					<div class="item">
						<div class="col-md-3 text-center">
						<%VotoBean slide3=slide.get(2); %>
							<%
							String foto3 = "data:image/jpg;base64," + slide3.getInsegnante().getFoto();
							%>
						<img src=<%=foto3%> class="img-circle">
						</div>
						<div class="col-md-9">
							<h3><%=slide3.getStudente().getNome()%> ha utilizzato il servizio di BookLessons.
							Ecco cosa pensa del suo insegnante <%=slide3.getInsegnante().getNome()%></h3>
							<blockquote><%=slide3.getCommento()%></blockquote>
							<%for (int i=0;i<slide3.getVoto();i++){ %>
							<i class="fa fa-star"></i>
							<%} %>
						</div>
					</div>
				</div>
				

				<!-- Left and right controls -->
				<a class="left carousel-control" style="background:none;" href="#myCarousel"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="right carousel-control" style="background:none;" href="#myCarousel"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<%} %>
	</div>


	<%@include file="footer.jsp"%>
<script src="scripts/jquery.js"></script>
<script src="scripts/ricercaMateria.js"></script>
<script>
$(document).ready(function(){
	$("nav li").hover(
		function(){
			$("ul", this).delay(50).slideDown(100);
		},
		function(){
			$("ul", this).slideUp(100);
		}
	);
});
</script>
</body>
</html>