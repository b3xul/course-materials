package hydraulics.test;

import hydraulic.Element;
import hydraulic.HSystem;
import junit.framework.TestCase;

public class TestR1_System extends TestCase {

	public void testEmptySystem (){
		
		HSystem s = new HSystem();
		
		Element[] elements = s.getElements();
	
		assertEquals( 0,   		// expected value
				elements.length // actual value
				);
	}
	
}
