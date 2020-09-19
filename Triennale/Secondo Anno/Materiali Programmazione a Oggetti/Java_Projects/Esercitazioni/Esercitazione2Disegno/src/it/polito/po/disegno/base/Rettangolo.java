package it.polito.po.disegno.base;

public class Rettangolo extends Figura {
	
	private final int l;
	private final int h;

	public Rettangolo(int h, int l, char daStampare) {
		super(daStampare);
		this.h = h;
		this.l = l;
	}

	@Override
	public void disegna(Schermo s) {
		
		int maxW = s.getMaxWidth();
		int maxL = s.getMaxLenght();
		
		Coordinata p = s.getPuntoPartenza(this);
		int x = p.getX();
		int y = p.getY();
		
		for (int i = 0; i < h; i++) {
			if(x+i>=maxW) break;
			for (int j = 0; j < l; j++) {
				if(y+j >= maxL) break;
				s.stampaPunto(x+i, y+j, daStampare);
			}
		}
	}

}
