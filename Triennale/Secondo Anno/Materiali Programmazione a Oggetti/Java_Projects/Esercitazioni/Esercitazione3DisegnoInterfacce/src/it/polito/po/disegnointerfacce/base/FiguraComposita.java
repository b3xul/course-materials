package it.polito.po.disegnointerfacce.base;

import it.polito.po.disegnointerfacce.exceptions.FiguraFuoriSchermoException;
import it.polito.po.disegnointerfacce.exceptions.TroppeFigureException;

public interface FiguraComposita extends Figura {

	public abstract void registraComponenti(Schermo s) throws FiguraFuoriSchermoException, TroppeFigureException;
	
	public abstract void aggiungi(Figura figura, int offsetx, int offsety) throws TroppeFigureException;

}
