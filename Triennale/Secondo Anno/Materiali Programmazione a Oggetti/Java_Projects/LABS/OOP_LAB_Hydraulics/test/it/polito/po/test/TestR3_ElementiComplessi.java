package it.polito.po.test;

import hydraulic.*;
import junit.framework.TestCase;


public class TestR3_ElementiComplessi extends TestCase {

	public void testSplit(){
		
		HSystem s = new HSystem();
		Element src = new Source("Src");		
		Split t = new Split("T");	
		Element sink1 = new Sink("Sink 1");		
		Element sink2 = new Sink("Sink 2");		
		s.addElement(src);
		s.addElement(t);
		s.addElement(sink1);
		s.addElement(sink2);
		
		src.connect(t);
		t.connect(sink1,0);
		t.connect(sink2,1);
		
		Element[] u = t.getOutputs();
		
		assertSame(sink1,u[0]);
		assertSame(sink2,u[1]);
	}

}
