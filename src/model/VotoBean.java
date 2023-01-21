package model;

import java.util.Date;

public class VotoBean {
	private StudenteBean studente;
	private InsegnanteBean insegnante;
	private int voto;
	private String commento;
	private Date dataVoto;
	
	public VotoBean(StudenteBean studente, InsegnanteBean insegnante, int voto, String commento, Date dataVoto) {
		super();
		this.studente = studente;
		this.insegnante = insegnante;
		this.voto = voto;
		this.commento = commento;
		this.dataVoto = dataVoto;
	}
	
	public VotoBean() {
		
	}

	public StudenteBean getStudente() {
		return studente;
	}

	public void setStudente(StudenteBean studente) {
		this.studente = studente;
	}

	public InsegnanteBean getInsegnante() {
		return insegnante;
	}

	public void setInsegnante(InsegnanteBean insegnante) {
		this.insegnante = insegnante;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public Date getDataVoto() {
		return dataVoto;
	}

	public void setDataVoto(Date dataVoto) {
		this.dataVoto = dataVoto;
	}
	
	
}
