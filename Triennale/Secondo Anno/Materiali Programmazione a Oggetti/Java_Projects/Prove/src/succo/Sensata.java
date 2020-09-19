package succo;

public class Sensata {

	private String name;
	protected int numero;
	protected double flow;

	public Sensata(String name,int numero, double flow) {
		this.name=name;
		this.numero=numero;
		this.flow=flow;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public double getFlow() {
		return this.flow;
	}
	
	@Override
	public String toString() {
		return	name + ":" + numero + " flow-> " + flow;
	}
}
