package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {
	private static final int MAX_EXITS=2;
	private Element [] out;
	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name);
		this.out=new Element[MAX_EXITS];
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
        return out;
    }

	public void connect(Element elem, int noutput){
		out[noutput]=elem;
		return;
	}
	
	public void simulate() {
		this.outFlow=this.inFlow;
		System.out.println(this);
		for(int i=0;i<MAX_EXITS;i++) {
			if(out[i]!=null) {
				out[i].setInFlow(this.outFlow/2);
				out[i].simulate();
			}
		}
		return;
	}
	
	@Override
	public StringBuilder layout(int spacing) {
		StringBuilder info=new StringBuilder();
		StringBuilder indent=new StringBuilder();
		
		info.append("[").append(this.name).append("] ").append(this.getClass().getSimpleName()).append(" ");
		
		for(int j=0;j<spacing+info.length();j++) {
			indent.append(" ");
		}
		
		for(int i=0;i<MAX_EXITS;i++) {
			if(out[i]!=null) {
				info.append("+-> ").append(out[i].layout(spacing+info.length()));
				if(i!=MAX_EXITS-1) {
					info.append(indent);
					info.append("|\n");
					info.append(indent);
				}
			}
		}
		return info;
	}
}