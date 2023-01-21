package model;

public class StudenteBean {
	private String nome, cognome, email, username, password, citta;
	private boolean amministratore;

	public StudenteBean(String nome, String cognome, String email, String username, String password, String citta) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.username = username;
		this.password = password;
		this.citta=citta;
		this.amministratore=false;
	}
	
	
	public StudenteBean() {
	}


	public String getNome() {
		return nome;
	}

	public boolean isAmministratore() {
		return amministratore;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public void setAmministratore(boolean amministratore) {
		this.amministratore=amministratore;
	}


	
}
