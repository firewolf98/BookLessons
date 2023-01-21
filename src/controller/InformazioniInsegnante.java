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

import org.json.JSONArray;
import org.json.JSONObject;

import model.DisponibilitaBean;
import model.DisponibilitaBeanDAO;
import model.InsegnanteBean;
import model.InsegnanteBeanDAO;
import model.VotoBean;
import model.VotoBeanDAO;

/**
 * Servlet implementation class InformazioniInsegnante
 */
@WebServlet("/InformazioniInsegnante")
public class InformazioniInsegnante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformazioniInsegnante() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		InsegnanteBeanDAO model=new InsegnanteBeanDAO();
		try {
			InsegnanteBean i=model.doRetrieveByKey(username);
			request.setAttribute("insegnante", i);
			DisponibilitaBeanDAO dDAO=new DisponibilitaBeanDAO();
			String giorni[]= {"domenica","lunedì", "martedì", "mercoledì","giovedi", "venerdì", "sabato"};
			ArrayList<DisponibilitaBean> disp=dDAO.doRetrieveByCondition(i);
			
			request.setAttribute("disponibilita", disp);
			
			/* disponibilita con stringa json*/
			JSONArray dJson = new JSONArray();
			for (DisponibilitaBean d : disp) {
					JSONObject obj=new JSONObject();
					obj.append("giorno", d.getGiorno());
					obj.append("oraInizio", d.getOraInizio());
					obj.append("oraFine", d.getOraFine());
					dJson.put(obj);
			}
			
			request.setAttribute("j", dJson.toString());
			
			ArrayList<String> giorniDisp=new ArrayList<>();
			for (DisponibilitaBean d: disp) {
				giorniDisp.add(d.getGiorno());
			}
			
			String giorniDisponibili="";
			for (int z=0;z<giorni.length;z++) {
				if (giorniDisp.contains(giorni[z]))
					giorniDisponibili+=","+z;
			}
			request.setAttribute("giorniDisp", giorniDisponibili);
			
			VotoBeanDAO votoDAO=new VotoBeanDAO();
			ArrayList<VotoBean> recensioni=votoDAO.doRetrieveByCondition(i);
			request.setAttribute("recensioni", recensioni);
			HttpSession session=request.getSession();
			session.setAttribute("recensioni", recensioni);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("informazioniInsegnante.jsp");
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
