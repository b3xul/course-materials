package it.polito.po.disegno.base;

import it.polito.po.disegno.base.exceptions.FiguraFuoriSchermoException;
import it.polito.po.disegno.base.exceptions.TroppeFigureException;

public abstract class FiguraComposita extends Figura {

	public FiguraComposita() {
		super('.');
	}
	
	public void disegna(Schermo s) {};
	
	public abstract void registraComponenti(Schermo s) throws FiguraFuoriSchermoException, TroppeFigureException;
	
	public abstract void aggiungi(Figura figura, int offsetx, int offsety) throws TroppeFigureException;

}
