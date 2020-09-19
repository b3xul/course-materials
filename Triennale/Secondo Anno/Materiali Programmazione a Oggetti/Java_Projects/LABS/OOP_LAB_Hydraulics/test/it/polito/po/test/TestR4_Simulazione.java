package it.polito.po.test;

import hydraulic.*;
import junit.framework.TestCase;


public class TestR4_Simulazione extends TestCase {

	public void testElementiSemplici(){
		CaptureHelper h = new CaptureHelper();
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
		double flow = 100.0;
		src.setFlow(flow);
		tap.setOpen(true);
		
		h.startCapture();
		s.simulate();
		h.stopCapture();
		
		double inTap = h.findNNum("Tap",1);
		double outTap = h.findNNum("Tap",2);
		
		double inSink = h.findNNum("Sink",1);

		assertEquals(flow,inTap,0.01);
		assertEquals(flow,outTap,0.01);
		assertEquals(flow,inSink,0.01);
	}
	
	public void testSplit(){
		CaptureHelper h = new CaptureHelper();

		HSystem s = new HSystem();
		Source src = new Source("Src");		
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
		
		double flow = 100.0;
		src.setFlow(flow);
		
		h.startCapture();
		s.simulate();
		h.stopCapture();

		double out1 = h.findNNum("Sink 1",1);
		double out2 = h.findNNum("Sink 2",1);;

		assertEquals(50,out1,0.01);
		assertEquals(50,out2,0.01);
	}

}
