<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet" href="css/style.css">
<style>
input.er{
	border-style: solid;
	border-color:red;
}
</style>
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="container bg-white margin-30-top">
		<ol class="breadcrumb" itemscope=""
			itemtype="http://schema.org/BreadcrumbList">
			<li><a href="index.jsp">Home</a></li>
			<li class="active">Registrati Studente</li>
		</ol>

		<div class="row margin-30-top">
					<div  id="left-box" class="panel panel-default panel-white col-md-12 col-md-6 col-md-offset-3 bg-light">
							<h1 align="center">Inserisci i tuoi dati</h1>
							<form action="ServletRegStudente" method="post" id="form1">
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="nome" id="nome"
											placeholder="Nome" required>
										<span class="error">Il nome deve contenere solo lettere dell'alfabeto</span><br>	
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="cognome" id="cognome"
											placeholder="Cognome" required>
										<span class="error">Il cognome deve contenere solo lettere dell'alfabeto</span><br>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="email" class="form-control" name="email" id="email"
											placeholder="E-mail" required>
											<span class="error">E-mail non disponibile</span><br>
										<span class="error">Formato non corretto</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="citta" id="citta"
											placeholder="Città" required>
										<span class="error">Formato non corretto</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="username" id="username"
											placeholder="Crea username" required>
											<span class="error">Username non disponibile</span><br>
											<span class="error">L'username può contenere solo caratteri alfanumerici o _, deve essere lungo tra 3 e 20 caratteri</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="password" class="form-control" name="password" id="password"
											placeholder="Crea password" required>
											<span class="error">Non sono ammessi caratteri speciali, inoltre la password deve essere lunga almeno 8 caratteri</span><br>
									</div>
								</div>
								<div class="col-sm-offset-5 col-md-8">
									<button type="submit" class="btn btn-default">Registrati</button>
								</div>
							</form>
						</div>
						<div class="col-md-3"></div>
					</div>
				</div>

<script src="scripts/verificaUsername.js"></script>
<script src="scripts/verificaEmail.js"></script>
<script src="scripts/validazioneStudente.js"></script>
</body>
</html>