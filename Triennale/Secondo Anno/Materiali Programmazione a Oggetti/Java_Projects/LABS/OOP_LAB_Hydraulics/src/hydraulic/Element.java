package hydraulic;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public abstract class Element {
	protected String name;
	protected Element out;
	protected double inFlow;
	protected double outFlow;
	/**
	 * Constructor
	 * @param name the name of the element
	 */
	public Element(String name){
		this.name=name;
	}

	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	public Element getOutput(){
		return this.out;
	}
	
	public void setInFlow(double flow) {
		this.inFlow=flow;
	}
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem){
		if(elem instanceof Source)
			System.out.println("Non posso connettere una sorgente in uscita!");
		else if(this instanceof Sink)
			System.out.println("Non posso connettere nulla a uno scarico!");
		else
			this.out=elem;
		
		return;
	}
	
	public abstract void simulate();
	
	public abstract StringBuilder layout(int spacing);
	
	public String toString() {
		return this.name + " " + this.inFlow + " " + this.outFlow;
	}
}
