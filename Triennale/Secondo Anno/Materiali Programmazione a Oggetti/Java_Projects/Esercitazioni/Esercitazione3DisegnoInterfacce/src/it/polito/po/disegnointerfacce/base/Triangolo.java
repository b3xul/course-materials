package it.polito.po.disegnointerfacce.base;

import it.polito.po.disegnointerfacce.base.Figura;

public class Triangolo implements Figura {
	private char daStampare;
	private final int l;
	private final int h;
	
	public Triangolo(int h, int l, char daStampare) {
		this.daStampare=daStampare;
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
			
			for (int j = 0; j < l-i; j++) {
				if(y+j >= maxL) break;
				s.stampaPunto(x+i, y+j, daStampare);
			}
		}
	}
	
}
