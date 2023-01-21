package model;

import java.util.Date;

public class OrdineBean {
	private int idOrdine;
	private int costoTot;
	private Date dataOrdine;
	
	public OrdineBean(int idOrdine, int costoTot, Date dataOrdine) {
		this.idOrdine = idOrdine;
		this.costoTot = costoTot;
		this.dataOrdine=dataOrdine;
	}
	
	public OrdineBean() {
		
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getCostoTot() {
		return costoTot;
	}

	public void setCostoTot(int costoTot) {
		this.costoTot = costoTot;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	
	



}
