package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.LezioneBean;
import model.LezioneBeanDAO;
import model.OrdineBean;
import model.OrdineBeanDAO;
import model.StudenteBean;

/**
 * Servlet implementation class EffettuaAcquisto
 */
@WebServlet("/EffettuaAcquisto")
public class EffettuaAcquisto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EffettuaAcquisto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		LezioneBeanDAO daoL=new LezioneBeanDAO();
		OrdineBeanDAO daoO=new OrdineBeanDAO();
		StudenteBean stud=(StudenteBean) session.getAttribute("utente");
		
		String cardNumber=request.getParameter("cardNumber");
		String cvv=request.getParameter("cvv");
		
	/*	if (!valida(cardNumber,cvv)) {
			request.setAttribute("message", "Formato errato dei dati");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
			requestDispatcher.forward(request, response);
		}*/
		
		Carrello carrello=(Carrello) session.getAttribute("carrello");
		if (carrello!=null) {
			int costoTot=0;
			ArrayList<LezioneBean> lezioni=carrello.getLezioni();
			
			//calcolo il costo totale dell'ordine 
			for (int i=0;i<lezioni.size();i++) {
				LezioneBean l=lezioni.get(i);
				costoTot+=l.getCosto();
			}
			
			
			
			
			for (int i=0;i<lezioni.size();i++) {
				LezioneBean l=lezioni.get(i);
				if (l.getStudente().getUsername()==null) {
					l.setStudente(stud);
				}
			}
			
			carrello.setLezioni(lezioni);
			
			ArrayList<LezioneBean> lezioniStud=(ArrayList) session.getAttribute("lezioni");
			
			//creo il nuovo ordine
			OrdineBean ordine=new OrdineBean(0,costoTot, new java.sql.Date(new Date().getTime()));
			try {
				boolean x=daoO.doSave(ordine);
				int codiceOrdine=daoO.doRetrieveByCondition();
				for (int i=0;i<lezioni.size();i++) {
					LezioneBean l=lezioni.get(i);
					ordine.setIdOrdine(codiceOrdine);
					l.setOrdine(ordine);
					daoL.doSave(l);
					l.setCodice(daoL.doRetrieveByCondition());
					lezioniStud.add(l);
				}
				lezioni.removeAll(lezioni);
				carrello.setLezioni(lezioni);
				session.setAttribute("carrello",carrello );
				session.setAttribute("lezioni", lezioniStud);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Cart.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e1) {
				e1.printStackTrace();
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

	/*private boolean valida(String cvv, String cardNumber) {
		boolean valido=true;
		String expcvv="^[0-9]{3,4}$";
		String expcard="^[0-9]{13,16}$";
		if (!Pattern.matches(expcvv, cvv))
			valido=false;
		if (!Pattern.matches(expcard, cardNumber))
			valido=false;
		return valido;
	}*/
}
