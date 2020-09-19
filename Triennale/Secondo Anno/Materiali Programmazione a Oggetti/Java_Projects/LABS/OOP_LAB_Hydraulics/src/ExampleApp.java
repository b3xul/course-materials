import hydraulic.*;

public class ExampleApp {

	public static void main(String args[]){
		
		HSystem s = new HSystem();
	
		// 1) the elements of the system are defined and added
		Source source1 = new Source("Source1");
		s.addElement(source1);
		Split split1 = new Split("Split1");
		s.addElement(split1);
		Tap tap1 = new Tap("Tap1");
		s.addElement(tap1);
		Split split2 = new Split("Split2");
		s.addElement(split2);
		Sink sink1 = new Sink("sink A");
		s.addElement(sink1);
		Sink sink2 = new Sink("sink B");
		s.addElement(sink2);
//		Split split3 = new Split("Split3");
//		s.addElement(split3);
		Tap tap2 = new Tap("Tap2");
		s.addElement(tap2);
		Sink sink3 = new Sink("sink C");
		s.addElement(sink3);
		
		Source source2 = new Source("Source2");
		s.addElement(source2);
	
		// 2) elements are then connected
		source1.connect(split1);
		split1.connect(tap1,0);
		tap1.connect(split2);
		split2.connect(sink1,0);
		split2.connect(sink2,1);
//		split1.connect(split3,0);
//		split3.connect(sink3,0);
		split1.connect(tap2,1);
		tap2.connect(sink3);
		tap2.connect(source2);
		sink2.connect(tap2);
		
//		// 3) simulation parameters are then defined
		source1.setFlow(20);
		tap1.setOpen(true);
		tap2.setOpen(false);
		source2.setFlow(60);
		
//		// 4) simulation starts
		s.simulate();
		
//		// 5) print the system layout
		System.out.println(s.layout());
	}
}
