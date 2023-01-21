package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InsegnanteBeanDAO;
import model.MateriaBean;
import model.MateriaBeanDAO;
import model.StudenteBeanDAO;
import model.VotoBean;
import model.VotoBeanDAO;

/**
 * Servlet implementation class Commenti
 */
@WebServlet("/Commenti")
public class Commenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Commenti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		ArrayList<VotoBean> slide=new ArrayList<>();
		synchronized (session) {
			VotoBeanDAO votodao=new VotoBeanDAO();
			try {
				ArrayList<VotoBean> voti=votodao.doRetrieveAll();
				Random random=new Random();
				for (int i=0;i<3;i++) {
					//int index=random.nextInt(voti.size());
					int index=random.ints(0,(voti.size())).findFirst().getAsInt();
					VotoBean voto=voti.get(index);
					slide.add(voto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			MateriaBeanDAO matDao=new MateriaBeanDAO();
			try {
				ArrayList<MateriaBean> materie=matDao.doRetrieveAll();
				System.out.println(materie.size());
				session.setAttribute("materie", materie);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			session.setAttribute("slide", slide);
		response.sendRedirect("index.jsp");
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
