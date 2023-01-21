package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DisponibilitaBean;
import model.DisponibilitaBeanDAO;
import model.InsegnanteBean;

/**
 * Servlet implementation class CancellaDisponibilita
 */
@WebServlet("/CancellaDisponibilita")
public class CancellaDisponibilita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaDisponibilita() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String giorno=request.getParameter("giorno");
		InsegnanteBean x=(InsegnanteBean) session.getAttribute("utente");
		
		DisponibilitaBeanDAO dao=new DisponibilitaBeanDAO();
		
		String redirect="modProfiloIns.jsp";
		try {
			dao.doDelete(giorno, x.getUsername());
			ArrayList<DisponibilitaBean> disponibili=(ArrayList<DisponibilitaBean>) session.getAttribute("disponibilita");
			for (int i=0;i<disponibili.size();i++) {
				DisponibilitaBean d=disponibili.get(i);
				if (d.getGiorno().equals(giorno))
					disponibili.remove(i);
			}
			session.setAttribute("disponibilita", disponibili);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(redirect);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
