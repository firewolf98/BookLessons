package model;

import java.sql.Date;

public class LezioneBean {
	private int codice,oraInizio,oraFine,costo;
	private Date dataLezione;
	private StudenteBean studente;
	private InsegnanteBean insegnante;
	private MateriaBean materia;
	private OrdineBean ordine;
	private boolean accreditata, fatturata;
	private String luogo;
	
	public LezioneBean(int codice,int oraInizio,int oraFine,Date dataLezione,int costo,StudenteBean studente,InsegnanteBean insegnante,MateriaBean materia,boolean accreditata, boolean fatturata, String luogo) {
		this.codice=codice;
		this.oraInizio= oraInizio;
		this.oraFine = oraFine;
		this.dataLezione = dataLezione;
		this.studente = studente;
		this.insegnante =insegnante;
		this.costo=costo;
		this.materia=materia;
		this.accreditata=accreditata;
		this.fatturata=fatturata;
		this.luogo=luogo;
	}
	
	public LezioneBean() {
		
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public int getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(int oraInizio) {
		this.oraInizio = oraInizio;
	}

	public int getOraFine() {
		return oraFine;
	}

	public void setOraFine(int oraFine) {
		this.oraFine = oraFine;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public Date getDataLezione() {
		return dataLezione;
	}

	public void setDataLezione(Date dataLezione) {
		this.dataLezione = dataLezione;
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

	public MateriaBean getMateria() {
		return materia;
	}

	public void setMateria(MateriaBean materia) {
		this.materia = materia;
	}

	public OrdineBean getOrdine() {
		return ordine;
	}

	public void setOrdine(OrdineBean ordine) {
		this.ordine = ordine;
	}

	public boolean isAccreditata() {
		return accreditata;
	}

	public void setAccreditata(boolean accreditata) {
		this.accreditata = accreditata;
	}
	
	public boolean isFatturata() {
		return fatturata;
	}
	
	public void setFatturata(boolean fatturata) {
		this.fatturata=fatturata;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	
	
}
