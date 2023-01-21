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

import model.InsegnanteBean;
import model.InsegnanteMateriaBean;
import model.InsegnanteMateriaBeanDAO;
import model.MateriaBean;

/**
 * Servlet implementation class CancellaMateria
 */
@WebServlet("/CancellaMateria")
public class CancellaMateria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaMateria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String materiaCanc=request.getParameter("nomeMateriaCanc");
		HttpSession session=request.getSession();
		InsegnanteBean x=(InsegnanteBean) session.getAttribute("utente");
		
		InsegnanteMateriaBeanDAO dao=new InsegnanteMateriaBeanDAO();
		try {
			dao.doDelete(materiaCanc, x.getUsername());
			ArrayList<MateriaBean> materieInsegnate=(ArrayList<MateriaBean>)session.getAttribute("materieInsegnate");
			materieInsegnate.remove(new MateriaBean(materiaCanc));
			session.setAttribute("materieInsegnate", materieInsegnate);
			response.sendRedirect("modProfiloIns.jsp");
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
