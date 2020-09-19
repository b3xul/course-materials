package hydraulics.test;

import hydraulic.HSystem;
import hydraulic.Element;

public class MainTest {

	public static void main(String[] args) {
		
		HSystem s = new HSystem();
		
		Element[] elements = s.getElements();
		
		if( elements.length != 0 ){
			System.err.println("Failed test!");
		}else{
			System.out.println("Test passed.");
		}
		

	}

}
