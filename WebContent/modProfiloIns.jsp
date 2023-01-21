<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
<style>
.form-control{
width:400px;
}
#exampleFormControlTextarea1{
 max-height:400px;
 max-width:400px;

 }
 input.er{
	border-style: solid;
	border-color:red;
}
</style>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/visIns.css">
<link rel="stylesheet" href="css/profIns.css">
<title>Prenota le tue ripetizioni</title>
</head>
<style>
input.er{
	border-style: solid;
	border-color:red;
}
</style>
<body>

	<!--Menu-->

	<%@include file="header.jsp"%>


	<%InsegnanteBean x=(InsegnanteBean) session.getAttribute("utente");
	String giorni[]={"lunedì", "martedì","mercoledì", "giovedì", "venerdì", "sabato", "domenica"};
	ArrayList<MateriaBean> materie=(ArrayList<MateriaBean>)session.getAttribute("materie");
	ArrayList<MateriaBean> materieInsegnate=(ArrayList<MateriaBean>)session.getAttribute("materieInsegnate");
%>

	<div class="container">

		<div class="row">
			<form action="ModificaDatiPersonali" method="post" enctype="multipart/form-data">
			<div align="center"><h3>Qui puoi modificare i tuoi dati...</h3></div>
				<div class="col-md-6">
					<p>Città:</p>
					<div class="form-group">
						<input type="text" class="form-control" name="citta" id="citta"
							value=<%=x.getCitta()%>>
							<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Vecchia Password:</p>
					<div class="form-group">
						<input type="password" class="form-control" name="passwordV" id="passwordV"
							placeholder="Vecchia password">
							<span class="error">La password non corrisponde alla tua password attuale</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Nuova Password:</p>
					<div class="form-group">
						<input type="password" class="form-control" name="passwordN1" id="passwordN1"
							placeholder="Inserisci password">
							
					</div>
				</div>
				<div class="col-md-6">
					<p>Nuova Password:</p>
					<div class="form-group">
						<input type="password" class="form-control" name="passwordN2" id="passwordN2"
							placeholder="Conferma password">
							<span class="error">La password non corrisponde alla password inserita</span><br>
					</div>
				</div>
				<div class="col-md-6">
					<p>Foto:</p>
					<div class="form-group">
						<input type="file" class="form-control" name="foto"
							 placeholder="Foto">
					</div>
				</div>
				<div class="col-md-6">
					<p>Tariffa Oraria:</p>
					<div class="form-group">
						<input type="number" class="form-control" name="tariffaOraria"
							value=<%=x.getTariffaOraria()%>>
					</div>
				</div>
				<div class="col-md-6">
					<p>Descrizione:</p>
					<div class="form-group">
						<textarea class="form-control rounded-0"
							id="exampleFormControlTextarea1" rows="10" name="descrizione">
							</textarea>
					</div>
				</div>
				<div class="col-sm-offset-5 col-md-8">
					<button type="submit" class="btn btn-primary">Modifica
						dati</button>
				</div>
			</form>
		</div>
		<br>

		<div class="row">
			<form action="AggiungiMateria" method="post">
				<div class="col-md-6">
				<br>
					<p>Aggiungi Materia:</p>
					<div class="form-group">
						<select name="nomeMateriaAgg" class="btn btn-default" >
							<%
								for (int i = 0; i < materie.size(); i++){
									String mat=materie.get(i).getNome();
									if (!(materieInsegnate.contains(materie.get(i)))){
							%>
							<option value=<%= mat%>><%= mat%></option>
							<%} %>
							<%} %>
						</select>
					</div>
				</div>
				<div class="col-sm-offset-5 col-md-8">
					<button type="submit" class="btn btn-primary">Aggiungi
						Materia</button>
				</div>
			</form>
			<form action="CancellaMateria" method="post">
				<div class="col-md-6">
					<p>Rimuovi Materia:</p>
						<select name="nomeMateriaCanc" class="btn btn-default" >
							<%	
								for (int i = 0; i < materieInsegnate.size(); i++){
									String mat=materieInsegnate.get(i).getNome();
							%>
							<option value=<%= mat%>><%= mat%></option>
							<%} %>
						</select>
					</div>
				<div class="col-sm-offset-5 col-md-8">
					<button type="submit" class="btn btn-primary">Rimuovi
						Materia</button>
				</div>
			</form>
		</div>

		<br>

		<div class="row">
			<form action="InserisciDisp" method="post">
				<div class="col-md-6"><br>
					<p>Aggiungi giorno:</p>
					<div class="form-group">
						<select class="btn btn-default" name="giorno" required>
							<%
								for (int i = 0; i < giorni.length; i++){
									String g=giorni[i];
							%>
							<option value=<%=g %>><%=g %></option>
							<%} %>
						</select>
					</div>
				</div>
				<div class="col-md-6">
					<p>Ora inizio:</p>
					<div class="form-group">
						<input type="number" class="form-control" name="oraInizio"
							placeholder="Ora inizio" required>
					</div>
				</div>
				<div class="col-md-6">
					<p>Ora fine:</p>
					<div class="form-group">
						<input type="number" class="form-control" name="oraFine"
							placeholder="Ora fine" required>
					</div>
				</div>
				<div class="col-sm-offset-5 col-md-8">
					<button type="submit" class="btn btn-primary">Aggiungi
						Giorno disponibile</button>
				</div>
				</form>
				<form action="CancellaDisponibilita" method="post">
				<div class="col-md-6">
					<p>Rimuovi giorno:</p>
					<div class="form-group">
						<select class="btn btn-default" name="giorno">
							<%ArrayList<DisponibilitaBean> giorniDisp=(ArrayList) session.getAttribute("disponibilita");
							for (int i=0;i<giorniDisp.size();i++){%>
							<option value=<%=giorniDisp.get(i).getGiorno()%>><%=giorniDisp.get(i).getGiorno() %></option>
							<% }%>
						</select>
					</div>
				</div>
				<div class="col-sm-offset-5 col-md-8">
					<button type="submit" class="btn btn-primary">Rimuovi
						Giorno disponibile</button>
				</div>
			</form>
	</div></div>


<script src="scripts/verificaFormModifica.js"></script>	
</body>
</html>