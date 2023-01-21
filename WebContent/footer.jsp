<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,model.*"%>
<footer>
	<%
	ArrayList<MateriaBean> materie=(ArrayList<MateriaBean>)session.getAttribute("materie");
	%>
		<div class="container footer-container">
			<div class="top-box col-xs-12">
				<div class="col-xs-12 col-sm-6 col-md-3 hidden-xs">
					<div class="col-md-12">
						<strong><p>Materie</p></strong>
						<hr>
						<%
							if (materie!=null){
							for (int i = 0; i < materie.size(); i++) {
								String mat=materie.get(i).getNome();
						%>
						<div class="col-md-3"><p><%=mat%></p></div>
						
						<%
							}
							}
						%>
					</div>
				</div>
			</div>
		
		<div class="top-box col-xs-12">
		<div class="col-xs-12 col-sm-6 col-md-3 hidden-xs">
                <div class="col-xs-12">
                    <strong>Hai bisogno di aiuto?</strong>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-11">
                    <p class="phone">
                        <i class="fa fa-phone fa-lg"></i>3889532837
                    </p>
                    <p>
                        Lun–Ven (9–13.30 / 14.30–18)
                    </p>

                    <div>
                        <strong>Come funziona</strong>
                        <hr>
                        <ul>
                            <li><a href="">FAQ Studente</a></li>
                            <li><a href="">FAQ Insegnante</a></li>
                        </ul>
                    </div>
                </div>
            </div>
     </div>
     </div>
      
</footer>