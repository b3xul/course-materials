package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * Lo status of the source is defined through the method
 * {@link #setInFlow(double) setFlow()}.
 */
public class Source extends Element {
	
	public Source(String name) {
		super(name);
		//TODO: complete
	}

	public void setFlow(double flow){
		this.outFlow=flow;
	}
	
	public void simulate() {
		/*super.flow=((Source)elem).getFlow();	dopo l'instanceof posso castare l'element all'elemento specifico e da lì usare getFlow(altrimenti sconosciuto a un Element)*/
		if(getOutput()!=null) {
			System.out.println(this);
			getOutput().setInFlow(this.outFlow);
			getOutput().simulate();
		}
		return;
		//TODO: lanciare eccezione 
	}
	
	@Override
	public StringBuilder layout(int spacing) {
		StringBuilder info=new StringBuilder();
		info.append("[").append(this.name).append("] ").append(this.getClass().getSimpleName()).append(" -> ");
		/*this.getclass.getsimplename ritorna il nome della classe che si vede nel codice sorgente*/
		if(getOutput()!=null)
			info.append(getOutput().layout(info.length()));
		return info;
	}

}
