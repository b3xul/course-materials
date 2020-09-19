package it.polito.po.test;

import junit.framework.TestCase;
import university.University;

public class TestUniversity extends TestCase {
	static final String universityName = "Politecnico di Torino";
	University poli;
	
	public void setUp(){ // FIXTURE
		poli = new University(universityName);
		poli.setRector("Marco", "Gilli");
	}

	public void testNameRector(){
		assertEquals("Wrong university name", // msg in caso di fallimento
						universityName,		// valore atteso
						poli.getName());		// valore effettivo
		
		assertNotNull("Wrong rector name",poli.getName());
		assertTrue("Wrong rector name",poli.getRector().contains("Gilli"));
	}
	
	public void testEnroll(){				
		int s1 = poli.enroll("Mario","Rossi");
		int s2 = poli.enroll("Giuseppe","Verdi");
		
		assertEquals("Wrong id number for first enrolled student",10000,s1);
		assertEquals("Wrong id number for second enrolled student",10001,s2);
	}
	
	public void testCourses(){
		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "Marco Torchiano");
		
		assertEquals("Wrong id number for first activated course",10,macro);
		assertEquals("Wrong id number for second activated course",11,oop);
		
		assertNotNull("Missing course description",poli.course(macro));
		assertTrue("Wrong description of course",poli.course(macro).contains("Macro Economics"));
	}
	
	public void testAttendees(){
		poli.enroll("Mario","Rossi");
		poli.enroll("Giuseppe","Verdi");
		
		poli.activate("Macro Economics", "Paul Krugman");
		poli.activate("Object Oriented Programming", "Marco Torchiano");
		
		poli.register(10000, 10);
		poli.register(10001, 10);
		poli.register(10001, 11);
		
		String attendees = poli.listAttendees(10);
		int nLines = 1 + attendees.trim().replaceAll("[^\n]", "").length();
		assertEquals("Wrong number of attendees for course 10",2,nLines);

		attendees = poli.listAttendees(11);
		nLines = 1 + attendees.trim().replaceAll("[^\n]", "").length();
		assertEquals("Wrong number of attendees for course 11",1,nLines);
	}
	public void testStudyPlan(){
		poli.enroll("Mario","Rossi");
		poli.enroll("Giuseppe","Verdi");
		
		poli.activate("Macro Economics", "Paul Krugman");
		poli.activate("Object Oriented Programming", "Marco Torchiano");
		
		poli.register(10000, 10);
		poli.register(10001, 10);
		poli.register(10001, 11);
		
		String plan = poli.studyPlan(10001);
		int nLines = 1 + plan.trim().replaceAll("[^\n]", "").length();
		assertEquals("Wrong number of courses for student 10001",2,nLines);

		plan = poli.studyPlan(10000);
		nLines = 1 + plan.trim().replaceAll("[^\n]", "").length();
		assertEquals("Wrong number of courses for student 10000",1,nLines);
	}
}
