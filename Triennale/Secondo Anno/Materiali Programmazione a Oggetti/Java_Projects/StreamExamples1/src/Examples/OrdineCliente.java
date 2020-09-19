package Examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class OrdineCliente {
	private String cliente;
	private List<Libro> libri;
	
	public OrdineCliente(String cliente,Libro...libri) {
		this.cliente=cliente;
		this.libri=Arrays.asList(libri);
	}
	public String getCliente() {
		return cliente;
	}
	public List<Libro> getLibri() {
		return libri;
	}
	public static OrdineCliente[] ordiniCliente = {
			new OrdineCliente("quercia", Libro.libri[0], Libro.libri[1], Libro.libri[2],
			Libro.libri[3], Libro.libri[4], Libro.libri[5], Libro.libri[6],Libro.libri[7]),
			new OrdineCliente("olmo", Libro.libri[0], Libro.libri[1]),
			new OrdineCliente("acero", Libro.libri[4], Libro.libri[5], Libro.libri[6])
			};
	public static Stream<OrdineCliente> getStream() {return Stream.of(ordiniCliente);}
	
}
