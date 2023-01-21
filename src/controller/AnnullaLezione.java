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
import model.InsegnanteBeanDAO;
import model.InsegnanteMateriaBean;
import model.InsegnanteMateriaBeanDAO;
import model.LezioneBean;
import model.LezioneBeanDAO;
import model.MateriaBean;

/**
 * Servlet implementation class AnnullaLezione
 */
@WebServlet("/AnnullaLezione")
public class AnnullaLezione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnullaLezione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		InsegnanteBean x=(InsegnanteBean) session.getAttribute("utente");
		int codLez=Integer.parseInt((String) request.getParameter("cod"));
		LezioneBeanDAO ldao=new LezioneBeanDAO();
		InsegnanteBeanDAO iDAO=new InsegnanteBeanDAO();
		try {
			ldao.doDelete(codLez);
			x.setRifiuti(x.getRifiuti()+1);
			iDAO.doUpdate(x);
			ArrayList<LezioneBean> lezioni=(ArrayList<LezioneBean>)session.getAttribute("lezioni");
			for (int i=0;i<lezioni.size();i++) {
				if(lezioni.get(i).getCodice()==codLez)
					lezioni.remove(i);
			}
			session.setAttribute("lezioni", lezioni);
			response.sendRedirect("profiloIns.jsp");
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
