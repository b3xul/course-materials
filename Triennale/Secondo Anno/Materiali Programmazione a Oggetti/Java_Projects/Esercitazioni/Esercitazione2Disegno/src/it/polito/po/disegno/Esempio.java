package it.polito.po.disegno;

import it.polito.po.disegno.base.Coordinata;
import it.polito.po.disegno.base.Figura;
import it.polito.po.disegno.base.Gruppo;
import it.polito.po.disegno.base.Punto;
import it.polito.po.disegno.base.Quadrato;
import it.polito.po.disegno.base.Rettangolo;
import it.polito.po.disegno.base.Schermo;
import it.polito.po.disegno.base.Triangolo;

public class Esempio {
	
	public static void main(String[] args) {
		try {
			
			
			Schermo s = new Schermo(10,30);
			
			//polimorfismo
			Punto p1 = new Punto('*');
			Figura p2 = new Punto('+');
			
			
			
			s.disegna(p1, new Coordinata(6, 24));
			s.disegna(p2, new Coordinata(7, 7));
			

//			Figura r1 = new Rettangolo(3, 3, 'x');
//			Rettangolo r2 = new Rettangolo(3, 2, 'a');
//			Rettangolo r3 = new Rettangolo(4, 4, 'o');
//
//			Triangolo t1 = new Triangolo(3, 3, 'm');
////			
//			s.disegna(r1, new Coordinata(3, 8));
////			s.disegna(r2, new Coordinata(0, 0));
////			s.disegna(r3, new Coordinata(1, 1));
//
//			s.disegna(t1, new Coordinata(1, 22));
////			
////			
//			Figura q1 = new Quadrato(3, 'X');
//			s.disegna(q1, new Coordinata(2, 9));
////			
////			
//			Gruppo gruppo1 = new Gruppo();
//			gruppo1.aggiungi(r2,0,0);
//			gruppo1.aggiungi(r3, 1, 1);
//			
//			s.disegna(gruppo1, new Coordinata(7, 12));
////			
			s.visualizza();
//			
//			//secondo schermo più piccolo 
//			
//			Schermo s2 = new Schermo(7,7);
//			s2.disegna(p1, new Coordinata(1, 1));
//			s2.disegna(r2, new Coordinata(2,2));
			
//			s2.visualizza();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
