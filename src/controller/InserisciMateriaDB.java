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

import model.MateriaBean;
import model.MateriaBeanDAO;

/**
 * Servlet implementation class InserisciMateriaDB
 */
@WebServlet("/InserisciMateriaDB")
public class InserisciMateriaDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciMateriaDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeMateria=request.getParameter("materia");
		HttpSession session=request.getSession();
		synchronized (session) {
			MateriaBeanDAO materiaD=new MateriaBeanDAO();
			MateriaBean materia=new MateriaBean(nomeMateria);
			try {
				boolean ris=materiaD.doSave(materia);
				if (ris) {
					ArrayList<MateriaBean> materie=(ArrayList) session.getAttribute("materie");
					materie.add(materia);
					session.setAttribute("materie", materie);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
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
