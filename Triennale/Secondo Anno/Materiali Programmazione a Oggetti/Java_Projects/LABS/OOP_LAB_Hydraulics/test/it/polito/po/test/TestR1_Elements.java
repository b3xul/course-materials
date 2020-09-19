package it.polito.po.test;

import hydraulic.*;
import junit.framework.TestCase;


public class TestR1_Elements extends TestCase {
	
	public void testEmptySystem(){
		HSystem s = new HSystem();
		
		Element[] elements = s.getElements();
		
		assertEquals(0,elements.length);
		
	}

	public void testGetElements(){
		HSystem s = new HSystem();
		Element el1 = new Source("Test");		
		Element el2 = new Source("Test 1");		
		s.addElement(el1);
		s.addElement(el2);
		
		Element[] elements = s.getElements();
		
		assertEquals(2,elements.length);
		
		assertTrue(el1==elements[0]
					|| el1==elements[1]);
	}
}
