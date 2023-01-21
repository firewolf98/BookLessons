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

#left-box{
	background-color: transparent;
	padding:10px;
}


</style>
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="container bg-white margin-30-top">
		<ol class="breadcrumb" itemscope=""
			itemtype="http://schema.org/BreadcrumbList">
			<li><a href="index.jsp">Home</a></li>
			<li class="active">Registrati Insegnante</li>
		</ol>
				<div class="row margin-30-top">
					<div  id="left-box" class="panel panel-default panel-white col-md-12 col-md-6 col-md-offset-3 ">
							<h1 align="center">Inserisci i tuoi dati</h1>
							<form action="ServletRegInsegnante" method="post" id="form1"
								enctype="multipart/form-data">
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="nome" id="nome"
											placeholder="Nome" >
										<span class="error">Il nome deve contenere solo lettere dell'alfabeto</span><br>	
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="cognome" id="cognome"
											placeholder="Cognome" >
										<span class="error">Il cognome deve contenere solo lettere dell'alfabeto</span><br>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="email" class="form-control" name="email" id="email"
											placeholder="E-mail">
											<span class="error">E-mail non disponibile</span><br>
											<span class="error">Formato non corretto</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="citta" id="citta"
											placeholder="Città" >
											<span class="error">Formato non corretto</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="username" id="username"
											placeholder="Crea username" >	
											<span class="error" id="us">Username non disponibile</span>
											<span class="error">L'username può contenere solo caratteri alfanumerici o _, deve essere lungo tra 3 e 20 caratteri</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="password" class="form-control" name="password" id="password"
											placeholder="Crea password">
											<span class="error">Non sono ammessi caratteri speciali, inoltre la password deve essere lunga almeno 8 caratteri</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										Sesso: <input type="radio" name="sesso" value="M">
										Maschio <input type="radio" name="sesso" value="F">
										Femmina <br>
										<span class="error" id="sessoError">Sesso obbligatorio</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<select name="livello" id="livello" class="form-control">
											<option value="elementari">Elementari</option>
											<option value="medie">Medie</option>
											<option value="superiori">Superiori</option>
											<option value="università">Universita'</option>
										</select>
										<span class="error">Livello obbligatorio</span><br>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="file" class="form-control" name="foto"
											placeholder="Foto" required >
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="number" class="form-control" name="tariffaOraria" id="tariffa"
											placeholder="Tariffa Oraria">
										<span class="error">La tariffa deve essere compresa tra 10&euro; e 40&euro;</span><br>	
									</div>
								</div>
								<div class="col-sm-offset-5 col-md-8">
									<button type="submit" class="btn btn-default">Registrati</button>
								</div>
							</form>
						</div>
					</div>
				</div>
	

<script src="scripts/verificaUsername.js"></script>
<script src="scripts/verificaEmail.js"></script>
<script src="scripts/validazioneInsegnante.js"></script>
</body>
</html>


