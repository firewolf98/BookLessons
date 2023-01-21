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
 * Servlet implementation class FiltraInsegnanti
 */
@WebServlet("/FiltraInsegnanti")
public class FiltraInsegnanti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltraInsegnanti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s=request.getSession();
	//	ArrayList<InsegnanteBean> i=(ArrayList<InsegnanteBean>) s.getAttribute("insegnanti");

		String materia=(String) s.getAttribute("materia");
		String citta=(String) s.getAttribute("citta");
		String livello=(String)s.getAttribute("livello");
		InsegnanteBeanDAO iDao=new InsegnanteBeanDAO();
		
		String min=request.getParameter("minPrezzo");
		int minPrezzo;
		if (min==null||min.equals("")) minPrezzo=-1;
		else minPrezzo=Integer.parseInt(min);
		
		String max=request.getParameter("maxPrezzo");
		int maxPrezzo;
		if (max==null||max.equals("")) maxPrezzo=-1;
		else maxPrezzo=Integer.parseInt(max);
		
		String sesso=request.getParameter("sesso");
		
		try {
			ArrayList<InsegnanteBean> i;
			if (citta.equals(""))
				i=iDao.doRetrievebyCondition(new MateriaBean(materia), livello);
			else
				i = iDao.doRetrievebyCondition(new MateriaBean(materia), livello, citta);
		
			if (minPrezzo!=-1||maxPrezzo!=-1)
				i=iDao.doRetrievebyCondition(i, minPrezzo, maxPrezzo);
			
			if (sesso!=null)
				i=iDao.doRetrievebyCondition(i, sesso.charAt(0));
			
			request.setAttribute("insegnanti", i);
			s.setAttribute("insegnanti", i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		request.setAttribute("materia", materia);
		request.setAttribute("citta", citta);
		request.setAttribute("minPrezzo", minPrezzo);
		request.setAttribute("maxPrezzo", maxPrezzo);
		request.setAttribute("sesso", sesso);
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
