package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.LezioneBean;

/**
 * Servlet implementation class RimuoviCarrelloLezione
 */
@WebServlet("/RimuoviCarrelloLezione")
public class RimuoviCarrelloLezione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviCarrelloLezione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String usernameIns=request.getParameter("insegnante");
		Date data=java.sql.Date.valueOf(request.getParameter("data"));
		int oraInizio=Integer.parseInt(request.getParameter("oraInizio"));
		int oraFine=Integer.parseInt(request.getParameter("oraFine"));
		Carrello cart=(Carrello) session.getAttribute("carrello");
		ArrayList<LezioneBean> lezioni=cart.getLezioni();
		
		synchronized(session){
			for (int i=0;i<lezioni.size();i++) {
				LezioneBean x=lezioni.get(i);
				if ((x.getInsegnante().getUsername().equals(usernameIns))&&(x.getDataLezione().equals(data))&&(x.getOraFine()==oraFine)&&(x.getOraInizio()==oraInizio))
					lezioni.remove(i);
			}
			session.setAttribute("lezioni", lezioni);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Cart.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
