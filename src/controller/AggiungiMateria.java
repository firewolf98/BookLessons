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
import model.InsegnanteMateriaBean;
import model.InsegnanteMateriaBeanDAO;
import model.MateriaBean;

/**
 * Servlet implementation class AggiungiMateria
 */
@WebServlet("/AggiungiMateria")
public class AggiungiMateria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiMateria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String materiaAgg=request.getParameter("nomeMateriaAgg");
		HttpSession session=request.getSession();
		InsegnanteBean x=(InsegnanteBean) session.getAttribute("utente");
		InsegnanteMateriaBean insMat=new InsegnanteMateriaBean(new MateriaBean(materiaAgg), x);
		
		InsegnanteMateriaBeanDAO dao=new InsegnanteMateriaBeanDAO();
		try {
			if(dao.doRetrieveByKey(materiaAgg,x.getUsername())==null) {
				dao.doSave(insMat);
				ArrayList<MateriaBean> materieInsegnate=(ArrayList<MateriaBean>)session.getAttribute("materieInsegnate");
				materieInsegnate.add(new MateriaBean(materiaAgg));
				session.setAttribute("materieInsegnate", materieInsegnate);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("modProfiloIns.jsp");
				requestDispatcher.forward(request, response);;
			}
			else {
				request.setAttribute("message", "La materia è già presente");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
				requestDispatcher.forward(request, response);
			}
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
