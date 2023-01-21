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
<link rel="stylesheet" href="css/style.css">
<title>Prenota le tue ripetizioni</title>
</head>
<style>
#id1 {
	border-radius: 50%;
	width:100px;
	height: 100px;
	
}

.desc {
	text-align: center;
}

#cont {
	border: 1px solid grey;
}

#text {
	min-height: 150px;
	padding-top: 60px;
}
</style>
<body>

	<%@ include file="header.jsp"%>



	<%
		Carrello carrello = (Carrello) session.getAttribute("carrello");
	%>
	<%
		if (carrello != null) {
			int costoTot = 0;
			ArrayList<LezioneBean> lezioni = carrello.getLezioni();
	%>

	<div class="container" id="cont">
		<h3 class="text-center">Il mio carrello</h3>
		
		<%
			if (lezioni.size()>0){
			for (int i = 0; i < lezioni.size(); i++) {

					LezioneBean x = lezioni.get(i);
					costoTot += x.getCosto();
		%>

		<div class="row">
		<div class="col-md-5">

			<div>
				<%
					String foto = "data:image/jpg;base64," + x.getInsegnante().getFoto();
				%>

				<img src=<%=foto%> width="130" height="130" id="id1"
					style="float: left; padding-right: 3px;">

				<h4 class="center"><%=x.getInsegnante().getNome()%>
					<%=x.getInsegnante().getCognome()%></h4>

				<p>
					Insegnante di <b><%=x.getMateria().getNome()%></b> A <b><%=x.getInsegnante().getCitta()%></b>
				</p>
				<%String descrizione=x.getInsegnante().getDescrizione(); 
				if (descrizione==null) descrizione="";%>
				<p><%=descrizione%></p>
			</div>

		</div>
		<div class="col-md-3" id="text">

			<h4>
				Il:<%=x.getDataLezione()%></h4>

			<h4>
				Dalle:<%=x.getOraInizio()%>

				Alle:<%=x.getOraFine()%></h4>

			<h4>
				A:Casa
				<%=x.getLuogo()%></h4>
		</div>

		<div class="col-md-4" style="padding-top: 60px;">

			<h4 style="border: 4px solid grey; padding: 2px;">
				Costo lezione:
				<%=x.getCosto()%>&#8364
			</h4>
			<a href="RimuoviCarrelloLezione?insegnante=<%=x.getInsegnante().getUsername()%>&data=<%=x.getDataLezione()%>&oraInizio=<%=x.getOraInizio()%>&oraFine=<%=x.getOraFine()%>">
			<input type="button" class="btn btn-primary" value="Rimuovi" ></a>
		</div>
		</div>
		<div style="height: 20px"></div>
		<%
			}
		%>
		<div class="col-md-4" align="right">

			<h1>
				Totale:
				<%=costoTot%>&#8364
			</h1>

			<h2>
				<a href="ControlloLog"><input type="button"
					class="btn btn-primary" value="Procedi all'ordine"></a>
				<a href="RimuoviCarrelloLezioni"><input type="button"
					class="btn btn-primary" value="Rimuovi tutto"></a>
			</h2>
		</div>
		<%
		} else {
	%>
	<div align="center">
		<h2>
			<i>Il tuo carrello &egrave; vuoto</i>
		</h2>
	</div>
	<%}} else{%>
	<div align="center">
		<h2>
			<i>Il tuo carrello &egrave; vuoto</i>
		</h2>
	</div>
	<%}%>
	</div>
</body>
</html>