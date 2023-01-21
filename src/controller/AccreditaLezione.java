package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InsegnanteBean;
import model.InsegnanteBeanDAO;
import model.InsegnanteMateriaBean;
import model.InsegnanteMateriaBeanDAO;
import model.LezioneBean;
import model.LezioneBeanDAO;
import model.MateriaBean;

/**
 * Servlet implementation class AccreditaLezione
 */
@WebServlet("/AccreditaLezione")
public class AccreditaLezione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccreditaLezione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		InsegnanteBean x=(InsegnanteBean) session.getAttribute("utente");
		
		String codiceLezione=request.getParameter("codiceL");
		if (!valida(codiceLezione)) {
			request.setAttribute("message", "Formato dei dati non corretto");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			int codLez=Integer.parseInt(codiceLezione);
			LezioneBean lez=null;
			LezioneBeanDAO ldao=new LezioneBeanDAO();
			try {
				ArrayList<LezioneBean> lezioni=(ArrayList<LezioneBean>)session.getAttribute("lezioni");
				lez=ldao.doRetrieveByKey(codLez);
				if (lez==null|| !lez.getInsegnante().getUsername().equals(x.getUsername())) {
					request.setAttribute("message", "Impossibile accreditare la lezione");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
					requestDispatcher.forward(request, response);
				}

				else{
					if(!lez.isAccreditata()) {
						lez.setAccreditata(true);
						for (int i=0;i<lezioni.size();i++) {
							if(lezioni.get(i).getCodice()==lez.getCodice())
								lezioni.get(i).setAccreditata(true);
						}
						ldao.doUpdate(lez);
						session.setAttribute("lezioni", lezioni);
						response.sendRedirect("profiloIns.jsp");
					}
					else {
						request.setAttribute("message", "La lezione selezionata è stata già accreditata");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
						requestDispatcher.forward(request, response);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean valida(String codice) {
		String expCodice="^[0-9]$";
		return Pattern.matches(expCodice, codice);
	}

}