package model;

public class InsegnanteMateriaBean {
	private MateriaBean nomeMateria;
	private InsegnanteBean insegnante;
	
	public InsegnanteMateriaBean(MateriaBean nomeMateria,InsegnanteBean insegnante) {
		this.nomeMateria = nomeMateria;
		this.insegnante = insegnante ;
	}
	
	public InsegnanteMateriaBean() {
		
	}

	public MateriaBean getNomeMateria() {
		return nomeMateria;
	}

	public void setNomeMateria(MateriaBean nomeMateria) {
		this.nomeMateria = nomeMateria;
	}

	public InsegnanteBean getInsegnante() {
		return insegnante;
	}

	public void setInsegnante(InsegnanteBean insegnante) {
		this.insegnante = insegnante;
	}
}
