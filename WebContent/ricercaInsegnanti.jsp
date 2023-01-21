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
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet"type="text/css" href="css/visIns.css">
</head>
<style>
.btn-yellow {
    background-color: #ffe700;
    color: #007dc3;
    border: 2px solid #ffe700;
    font-weight: 700;
    font-size: 16px;
}
.btn-block {
    display: block;
    width: 100%;
}
.btn-group-lg>.btn, .btn-lg {
    padding: 10px 16px;
    font-size: 18px;
    line-height: 1.3333333;
    border-radius: 6px;
}
</style>
<body>
	<!--Menu-->
	<%@include file="header.jsp"%>


	<div class="container">
		<h2>Risultato della ricerca</h2>

		<%
			ArrayList insegnanti = (ArrayList) session.getAttribute("insegnanti");
		%>
		<p>
			<%String citta=(String)session.getAttribute("citta"); %>
			Abbiamo trovato <b><%=insegnanti.size()%></b> 
			<%if (insegnanti.size()==1){ %>insegnante <%}else{  %> insegnanti <%} %>di <b><%=(String) session.getAttribute("materia")%></b>
			<%if (!citta.equals("")) {%>a <b><%=(String) request.getAttribute("citta")%></b><%}
			
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
			if (insegnanti.size() < end)
				end = insegnanti.size();

			int p;
			if (start == 0)
				p = 0;
			else
				p = Integer.parseInt(request.getParameter("page"));
			
			session.setAttribute("pagina","ricercaInsegnanti.jsp");
			%>
		<p>
		<div class="row">
			<div class="col-md-8">
				<%
					//for (int i = 0; i < insegnanti.size(); i++) {
						for (int i=start;i<end;i++){
						InsegnanteBean x = (InsegnanteBean) insegnanti.get(i);
				%>

				<div class="row">
					<div class="col-md-5">
						<%
							String foto = "data:image/jpg;base64," + x.getFoto();
						%>
						<img src=<%=foto%> class="img-responsive img-circle relative loading">
					</div>
					<div class="col-md-7">
						<h4><b><%=x.getNome()%>
							<%=x.getCognome()%></b></h4>
						<%
							String descrizione = x.getDescrizione();
								if (descrizione == null)
									descrizione = "";
						%>
						<p><%=descrizione%></p>
						<p>
							<b><%=x.getTariffaOraria() %>&euro;/ora</b>
						</p>
						<%
							if (tipoUtente == null || tipoUtente.equals("studente")) {
						%>
						<a href="InformazioniInsegnante?username=<%=x.getUsername()%>">
							<button type="button" class="btn btn-primary">Prenota</button>
						</a>
						<%
							} else if (tipoUtente != null && tipoUtente.equals("admin")) {
						%>
						<a href="EliminaInsegnante?username=<%=x.getUsername()%>">
							<button type="button" class="btn btn-primary">Elimina
								Account</button>
						</a>
						<%
							}
						%>
					</div>
				</div>

				<hr>
				<%
					}
				%>


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
							href="Paginazione?page=<%=p - 1%>">Previous</a></li> <%
 						}				
							int l = insegnanti.size();
							for (int j = 0; l>0; j++) {
								l = l / 5;
						%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=j%>"><%=j + 1%></a></li>
						<%
							}
							if (end < insegnanti.size() ) {
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

			<%
				if (insegnanti.size() != 0) {
			%>
			<div class="col-md-1"></div>
			<div class="col-md-3">
				<div class="panel">
					<div class="panel-heading"><span class="entry-title bb-1px padding-bottom-10 header-block-sidebar-search">
            <i class="fa fa-search-plus txt-primary"></i> AFFINA LA TUA RICERCA
        </span></div>
					<div class="panel-body">
						<form action="FiltraInsegnanti" method="get">
							<div class="form-group">
							<b>Tariffa oraria:</b><br>
							<div class="col-xs-5">
								 Da:<input type="number" class="form-control" id="minPrezzo" name="minPrezzo" value="${param.minPrezzo}">
							</div>
							<div class="col-xs-2"></div>
							<div class="col-xs-5">	
								A:<input type="number" class="form-control" id="maxPrezzo" name="maxPrezzo" value="${param.maxPrezzo}"><br>
							</div>
							</div>
							<b>Sesso:</b><br>
							<div class="form-check">
								<input type="radio" class="form-check-input" id="sessoM"
									name="sesso" value="M"> <label class="form-check-label"
									for="sessoM">Maschio</label><br> <input type="radio"
									class="form-check-input" id="sessoF" name="sesso" value="F">
								<label class="form-check-label" for="sessoF">Femmina</label><br>
							</div><br>
							<button type="submit" class=" btn btn-lg btn-block btn-yellow">
							<i class="fa fa-search"></i>CERCA INSEGNANTE
							</button>
						</form>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>