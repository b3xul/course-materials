package it.polito.po.disegno.base;

import it.polito.po.disegno.base.exceptions.FiguraFuoriSchermoException;
import it.polito.po.disegno.base.exceptions.TroppeFigureException;

public class Gruppo extends FiguraComposita{
	
	private Figura[] lista_elementi;
	private Coordinata[] lista_coordinate;
	private int next; 
	
	private static final int MAX_ELEMENTI = 50;
	
	public Gruppo() {
//		this.elementi = new HashMap<>();
		
		this.lista_elementi = new Figura[MAX_ELEMENTI];
		this.lista_coordinate = new Coordinata[MAX_ELEMENTI];
		this.next = 0;
	}

	
	public void aggiungi(Figura f, int offsetX, int offsetY) throws TroppeFigureException  {
		if(next < MAX_ELEMENTI) {
			lista_elementi[next]=f;
			lista_coordinate[next++] = new Coordinata(offsetX, offsetY);
		}
		else throw new TroppeFigureException();
			
//		elementi.put(f, new Coordinata(offsetX, offsetY));
	}

	@Override
	public void registraComponenti(Schermo s) throws FiguraFuoriSchermoException, TroppeFigureException {
		Coordinata p = s.getPuntoPartenza(this);
		
		for(int i =0; i < next; i++) {
						
			s.disegna(lista_elementi[i], new Coordinata(p.getX()+lista_coordinate[i].getX(), p.getY()+lista_coordinate[i].getY()));
		}
		
	}
	
}
