package model;

public class InsegnanteBean {
	private String nome, cognome, email, password, citta,  foto, livello, username, descrizione;
	private int rifiuti, tariffaOraria;
	private boolean amministratore;
	private char sesso;
	
	public InsegnanteBean(String nome, String cognome, String email, String password, String username, String citta, 
			String foto, String livello, int rifiuti, int tariffaOraria, char sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.username=username;
		this.citta = citta;
		this.foto = foto;
		this.livello = livello;
		this.rifiuti = rifiuti;
		this.tariffaOraria = tariffaOraria;
		this.amministratore=false;
		this.descrizione="";
		this.sesso=sesso;
	}
	
	public InsegnanteBean() {
		
	}
	
	
	
	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	public String getUsername() {
		return username;
	}
	
	public boolean isAmministratore() {
		return amministratore;
	}
	
	public String getNome() {
		return nome;
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
	
	public void setUsername(String username) {
		this.username=username;
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


	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public int getRifiuti() {
		return rifiuti;
	}

	public void setRifiuti(int rifiuti) {
		this.rifiuti = rifiuti;
	}

	public int getTariffaOraria() {
		return tariffaOraria;
	}

	public void setTariffaOraria(int tariffaOraria) {
		this.tariffaOraria = tariffaOraria;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}
	
	public void setAmministratore(boolean amministratore) {
		this.amministratore=amministratore;
	}
}
