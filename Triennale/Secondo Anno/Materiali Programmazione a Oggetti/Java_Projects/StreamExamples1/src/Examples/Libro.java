package Examples;

import java.util.Arrays;
import java.util.List;

public class Libro {
	private String autore;
	private String titolo;
	private String editore;
	private int pagine;
	
	public static Libro[] libri = {
			new Libro ("falco", "rosso", "deltaplano", 100),
			new Libro ("falco", "arancio", "caravella", 120),
			new Libro ("rondine", "blu", "caravella", 250),
			new Libro ("rondine", "azzurro", "caravella", 250),
			new Libro ("rondine", "indaco", "deltaplano", 80),
			new Libro ("rondine", "giallo", "deltaplano", 100),
			new Libro ("corvo", "nero", "monociclo", 300),
			new Libro ("corvo", "grigio", "caravella", 240),
	};
	public static List<Libro> listaLibri = Arrays.asList(libri);
	
	public Libro(String autore,
				String titolo,
				String editore,
				int pagine) {
		this.autore=autore;
		this.titolo=titolo;
		this.editore=editore;
		this.pagine=pagine;
	}
	public String getAutore() {
		return autore;
	}
	public int getPagine() {
		return pagine;
	}
	public String getEditore() {
		return editore;
	}
	public String getTitolo() {
		return titolo;
	}
	
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public void setEditore(String editore) {
		this.editore = editore;
	}
	public void setPagine(int pagine) {
		this.pagine = pagine;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	@Override
	public String toString() {
		return this.titolo;
	}
}
