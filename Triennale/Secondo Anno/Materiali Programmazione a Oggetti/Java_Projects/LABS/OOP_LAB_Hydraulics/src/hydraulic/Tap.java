package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends Element {
	private boolean open;
	
	public Tap(String name) {
		super(name);
		//TODO: complete
	}
	
	public boolean getOpen(){
		return this.open;
	}
	
	public void setOpen(boolean open){
		this.open=open;
	}

	public void simulate() {		
		/*flow=((Source)elem).getFlow();	dopo l'instanceof posso castare l'element all'elemento specifico e da lì usare getFlow(altrimenti sconosciuto a un Element)*/
		/*if(!((Tap)elem).getOpen())*/
		
		if(getOutput()!=null) {
			if(this.open)
				this.outFlow=inFlow;
			else
				this.outFlow=0;
			
			System.out.println(this);
			getOutput().setInFlow(this.outFlow);
			getOutput().simulate();
		}
		return;
	}
	
	@Override
	public StringBuilder layout(int spacing) {
		StringBuilder info=new StringBuilder();
		info.append("[").append(this.name).append("] ").append(this.getClass().getSimpleName()).append(" -> ");
		if(getOutput()!=null)
			info.append(getOutput().layout(spacing+info.length()));
		return info;
	}
}
