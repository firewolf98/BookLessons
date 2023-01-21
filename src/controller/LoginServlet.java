package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DisponibilitaBean;
import model.DisponibilitaBeanDAO;
import model.InsegnanteBean;
import model.InsegnanteBeanDAO;
import model.InsegnanteMateriaBean;
import model.InsegnanteMateriaBeanDAO;
import model.LezioneBean;
import model.LezioneBeanDAO;
import model.MateriaBean;
import model.MateriaBeanDAO;
import model.StudenteBean;
import model.StudenteBeanDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		StudenteBeanDAO sdao=new StudenteBeanDAO();
		InsegnanteBeanDAO idao=new InsegnanteBeanDAO();
		StudenteBean s;
		InsegnanteBean i;
		boolean snull=false,inull=false;
	
		HttpSession session=request.getSession();
		String redirect="";
		
		
		try {
			s = sdao.doRetrieveByKey(username);
			if (s!=null) {
				if (s.getPassword().equals(password)) {
					if (s.isAmministratore()) {
						session.setAttribute("isLogged", true);
						session.setAttribute("utente", s);
						redirect="index.jsp";
						session.setAttribute("tipoUtente", "admin");
						
						InsegnanteBeanDAO daoI=new InsegnanteBeanDAO();
						ArrayList<InsegnanteBean> insegnanti=daoI.doRetrieveAll();
						session.setAttribute("insegnantiAdmin", insegnanti);
					}

					else {
						session.setAttribute("isLogged", true);
						session.setAttribute("utente", s);
						redirect="index.jsp";
						session.setAttribute("tipoUtente", "studente");
						
						LezioneBeanDAO lDAO=new LezioneBeanDAO();
						ArrayList<LezioneBean> lezioni=lDAO.doRetrieveByCondition(s);
						session.setAttribute("lezioni", lezioni);
					}
				}
				else {
					redirect="login.jsp";
				}
			}
			else {
				snull=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			i=idao.doRetrieveByKey(username);
			if (i!=null) {
				if (i.getPassword().equals(password))
					if (i.isAmministratore()) {
						session.setAttribute("isLogged", true);
						session.setAttribute("utente", i);
						redirect="index.jsp";
						session.setAttribute("tipoUtente", "admin");
						InsegnanteBeanDAO daoI=new InsegnanteBeanDAO();
						ArrayList<InsegnanteBean> insegnanti=daoI.doRetrieveAll();
						session.setAttribute("insegnantiAdmin", insegnanti);
					}
					else {
						session.setAttribute("isLogged", true);
						session.setAttribute("utente", i);
						redirect="index.jsp";
						session.setAttribute("tipoUtente", "insegnante");
						InsegnanteMateriaBeanDAO daoIM=new InsegnanteMateriaBeanDAO();
						ArrayList<MateriaBean> materieInsegnate=daoIM.doRetrieveByCondition(i);
						session.setAttribute("materieInsegnate", materieInsegnate);
						
						DisponibilitaBeanDAO daoD=new DisponibilitaBeanDAO();
						ArrayList<DisponibilitaBean> disponibilita=daoD.doRetrieveByCondition(i);
						session.setAttribute("disponibilita", disponibilita);
						
						LezioneBeanDAO lDAO=new LezioneBeanDAO();
						ArrayList<LezioneBean> lezioni=lDAO.doRetrieveByCondition(i);
						session.setAttribute("lezioni", lezioni);
					}
				
				else {
					redirect="login.jsp";
				}
			}else {
				inull=true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (snull&& inull) {
			redirect="registrati.jsp";
		}
		
		if (redirect.equals("index.jsp")) {
			MateriaBeanDAO dao=new MateriaBeanDAO();
			ArrayList<MateriaBean> materie;
			try {
				materie = dao.doRetrieveAll();
				session.setAttribute("materie", materie);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
		}
		
		if (redirect.equals("login.jsp")) {
			request.setAttribute("message", "Username o password errati");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
			requestDispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(redirect);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
