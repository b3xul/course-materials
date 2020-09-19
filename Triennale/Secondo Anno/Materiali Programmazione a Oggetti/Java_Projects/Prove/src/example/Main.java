package example;

import succo.Facciata;

public class Main {
	
	public static void main(String[] args) {
		Facciata base=new Facciata();
		
		base.addSensata("Quarto", 4, 0.4);
		base.addSensata("Primo", 1, 1.1);
		base.addSensata("Secondo", 2, 2.2);
		
		//base.leggiETogli();
		base.ordinamiNumber();
		base.ordinamiF();
	}

}
