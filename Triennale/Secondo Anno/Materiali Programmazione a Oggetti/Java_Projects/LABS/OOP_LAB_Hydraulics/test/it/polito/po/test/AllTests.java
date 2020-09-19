package it.polito.po.test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for default package");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestR1_Elements.class);
		suite.addTestSuite(TestR2_ElementiSemplici.class);
		suite.addTestSuite(TestR3_ElementiComplessi.class);
		suite.addTestSuite(TestR4_Simulazione.class);
		//$JUnit-END$
		return suite;
	}
}