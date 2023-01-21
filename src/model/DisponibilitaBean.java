package model;

public class DisponibilitaBean {
		private String giorno;
		private InsegnanteBean insegnante;
		private int oraInizio,oraFine;

		public DisponibilitaBean(String giorno,InsegnanteBean insegnante,int oraInizio,int oraFine) {
			this.giorno = giorno;
			this.insegnante = insegnante;
			this.oraInizio=oraInizio;
			this.oraFine=oraFine;
		}
		
		
		public DisponibilitaBean() {
		}


		public String getGiorno() {
			return giorno;
		}

		public void setGiorno(String giorno) {
			this.giorno = giorno;
		}


		public InsegnanteBean getInsegnante() {
			return insegnante;
		}


		public void setInsegnante(InsegnanteBean insegnante) {
			this.insegnante = insegnante;
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
		

	}
