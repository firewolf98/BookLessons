package model;

import java.util.ArrayList;

public class Carrello {
	private ArrayList<LezioneBean> lezioni;
	
	public Carrello() {
		lezioni=new ArrayList<>();
	}

	public ArrayList<LezioneBean> getLezioni() {
		return lezioni;
	}

	public void setLezioni(ArrayList<LezioneBean> lezioni) {
		this.lezioni = lezioni;
	}
	
	public void aggiungiLezione(LezioneBean lezione) {
		lezioni.add(lezione);
	}
	
	public void cancellaLezione(LezioneBean lezione) {
		lezioni.remove(lezione);
	}
	
}
