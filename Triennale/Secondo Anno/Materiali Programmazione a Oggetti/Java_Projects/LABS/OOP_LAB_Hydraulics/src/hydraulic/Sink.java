package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {

	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		//TODO: complete
	}
	
	public void simulate() {	
		/*flow=((Source)elem).getFlow();	dopo l'instanceof posso castare l'element all'elemento specifico e da lì usare getFlow(altrimenti sconosciuto a un Element)*/	
		System.out.println(this);
		if(getOutput()!=null) {}
			//TODO: Lanciare un'altra eccezione
		return;
	}
	
	@Override
	public StringBuilder layout(int spacing) {
		StringBuilder info=new StringBuilder();
		info.append("[").append(this.name).append("] ").append(this.getClass().getSimpleName()).append(" |\n");
		return info;
	}
	
	@Override
	public String toString(){
		return this.name + " " + this.inFlow;
	}
	
}
