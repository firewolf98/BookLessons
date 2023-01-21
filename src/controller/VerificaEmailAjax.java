package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.InsegnanteBean;
import model.InsegnanteBeanDAO;
import model.StudenteBean;
import model.StudenteBeanDAO;

/**
 * Servlet implementation class verificaEmailAjax
 */
@WebServlet("/VerificaEmailAjax")
public class VerificaEmailAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificaEmailAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray usernameJson = new JSONArray();
		String query = request.getParameter("q");
		
		InsegnanteBeanDAO iDAO=new InsegnanteBeanDAO();
		StudenteBeanDAO sDAO=new StudenteBeanDAO();
		if (query != null) {
			try {
				ArrayList<InsegnanteBean> insegnanti=iDAO.doRetrieveAll();
				for (InsegnanteBean i : insegnanti) {
					if (i.getEmail().equals(query))
							usernameJson.put(i.getEmail());
				}
				
				ArrayList<StudenteBean> studenti=sDAO.doRetrieveAll();
				for (StudenteBean s : studenti) {
					if (s.getEmail().equals(query))
							usernameJson.put(s.getEmail());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		response.setContentType("application/json");
		response.getWriter().append(usernameJson.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
