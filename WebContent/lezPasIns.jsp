<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
<title><%=((InsegnanteBean)session.getAttribute("utente")).getNome() %> <%=((InsegnanteBean)session.getAttribute("utente")).getCognome() %></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/visIns.css">
</head>
<body>
	<!--Menu-->

	<%@include file="header.jsp"%>

	<%@include file="headerIns.jsp"%>


		<%
			ArrayList lezioni = (ArrayList) session.getAttribute("lezioni");
			ArrayList<LezioneBean> lezioniPas=new ArrayList<>();
			Date today=new Date();
			for (int i = 0; i < lezioni.size(); i++) {
				LezioneBean y = (LezioneBean) lezioni.get(i);
				if(y.getDataLezione().before(today)) {
					lezioniPas.add(y);
				}
			}
			
			//paginazione
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
			if (lezioniPas.size() < end)
				end = lezioniPas.size();

			int p;
			if (start == 0)
				p = 0;
			else
				p = Integer.parseInt(request.getParameter("page"));
			
			session.setAttribute("pagina","lezPasIns.jsp");
		%>

<div class="container">
<div class="row"><div style="height: 20px"></div></div>
<div class="row">
			<div class="col-md-7">
		<%if (lezioniPas.size()!=0){ 
					for (int i = start; i < end; i++) {
						LezioneBean y = (LezioneBean) lezioniPas.get(i);
				%>

		<div class="row">
				<div class="col-md-5">
				<h4>		
					<br>Studente:
					<b><i><%=y.getStudente().getNome()%>
					<%=y.getStudente().getCognome() %></i></b>
				</h4>
				</div>
				<div class="col-md-5">
					<h4>Data Lezione:
					<b><i><%=y.getDataLezione()%></i></b>
					<br>Ora Inizio:
					<b><i><%=y.getOraInizio()%></i></b>
					<br>Ora Fine:
					<b><i><%=y.getOraFine()%></i></b>
					<br>Luogo:
					<b><i><%=y.getLuogo()%></i></b>
					<br>Costo:
					<b><i><%=y.getCosto()%></i></b>
					<br>Materia:
					<b><i><%=y.getMateria().getNome()%></i></b></h4>
				</div>
	
			
		</div>
		<div style="height: 20px"><hr></div>

		<%
					}
				%>
		<%} else{ %>
		<h4 class="text-center">Non ci sono lezioni da mostrare</h4>
		<%} %>
	</div>
	
	<div class="col-md-1"></div>
	
			<div class="col-md-5">
			
			<%if (lezioniPas.size()>0){ %>
			
	<div class="panel">
	<div class="panel-heading">
		<h3 class="text-center">Accredita Lezioni</h3>
		</div>
		<div class="panel-body">
		<form action="AccreditaLezione" method="post">
			<div class="col-md-5">
				<p>Codice Lezione:</p>
				<div class="form-group">
					<input type="text" class="form-control" name="codiceL"
						placeholder="codice lezione" required pattern="[0-9]">
				</div>
			</div>
			<div class="col-md-7">
				<p>Iban:</p>
				<div class="form-group">
					<input type="text" class="form-control" name="iban"
						placeholder="codice iban" required  maxlength="16" pattern="[0-9]{13,16}">
				</div>
			</div>

			<div align="center">
				<button type="submit" class="btn btn-default">Accredita</button>
			</div>
		</form>
		</div>
		</div>
			<%} %>
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
							int l = lezioniPas.size();
							for (int j = 0; l > 0; j++) {
								l = l / 5;
						%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=j%>"><%=j + 1%></a></li>
						<%
							}
							if (end < lezioniPas.size()) {
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

</body>
</html>