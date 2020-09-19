package it.polito.po.disegno.base;

public abstract class Figura {
	protected char daStampare;
	
	public Figura(char daStampare) {
		this.daStampare = daStampare;
	}
	
	public void disegna(Schermo s) {}
	
}
