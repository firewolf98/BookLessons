package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VisualizzaInsegnanti
 */
@WebServlet("/PaginazioneInsegnanti")
public class PaginazioneInsegnanti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginazioneInsegnanti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		ArrayList insegnanti=(ArrayList) s.getAttribute("insegnanti");
		request.setAttribute("insegnanti", insegnanti);
		int p=Integer.parseInt(request.getParameter("page"));
		int start=p*5;
		request.setAttribute("start", String.valueOf(start));
		request.setAttribute("end",String.valueOf(start+5));
	
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ricercaInsegnanti.jsp");
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
