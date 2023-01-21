package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InsegnanteBean;
import model.InsegnanteBeanDAO;
import model.MateriaBean;

/**
 * Servlet implementation class RicercaInsegnanti
 */
@WebServlet("/RicercaInsegnanti")
public class RicercaInsegnanti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaInsegnanti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	MateriaBean materia=new MateriaBean(request.getParameter("materia"));
    	String livello=request.getParameter("livello");
    	String citta=request.getParameter("citta");
    	
    	if (livello.equals("universita"))
    		livello="università";

    	//materia e città devono avere la prima lettera maiuscola
    	//materia.setNome(Character.toUpperCase(materia.getNome().charAt(0))+materia.getNome().substring(1,materia.getNome().length()));
    	//citta=Character.toUpperCase(citta.charAt(0))+citta.substring(1,citta.length());
    	HttpSession s=request.getSession();

    	synchronized(s) {
    		request.setAttribute("materia", materia.getNome());
    		request.setAttribute("livello", livello);
    		request.setAttribute("citta", citta);
    		
    		if (livello.equals("")||materia.equals("")) {
    			request.setAttribute("message", "Devi inserire materia e livello per poter effettuare la ricerca");
    			response.sendRedirect("errorPage.jsp");
    		}

    		s.setAttribute("materia", materia.getNome());
    		s.setAttribute("livello", livello);
    		s.setAttribute("citta", citta);

    		InsegnanteBeanDAO i=new InsegnanteBeanDAO();
    		try {
    			ArrayList<InsegnanteBean> insegnanti;
    			if (citta.equals(""))
    				insegnanti=i.doRetrievebyCondition(materia, livello);
    			else
    				insegnanti=i.doRetrievebyCondition(materia, livello, citta);
    			request.setAttribute("insegnanti", insegnanti);
    			s.setAttribute("insegnanti", insegnanti);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}

    		request.setAttribute("start", "0");
    		request.setAttribute("end", "5");
    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ricercaInsegnanti.jsp");
    		requestDispatcher.forward(request, response);
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
