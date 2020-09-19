package it.polito.po.disegno.base;

public class Punto extends Figura {

	public Punto(char daStampare) {
		super(daStampare);

	}

	@Override
	public void disegna(Schermo s) {
		
		Coordinata c = s.getPuntoPartenza(this);

		s.stampaPunto(c.getX(), c.getY(), this.daStampare);

	}
	


}
