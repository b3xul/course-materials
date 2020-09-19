package Examples;

import java.util.ArrayList;

public class OrdineEditore {

	private String editore;
	private ArrayList<Linea> linee = new ArrayList<Linea>();
	private class Linea {
	String titolo; int n;
	Linea(String titolo, int n) {this.titolo = titolo; this.n = n;}
	public String toString() {return titolo + ":" +n;}
	}
	public OrdineEditore (String editore) {
	this.editore = editore;
	}
	public void addLinea(String titolo, int n) {
	linee.add(new Linea(titolo, n));
	}
	public String toString() {return editore + " " + linee;}

}
