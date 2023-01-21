<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
<style>
div.row{
border:0px;
}
div.col-md-5{
margin-left:40px;
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

	<%StudenteBean x=(StudenteBean) session.getAttribute("utente");%>

	<div class="container">

		<div class="row">
		<div class="col-md-11" align="center"><h3>Qui puoi modificare i tuoi dati...</h3></div>
			<form action="ModificaDatiPersonali" method="post" id="form1">
				<div class="col-md-5">
					<p>Città:</p>
					<div class="form-group">
						<input type="text" class="form-control" name="citta" id="citta"
							value=<%=x.getCitta()%>>
						<span class="error">Formato errato</span><br>
					</div>
				</div>
				<div class="col-md-5">
					<p>Vecchia Password</p>
					<div class="form-group">
						<input type="password" class="form-control" name="passwordV" id="passwordV"
							placeholder="Vecchia password">
							<span class="error">La password non corrisponde alla tua password attuale</span><br>
					</div>
				</div>
				<div class="col-md-5">
					<p>Nuova Password</p>
					<div class="form-group">
						<input type="password" class="form-control" name="passwordN1"  id="passwordN1"
							placeholder="Inserisci password">
							<span class="error">Non sono ammessi caratteri speciali, inoltre la password deve essere lunga almeno 8 caratteri</span><br>
					</div>
				</div>
				<div class="col-md-5">
					<p>Conferma Nuova Password</p>
					<div class="form-group">
						<input type="password" class="form-control" name="passwordN2"  id="passwordN2"
							placeholder="Conferma password">
							<span class="error">La password non corrisponde alla password inserita</span><br>
					</div>
				</div>
				
				<div class="col-md-11" align="center">
				
					<button type="submit" class="btn btn-primary">Modifica
						dati</button>
						
				</div>
			</form>
		</div>
<script src="scripts/verificaFormModifica.js"></script>		
</body>
</html>