package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;

import model.MateriaBean;
import model.MateriaBeanDAO;

/**
 * Servlet implementation class RicercaAjaxMateria
 */
@WebServlet("/RicercaAjaxMateria")
public class RicercaAjaxMateria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaAjaxMateria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray matJson = new JSONArray();
		String query = request.getParameter("q");
		if (query != null) {
			MateriaBeanDAO mdao=new MateriaBeanDAO();
			List<MateriaBean> materie;
			try {
				materie = mdao.doRetrieveByNome(query + "%");
				for (MateriaBean m : materie) {
					matJson.put(m.getNome());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.getWriter().append(matJson.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
