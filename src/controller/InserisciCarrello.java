package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class InserisciCarrello
 */
@WebServlet("/InserisciCarrello")
public class InserisciCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String luogo= request.getParameter("luogo");
		//response.getWriter().print(request.getParameter("data"));
		HttpSession session=request.getSession();
		String inizio=(String) request.getParameter("oraInizio");
		String fine=(String) request.getParameter("oraFine");
		String date=request.getParameter("data");

		synchronized(session) {
			if (!date.equals("") && !inizio.equals("") && !fine.equals("") && !date.equals("")) {
				//try {

				//date = formatter.parse(request.getParameter("data"));
				//response.getWriter().print(date);
				//LezioneBeanDAO lezioneDao=new LezioneBeanDAO();
				InsegnanteBean insegnante=(InsegnanteBean) session.getAttribute("insegnante");
				//StudenteBeanDAO sdao=new StudenteBeanDAO();
				StudenteBean studente=(StudenteBean)session.getAttribute("utente");
				if (studente==null) {
					studente=new StudenteBean();
				}
				MateriaBean materia=new MateriaBean((String)session.getAttribute("materia"));
				int oraInizio=Integer.parseInt(inizio);
				int oraFine=Integer.parseInt(fine);
				int costo=insegnante.getTariffaOraria()*(oraFine-oraInizio);
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			//	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
				java.util.Date date1;
				try {
					date1 = sdf1.parse(date);
					java.sql.Date sqldate = new java.sql.Date(date1.getTime()); 
				
					LezioneBean lezione=new LezioneBean(0, oraInizio,oraFine, sqldate , costo, studente, insegnante, materia,false, false,luogo);

					Carrello carrello=(Carrello)session.getAttribute("carrello");
					if (carrello==null) {
						carrello=new Carrello();
					}
					carrello.aggiungiLezione(lezione);
					session.setAttribute("carrello", carrello);

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("Cart.jsp");
					requestDispatcher.forward(request, response);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				


				/*} catch (ParseException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/

			} else {
				request.setAttribute("message", "Devi inserire tutti i parametri per poter prenotare!");
				response.sendRedirect("errorPage.jsp");
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

}
