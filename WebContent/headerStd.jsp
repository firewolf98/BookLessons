<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,model.*"%>
    
    <%StudenteBean x=(StudenteBean) session.getAttribute("utente"); %>
	
	<div class="container">
	<div class="col-md-12" align="center">
		<h2>Bentornato, <i><%=x.getNome() %></i></h2>
	

	</div>
	<div class="col-md-8">
	<p><h3><b>Email:</b> <%=x.getEmail() %></h3><br></div>
	<div class="col-md-4">
		  <h3> <b>Città:</b><%=x.getCitta() %></h3></div>
	</div>