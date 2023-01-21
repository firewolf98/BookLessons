package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudenteBean;
import model.StudenteBeanDAO;

/**
 * Servlet implementation class ServletRegStudente
 */
@WebServlet("/ServletRegStudente")
public class ServletRegStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegStudente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String citta=request.getParameter("citta");

		if (valido(nome,cognome,email,username,password,citta)) {
			StudenteBean s=new StudenteBean(nome, cognome, email, username, password, citta);
			StudenteBeanDAO stud=new StudenteBeanDAO();

			boolean inserito;
			try {
				inserito = stud.doSave(s);
				request.setAttribute("ris", inserito);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else request.setAttribute("ris", false);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("outputRegistrazione.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean valido(String nome, String cognome, String email, String username, String password, String citta ) {
		boolean valido=true;
		String expNome="^[A-Z]{1}[A-Za-z ]*$";
		String expCognome="^[A-Z]{1}[A-Za-z ]*$";
		String expEmail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String expUsername="^[a-zA-Z0-9]{1}[a-z_A-Z0-9]{2,19}$";
		String expPassword="^[a-zA-Z0-9]{8,19}$";
		String expCitta="^[A-Z]{1}[a-z]*$";
		
		if (!Pattern.matches(expNome, nome))
			valido=false;
		if (!Pattern.matches(expCognome, cognome))
			valido=false;
		if (!Pattern.matches(expUsername, username))
			valido=false;
		if (!Pattern.matches(expPassword, password))
			valido=false;
		if (!Pattern.matches(expCitta, citta))
			valido=false;
		if (!Pattern.matches(expEmail, email))
			valido=false;
		return valido;
	}

}
