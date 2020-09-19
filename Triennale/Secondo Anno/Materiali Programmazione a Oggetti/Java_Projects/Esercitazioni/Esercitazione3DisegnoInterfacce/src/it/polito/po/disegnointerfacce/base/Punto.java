package it.polito.po.disegnointerfacce.base;

public class Punto implements Figura {
	char daStampare;
	
	public Punto(char daStampare) {
		this.daStampare = daStampare;
	}

	@Override
	public void disegna(Schermo s) {

		Coordinata c = s.getPuntoPartenza(this);

		s.stampaPunto(c.getX(), c.getY(), this.daStampare);

	}
	


}
