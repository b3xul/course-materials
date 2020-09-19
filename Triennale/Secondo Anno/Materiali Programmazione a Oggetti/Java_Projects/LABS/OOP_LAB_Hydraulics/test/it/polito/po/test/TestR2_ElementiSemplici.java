package it.polito.po.test;

import hydraulic.*;
import junit.framework.TestCase;


public class TestR2_ElementiSemplici extends TestCase {

	public void testNomeElemento(){
		String name="Test";
		Element el = new Source(name);
		
		assertEquals(name,el.getName());
	}

	public void testElementi(){
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
		assertSame(tap,src.getOutput());
		assertSame(sink,tap.getOutput());
	}
}
