package it.polito.po.rubrica;

import it.polito.po.rubrica.classi.Rubrica;

public class MainClass {

	public static void main(String[] args) {
//		Voce v1;
//		Voce v2;
//		
//		v1 = new Voce("Giorgio", "Bruno", "0110901234");
//		v2 = new Voce("Marco", "Torchiano", "0110904321");
//
//		System.out.println(v1.toString());
//		System.out.println(v2.toString());
		
		Rubrica rubrica1 = new Rubrica("Prima Rubrica");
		
		rubrica1.aggiungi("Giorgio", "Bruno", "0110901234");
		rubrica1.aggiungi("Marco", "Torchiano", "01109031231");
		rubrica1.aggiungi("XXXXX", "YYYYYY", "01109023423431");
		rubrica1.aggiungi("AAAAAA", "BBBBB", "0119872349873");
		
		//primo
		System.out.println(rubrica1.primo());
		
		
		// voce
		System.out.println(rubrica1.voce(1));
		System.out.println(rubrica1.voce(2));
		System.out.println(rubrica1.voce(3));
		System.out.println(rubrica1.voce(4));
		System.out.println(rubrica1.voce(5));
		
		
		// elenco
		
		System.out.println(rubrica1.elenco());
		
		// ricerca
		System.out.println(rubrica1.ricerca("Giorgio"));
		System.out.println(rubrica1.ricerca("gio"));
		System.out.println(rubrica1.ricerca("lkjlsdkf"));
		
		// tostring
		System.out.println(rubrica1);
		
	}

}
