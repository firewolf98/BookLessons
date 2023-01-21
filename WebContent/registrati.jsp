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
<%@ include file="header.jsp" %>
<div class="container bg-white margin-30-top">
        <ol class="breadcrumb" itemscope="" itemtype="http://schema.org/BreadcrumbList">
            <li><a href="index.jsp">Home</a></li>
    <li class="active">Registrati</li>
    </ol>
    
    <div class="row">
            <div class="col-md-12">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="well margin-30-top">
                
                 
                    <h2>Come ti vuoi registrare?</h2>
                    <hr>


                    <div class="bottoni-top padding-10-top-bott">
                        <a href="registrazioneStudente.jsp" class="btn btn-info btn-block butt-studente"><small><label>Hai bisogno di ripetizioni?</label></small><br>REGISTRATI COME STUDENTE</a><hr>

                    </div>

                    <div class="bottoni-top padding-10-top-bott">
                        <a href="registrazioneInsegnante.jsp" class="btn btn-primary btn-block butt-tutor"><small><label>Vuoi insegnare?</label></small><br>REGISTRATI COME INSEGNANTE</a>
                    </div>

                    <hr>
                </div>
            </div>
        </div>
    </div>
                    </div>

    </div>

</body>
</html>