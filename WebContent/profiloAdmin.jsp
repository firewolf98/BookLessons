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
<link rel="stylesheet" href="css/visIns.css">
<title>Prenota le tue ripetizioni</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container">
		<%
			ArrayList insegnanti = (ArrayList) session.getAttribute("insegnantiAdmin");
			ArrayList<MateriaBean> materie = (ArrayList) session.getAttribute("materie");
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
			if (insegnanti.size()<end)
				end=insegnanti.size();

			int p;
			if (start == 0)
				p = 0;
			else
				p = Integer.parseInt(request.getParameter("page"));
			
			session.setAttribute("pagina","profiloAdmin.jsp");
			
		%>
		<div class="row">
			<div class="col-md-7">
				<h3 text-align="center">Insegnanti</h3>
				<%
					if (insegnanti != null) {
						for (int i = start; i < end; i++) {
							InsegnanteBean x = (InsegnanteBean) insegnanti.get(i);
				%>

				<div class="row">
					<div class="col-md-5">
						<%
							String foto = "data:image/jpg;base64," + x.getFoto();
						%>
						<img src=<%=foto%> class="img-responsive img-thumbnail">
					</div>
					<div class="col-md-7">
						<h4><%=x.getNome()%>
							<%=x.getCognome()%></h4>
						<p>
							<b>Numero rifiuti</b>:<%=x.getRifiuti()%></p>
						<%
							String descrizione = x.getDescrizione();
									if (descrizione == null)
										descrizione = "";
						%>
						<p><%=descrizione%></p>
						<a href="EliminaInsegnante?username=<%=x.getUsername()%>">
							<button type="button" class="btn btn-primary">Elimina
								Account</button>
						</a>
					</div>
				</div>
				<div style="height: 20px"></div>

				<%
					}
					}
				%>


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
							int l = insegnanti.size();
							for (int j = 0; l > 0; j++) {
								l = l / 5;
						%>
						<li class="page-item"><a class="page-link"
							href="Paginazione?page=<%=j%>"><%=j + 1%></a></li>
						<%
							}
							if (end < insegnanti.size()) {
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
			<div class="col-md-1"></div>
			<div class="col-md-4">
				<h3>Aggiungi materia</h3>
				<form action="InserisciMateriaDB">
					<input type="text" name="materia">
					<button type="submit" value="Inserisci Materia">Inserisci
						Materia</button>
				</form>
			</div>
		</div>
		</div>
</body>
</html>