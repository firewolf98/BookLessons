package controller;

import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.InsegnanteBean;
import model.InsegnanteBeanDAO;
import model.StudenteBean;
import model.StudenteBeanDAO;

/**
 * Servlet implementation class ModificaDatiPersonali
 */
@WebServlet("/ModificaDatiPersonali")
@MultipartConfig
public class ModificaDatiPersonali extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaDatiPersonali() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String tipoUtente=(String) session.getAttribute("tipoUtente");
		
		if (tipoUtente.equals("insegnante")) {
			InsegnanteBean x=(InsegnanteBean) session.getAttribute("utente");
			String passV=request.getParameter("passwordV");
			//password vecchia non corretta
			if (!passV.equals("")&&!passV.equals(x.getPassword())) {
				request.setAttribute("message", "Password vecchia non corretta");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
				requestDispatcher.forward(request, response);
			}
			else {
				String citta=request.getParameter("citta");
				String pass1=request.getParameter("passwordN1");
				String pass2=request.getParameter("passwordN2");
				
				if (!pass1.equals("")&&!pass2.equals("")&&!validaPass(pass1,pass2)) {
					request.setAttribute("message", "Formato dei dati non corretto");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
					requestDispatcher.forward(request, response);
				}
				else {
				if(!pass1.equals(pass2)) {
					request.setAttribute("message", "Le due password inserite non corrispondono");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
					requestDispatcher.forward(request, response);
				}
				else {
					if (pass1.equals(""))
						pass1=x.getPassword();

					Part filePart = request.getPart("foto");
					InputStream content = filePart.getInputStream();
					if (content.available()!=0) {				
						String encodstring=""; 	
						try {		
							FileInputStream imageInFile = (FileInputStream) content;
							byte imageData[] = new byte[500000];			
							imageInFile.read(imageData);			
							byte[] asBytes = Base64.getEncoder().encode(imageData);		
							encodstring= new String(asBytes, "utf-8");
							x.setFoto(encodstring);
						} 					catch (FileNotFoundException e) {	
							System.out.println("Image not found" + e);		
						} catch (IOException ioe) {				
							System.out.println("Exception while reading the Image " + ioe);	
						} 
					}

					int tariffaOraria=Integer.parseInt(request.getParameter("tariffaOraria"));
					String descrizione=request.getParameter("descrizione");
					

					InsegnanteBeanDAO iDAO=new InsegnanteBeanDAO();

					if (!citta.equals(x.getCitta()))
						x.setCitta(citta);
					if (!pass1.equals(x.getPassword()))
						x.setPassword(pass1);
					if (tariffaOraria!=x.getTariffaOraria())
						x.setTariffaOraria(tariffaOraria);
					if (descrizione!=""&&!descrizione.equals(x.getDescrizione()))
						x.setDescrizione(descrizione);
					else x.setDescrizione(null);

					try {
						iDAO.doUpdate(x);
						session.setAttribute("utente", x);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("profiloIns.jsp");
						requestDispatcher.forward(request, response);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				}
			}
		}
			else if (tipoUtente.equals("studente")) {
				StudenteBean x=(StudenteBean) session.getAttribute("utente");
				String citta=request.getParameter("citta");
				String pass1=request.getParameter("passwordN1");
				String pass2=request.getParameter("passwordN2");
				String passV=request.getParameter("passwordV");
				//password vecchia non corretta
				if (!passV.equals("")&&!passV.equals(x.getPassword())) {
					request.setAttribute("message", "Password vecchia non corretta");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
					requestDispatcher.forward(request, response);
				}

				else {
					if (!pass1.equals("")&&!pass2.equals("")&&!validaPass(pass1,pass2)) {
						request.setAttribute("message", "Formato dei dati non corretto");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
						requestDispatcher.forward(request, response);
					}
					else {
					if(!pass1.equals(pass2)) {
						request.setAttribute("message", "Le due password inserite non corrispondono");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
						requestDispatcher.forward(request, response);
					}
					else {
						if (pass1.equals(""))
							pass1=x.getPassword();


						StudenteBeanDAO sDAO=new StudenteBeanDAO();

						if (!citta.equals(x.getCitta()))
							x.setCitta(citta);
						if (!pass1.equals(x.getPassword()))
							x.setPassword(pass1);
						try {
							sDAO.doUpdate(x);
							session.setAttribute("utente", x);

							RequestDispatcher requestDispatcher = request.getRequestDispatcher("profiloStud.jsp");
							requestDispatcher.forward(request, response);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
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
	
	private boolean validaPass(String pass1,String pass2 ) {
		boolean valido=true;
		String expPassword="^[a-zA-Z0-9]{8,19}$";
		if (!Pattern.matches(expPassword, pass1)||!Pattern.matches(expPassword, pass2))
			valido=false;
		return valido;
	}
}

