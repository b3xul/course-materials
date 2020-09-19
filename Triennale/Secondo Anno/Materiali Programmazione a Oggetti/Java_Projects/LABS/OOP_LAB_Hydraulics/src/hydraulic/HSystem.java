package hydraulic;

import java.util.Arrays;

//import java.util.Arrays;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	private static final int MAX_ELEMENTS=1000;
	private Element[] elements;
	private int next;

	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public HSystem() {
		this.elements=new Element[MAX_ELEMENTS];
	}
	
	public void addElement(Element elem){
		if(next>=MAX_ELEMENTS)
			return;
		//TODO aggiungere eccezione
		elements[next++]=elem;
		return;
	}
	
	/**
	 * returns the element added so far to the system
	 * @return an array of elements whose length is equal to the number of added elements
	 */
	public Element[] getElements(){
		Element[] output;
		output=new Element[next];
		output=Arrays.copyOf(elements,next);
		
		return output;
	}
	
	/**
	 * starts the simulation of the system
	 */
	
	public void simulate(){
		// TODO: to be implemented
		for(int i=0;i<next;i++) {
			Element e=elements[i];
			if(e instanceof Source)
				e.simulate();
			
		}
		
	}
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		StringBuilder output = new StringBuilder();
		for(int i=0;i<next;i++) {
			Element e=elements[i];
			if(e instanceof Source)
				output.append(e.layout(0));
		}
		return output.toString();
	}
	
}

