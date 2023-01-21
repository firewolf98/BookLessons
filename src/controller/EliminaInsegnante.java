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

/**
 * Servlet implementation class EliminaInsegnante
 */
@WebServlet("/EliminaInsegnante")
public class EliminaInsegnante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaInsegnante() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		HttpSession session=request.getSession();
		
		synchronized (session) {
			InsegnanteBeanDAO daoI=new InsegnanteBeanDAO();
			try {
				daoI.doDelete(username);
				ArrayList<InsegnanteBean> insegnanti=(ArrayList) session.getAttribute("insegnantiAdmin");
				for (int i=0;i<insegnanti.size();i++) {
					InsegnanteBean x=insegnanti.get(i);
					if (x.getUsername().equals(username))
						insegnanti.remove(i);
				}
				session.setAttribute("insegnantiAdmin", insegnanti);
				response.sendRedirect("index.jsp");
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
