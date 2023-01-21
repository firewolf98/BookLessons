<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
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
<link rel='stylesheet' href='https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css' />
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/visIns.css">
<title>Prenota le tue ripetizioni</title>

<style>
.Highlighted a{
   background-color : Green !important;
   background-image :none !important;
   color: White !important;
   font-weight:bold !important;
   font-size: 12pt;
}

select {
	color:black;
}

</style>
</head>
<body>

	<%@include file="header.jsp"%>
	
	<%ArrayList<VotoBean> voti = (ArrayList) session.getAttribute("recensioni");
	String s = (String) request.getAttribute("start");
	int start, end;
	if (s == null)
		start = 0;
	else
		start = Integer.parseInt(s);

	String e = (String) request.getAttribute("end");
	if (e == null)
		end = start + 5;
	else
		end = Integer.parseInt(e);
	if (voti.size() < end)
		end = voti.size();

	int p;
	if (start == 0)
		p = 0;
	else
		p = Integer.parseInt(request.getParameter("page")); %>

	<div class="container">
		<%
			InsegnanteBean insegnante = (InsegnanteBean) request.getAttribute("insegnante");
		%>
		<%
			String foto = "data:image/jpg;base64," + insegnante.getFoto();
			session.setAttribute("insegnante", insegnante);

			ArrayList<DisponibilitaBean> d = (ArrayList) request.getAttribute("disponibilita");
			String j = (String) request.getAttribute("j");
		%>

		<div class="row">
			<h2 class="text-center">
				Prenota la tua lezione con
				<%=insegnante.getNome()%></h2>
			<div class="col-md-8">
				<div class="col-md-5">
					<img src=<%=foto%> class="img-responsive img-thumbnail">
				</div>
				<div class="col-md-7">
					<h4><%=insegnante.getNome()%>
						<%=insegnante.getCognome()%></h4>
					<%
						String descrizione = insegnante.getDescrizione();
						if (descrizione == null)
							descrizione = "";
					%>
					<p><%=descrizione%></p>
					<p>
						<b><%=insegnante.getTariffaOraria()%>&euro;/ora</b>
					</p>
				</div>
			</div>

			<div class="col-md-1"></div>

			<div class="col-md-3">
				<%
					if (tipoUtente == null || tipoUtente.equals("studente")) {
				%>
				<div class="panel">
					<div class="panel-heading">
						<b><i>Prenota la tua lezione</b></i>
					</div>
					<div class="panel-body">

						<form action="InserisciCarrello" method="post" id="form1">

							<div class="form-check">
								<b><i>Scegli il luogo per la tua lezione:</b></i><br> <input
									class="radio-input" type="radio" value="insegnante"
									name="luogo"> Casa Insegnante
							</div>
							<div class="form-check">

								<input class="radio-input" type="radio" name="luogo"
									value="studente" name="luogo"> Casa studente

							</div>
							<div class="form-check">

								<input class="radio-input" id="online" name="luogo" type="radio"
									value="skype"> Online <span class="emphasize">(Skype)</span>
							</div>
							<br> <i><b>Scegli la data della tua lezione:</b></i>

							<!--<input type="date" name="data"><br><br>  -->
						
								<input type="text" class="form-control" name="data" id="txtDate"
									onChange="funct()"  autocomplete="off" data-date-format="yyyy/mm/dd">
							

							<br><i><b>Scegli l'ora per la tua lezione:</b></i><br>
							<p>Ora Inizio:<br>
							<select  name="oraInizio" id="oraInizio" 
								onChange="functi()">
							</select><br></p>
							<p>Ora Fine:<br>
							<select name="oraFine" id="oraFine">
							</select><br></p>
							 <input type="submit" class="btn btn-default"
								value="Prenota"></input>
						</form>
					</div>
					<%
						}
					%>
				</div>
			</div>
			<%
				
				VotoBean voto;
			%>
			<div class="row">
				<h2 class="text-center">
					Ecco cosa dicono di
					<%=insegnante.getNome()%></h2>
				<div class="col-md-12">

						<%
							for (int f = 0; f < voti.size(); f++) {
								voto = voti.get(f);
						%>
						<div class="row">
						<p><%=voto.getStudente().getNome()%>:
						</p>
						
						<%
							for (int i = 0; i < voto.getVoto(); i++) {
						%>
						<i class="fa fa-star"></i>
						<%
							}
						%>
						<p><%=voto.getCommento()%></p>
						</div>
						<div style="height: 20px"><hr></div>
						<%
							}
						%>

					</div>
			</div>
			
			
			<!-- paginazione -->
				<nav aria-label="...">
					<ul class="pagination">
						<%
							if (start == 0) {
						%>
						<li class="page-item disabled"><a class="page-link">Previous</a>
							<%
								} else {
							%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=p - 1%>">Previous</a></li>
						<%
							}
							int l = voti.size();
							for (int z = 0; l > 0; z++) {
								l = l / 5;
						%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=z%>"><%=z + 1%></a></li>
						<%
							}
							if (end < voti.size() - 1) {
						%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=p + 1%>">Next</a></li>
						<%
							} else {
						%>
						<li class="page-item disabled"><a class="page-link">Next</a>
						</li>
						<%
							}
						%>
					</ul>
				</nav>
			
			
			</div>
			</div>
		<script src="scripts/jquery.js"></script>
		<script type="text/javascript" src="scripts/datepicker.js"></script>
		<script type="text/javascript" src="scripts/datepicker.min.js"></script>
<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
		<script>			
			var g ="<%=request.getAttribute("giorniDisp")%>";
			var giorniCal=g.split(",");
			
			$(document).ready(function() {
				$('#txtDate').datepicker({
					dateFormat: "yy-mm-dd",
					 startDate: '+1d',
					 minDate: '+1d',
					beforeShowDay: function(date) {
				    	var day = date.getDay();      
				      	for (var j = 1; j < giorniCal.length; j++) {
				            if (giorniCal[j] == day) {
				            	 return [true, "Highlighted", ''];
				            }
				        }
			        return [false, '', ''];
			    }
				});
			});
		</script>
		<script type="text/javascript">
			var oraFine;
			function funct() {
				var jsvariable =<%=request.getAttribute("j")%>;
				var data = $('#txtDate').datepicker('getDate');
				var day = data.getUTCDay();
				var giorni = [ "lunedì", "martedì", "mercoledì", "giovedi",
						"venerdì", "sabato", "domenica" ];
				var oraInizio;
	

				for (var i = 0; i < jsvariable.length; i++) {
					if (jsvariable[i].giorno == giorni[day]) {
						oraInizio = jsvariable[i].oraInizio;
						oraFine = jsvariable[i].oraFine;
					}
				}
				var x = 1;
				var o1=document.getElementById("oraInizio");
				for (var z = oraInizio; z < oraFine; z++) {
					o1.options[x] = new Option(z, z);
					x++;
				}
			}

			function functi() {
				var o1 = document.getElementById("oraInizio");
				var o2 = document.getElementById("oraFine");
				var x = 0;
				var v = parseInt(o1.value) + 1;
				for (var z = v; z <= oraFine; z++) {
					o2.options[x] = new Option(z, z);
					x++;
				}
			}
		</script>
		<script src="scripts/validaPrenotazione.js"></script></body>
</html>