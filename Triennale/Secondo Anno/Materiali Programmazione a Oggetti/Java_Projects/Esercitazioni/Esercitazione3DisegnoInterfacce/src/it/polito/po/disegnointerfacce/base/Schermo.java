package it.polito.po.disegnointerfacce.base;

import it.polito.po.disegnointerfacce.exceptions.FiguraFuoriSchermoException;
import it.polito.po.disegnointerfacce.exceptions.TroppeFigureException;

public class Schermo {
	private final static char C_SFONDO = '.';
	private final static int MAX_FIGURE = 100;
	
	private char[][] schermo;
	private int maxWidth;
	private int maxLenght;
	
	
	//TODO: strutture dati per gestire le figure
	FiguraSuSchermo[] lista_figure;
	int next;

	
	public Schermo(int w, int l){
		this.maxLenght = l;
		this.maxWidth = w;
		
		schermo = new char[maxWidth][maxLenght];

		init_schermo();

		lista_figure = new FiguraSuSchermo[MAX_FIGURE];
		next = 0;
		
	}

	public char[][] getSchermo() {
		return schermo;
	}

	private void init_schermo() {
		for(int i=0; i<maxWidth; i++)
			for(int j=0; j< maxLenght;j++)
				schermo[i][j] = C_SFONDO;
		
	}
	
	
	
	public void stampaPunto(int x, int y, char c) {
		if(c==C_SFONDO)
			c='*';
		schermo[x][y] = c;
	}
	
	
	public void disegna(Figura figura, Coordinata puntoPartenza) throws FiguraFuoriSchermoException, TroppeFigureException {
		//TODO: controllo che il punto di partenza non sia fuori dalla matrice dello schermo
		if(puntoPartenza.getX()>= maxWidth || puntoPartenza.getY()>=maxLenght)
			throw new FiguraFuoriSchermoException();
		
		//TODO: aggiunta figura alla struttura dati
		if(next<MAX_FIGURE)
			lista_figure[next++] = new FiguraSuSchermo(figura,puntoPartenza);
		else
			throw new TroppeFigureException();
		
		//TODO: 
		if(figura instanceof FiguraComposita) {
			((FiguraComposita)figura).registraComponenti(this);
		}
		
	
	}
	

	public void visualizza() {
	
		//TODO: re_inizializzare lo schermo (se serve)
		
		
		for(FiguraSuSchermo f: lista_figure) {
			if(f==null) break;
			f.getFigura().disegna(this);
		}
		
		//TODO: stampa a video della matrice
		for(int i=0; i<maxWidth;i++) {
			for(int j=0; j<maxLenght;j++)
				System.out.print(schermo[i][j]);
			System.out.println("");
		}
		
	}
	
	public Coordinata getPuntoPartenza(Figura figura) {
		
		Coordinata output = null;
		
		for(FiguraSuSchermo f: lista_figure) {
			if(f == null) break;
			if(f.getFigura().equals(figura))
				output = f.getCoordinata();
		}
		
		return output;
	}
	
	
	public int getMaxWidth() {
		return maxWidth;
	}

	public int getMaxLenght() {
		return maxLenght;
	}
	
	
	class FiguraSuSchermo {
		Figura figura;
		Coordinata coordinata;
		
		public Figura getFigura() {
			return figura;
		}
		public Coordinata getCoordinata() {
			return coordinata;
		}
		
		public FiguraSuSchermo(Figura figura, Coordinata coordinata) {
			this.figura = figura;
			this.coordinata = coordinata;
		}
		
	}
	
}
