<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<header>
<%String tipoUtente=(String) session.getAttribute("tipoUtente"); %>
	<nav id="topbar" class="navbar navbar-default navbar-fixed-top ">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse-main">
					<span class="sr-only"> Toggle navigation </span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp"><img
					class="img-responsive" src="img/logo.jpeg" alt="logo"></a>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse-main">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="index.jsp">Home</a></li>
				
				<% if (tipoUtente!=null){
				String pagina="";
				if (tipoUtente.equals("insegnante")) pagina="profiloIns.jsp";
				else if (tipoUtente.equals("studente")) pagina="profiloStud.jsp";
				else pagina="profiloAdmin.jsp";
				%>
			    <li><a href=<%=pagina%>>Profilo</a>	</li> <%} else{ %>
	
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Accedi<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><form action="LoginServlet" method="post">
					<div class="form-group" align="center">
            	<input type="text" required="required" placeholder="Username" name="username" class="form-control" style="width:140px">
        </div>
        
        <div class="form-group" align="center">     
            <input type="password" required="required" placeholder="Password" name="password" class="form-control" style="width:140px">
            
        </div>
        <div class="row" align="center">
            <div class="col-xs-12">
                <button class="btn btn-primary" type="submit">Accedi</button>
            </div>
            </div>
   
							</form>
							<hr class="divider">
							<div align="center">oppure</div><br>
							<div class="row" align="center">
							<a type="button" href="registrati.jsp" class="btn btn-primary" style="color:white">Registrati</a>
					</div>
					</ul>

								

					
				<%} %>
				
				<%if (tipoUtente==null||tipoUtente.equals("studente")){%><li><a href="Cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span></a></li>
				<%}if (tipoUtente!=null){ %>
				<li><a href="LogoutServlet">Logout</a></li>
				<%} %>
				
				<li><a  href="comeFunziona.jsp">Come Funziona</a>

				
		
			</ul>
		</div>
		</div>
	</nav>
</header>