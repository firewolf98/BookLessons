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

import org.json.JSONArray;

import model.InsegnanteBean;
import model.InsegnanteBeanDAO;
import model.StudenteBean;
import model.StudenteBeanDAO;

/**
 * Servlet implementation class VerificaPasswordVecchiaAjax
 */
@WebServlet("/VerificaPasswordVecchiaAjax")
public class VerificaPasswordVecchiaAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificaPasswordVecchiaAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray risJson = new JSONArray();
		String query = request.getParameter("p");

		HttpSession s=request.getSession();
		synchronized(s) {
			if (query != null) {
				String tipoUtente=(String) s.getAttribute("tipoUtente");
				if (tipoUtente.equals("studente")) {
					StudenteBean stud=(StudenteBean) s.getAttribute("utente");
					if (stud.getPassword().equals("query"))
						risJson.put(true);
				}
				else {
					if (tipoUtente.equals("insegnante")) {
						InsegnanteBean insegnante=(InsegnanteBean) s.getAttribute("utente");
						if (insegnante.getPassword().equals("query"))
							risJson.put(true);
					}
				}
			}
		}

			response.setContentType("application/json");
			response.getWriter().append(risJson.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
