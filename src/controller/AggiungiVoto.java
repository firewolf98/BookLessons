package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import model.MateriaBean;
import model.StudenteBean;
import model.VotoBean;
import model.VotoBeanDAO;

/**
 * Servlet implementation class AggiungiVoto
 */
@WebServlet("/AggiungiVoto")
public class AggiungiVoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiVoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ins=request.getParameter("insegnante");
		InsegnanteBean i=new InsegnanteBean();
		InsegnanteBeanDAO iDAO=new InsegnanteBeanDAO();
		try {
			i=iDAO.doRetrieveByKey(ins);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int v=Integer.parseInt(request.getParameter("voto"));
		String des=request.getParameter("commento");
		HttpSession session=request.getSession();
		StudenteBean x=(StudenteBean) session.getAttribute("utente");
		GregorianCalendar today=new GregorianCalendar();
		int m=today.get(GregorianCalendar.MONTH)+1;
		String date=today.get(GregorianCalendar.YEAR)+"-"+m+"-"+today.get(GregorianCalendar.DAY_OF_MONTH);
		VotoBean insVoto=new VotoBean(x,i,v,des,java.sql.Date.valueOf(date));
		VotoBeanDAO vDAO=new VotoBeanDAO();
		try {
			vDAO.doSave(insVoto);
			response.sendRedirect("lezPasStud.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
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
