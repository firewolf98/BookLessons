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
import javax.servlet.http.Part;

import model.InsegnanteBean;
import model.InsegnanteBeanDAO;

/**
 * Servlet implementation class ServletRegInsegnante
 */
@WebServlet("/ServletRegInsegnante")
@MultipartConfig
public class ServletRegInsegnante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegInsegnante() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		/*String foto=request.getParameter("foto");
		if (foto!="") {
			File f =  new File(foto);
			String encodstring = encodeFileToBase64Binary(f);*/
		String encodstring=""; 	
		Part filePart = request.getPart("foto");
		InputStream content = filePart.getInputStream();
		
		if (content.available()!=0) {				
			try (FileInputStream imageInFile = (FileInputStream) content) {		
				byte imageData[] = new byte[500000];			
				imageInFile.read(imageData);			
				byte[] asBytes = Base64.getEncoder().encode(imageData);		
				encodstring= new String(asBytes, "utf-8");
			} 					catch (FileNotFoundException e) {	
				System.out.println("Image not found" + e);		
			} catch (IOException ioe) {				
				System.out.println("Exception while reading the Image " + ioe);	
			} 
		

			String nome=request.getParameter("nome");
			String cognome=request.getParameter("cognome");
			String email=request.getParameter("email");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String citta=request.getParameter("citta");
			String livello=request.getParameter("livello");
			String sesso=request.getParameter("sesso");

			String tariffaOraria=request.getParameter("tariffaOraria");

			//if (nome!=""&&cognome!=""&&email!=""&&username!=""&&password!=""&&citta!=""&&livello!=""&&sesso!=""&&tariffaOraria!="") {
				if (validazione(nome,cognome,email,username,password,citta,livello,sesso, tariffaOraria)) {
				InsegnanteBean insegnante=new InsegnanteBean(nome, cognome, email,password, username, citta,
						encodstring,livello,0, Integer.parseInt(tariffaOraria), sesso.charAt(0));
				InsegnanteBeanDAO inse=new InsegnanteBeanDAO();

				boolean inserito;
				try {
					inserito = inse.doSave(insegnante);
					request.setAttribute("ris", inserito);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else request.setAttribute("ris", false);
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

		private static String encodeFileToBase64Binary(File file){
			String encodedfile = null;
			try {
				FileInputStream fileInputStreamReader = new FileInputStream(file);
				byte[] bytes = new byte[(int)file.length()];
				fileInputStreamReader.read(bytes);
				encodedfile = Base64.getEncoder().encodeToString(bytes);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return encodedfile;
		}
		
		private boolean validazione(String nome, String cognome, String email, String username, String password, String citta, String livello, String sesso, String tariffaOraria) {
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
			if (livello.equals("")||sesso.equals("")||tariffaOraria.equals(""))
				valido=false;
			return valido;
		}

	}
